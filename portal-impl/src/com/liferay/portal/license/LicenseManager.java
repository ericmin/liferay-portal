/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.license;

import com.liferay.portal.CompanyMaxUsersException;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.cluster.ClusterNodeResponse;
import com.liferay.portal.kernel.cluster.ClusterNodeResponses;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.Base64InputStream;
import com.liferay.portal.kernel.io.Base64OutputStream;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.license.util.LicenseUtil;
import com.liferay.portal.license.validator.ClusterValidator;
import com.liferay.portal.license.validator.DeveloperValidator;
import com.liferay.portal.license.validator.KeyValidator;
import com.liferay.portal.license.validator.LicenseTypeValidator;
import com.liferay.portal.license.validator.LicenseValidator;
import com.liferay.portal.license.validator.LimitedValidator;
import com.liferay.portal.license.validator.PerUserValidator;
import com.liferay.portal.license.validator.ProductionValidator;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.util.Encryptor;
import com.liferay.util.transport.DatagramHandler;
import com.liferay.util.transport.MulticastTransport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.DatagramPacket;

import java.security.Key;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shuyang Zhou
 * @author Amos Fong
 */
public class LicenseManager {

	public static final String PRODUCT_ID_PORTAL = "Portal";

	public static final int PRODUCT_TYPE_CE = 1;

	public static final int PRODUCT_TYPE_EE = 2;

	public static final int STATE_ABSENT = 1;

	public static final int STATE_EXPIRED = 2;

	public static final int STATE_GOOD = 3;

	public static final int STATE_INACTIVE = 4;

	public static final int STATE_INVALID = 5;

	public static final int STATE_OVERLOAD = 6;

	public static void checkBinaryLicense(String productId) {
		List<License> licenses = _getBinaryLicenses(productId);

		for (License license : licenses) {
			_checkBinaryLicense(license);
		}
	}

	public static void checkClusterLicense() {
		License license = _getLicense(PRODUCT_ID_PORTAL);

		if (license == null) {
			checkBinaryLicense(PRODUCT_ID_PORTAL);

			license = _getLicense(PRODUCT_ID_PORTAL);
		}

		if (license == null) {
			return;
		}

		String licenseEntryType = license.getLicenseEntryType();

		if ((licenseEntryType != null) && licenseEntryType.equals(License.TYPE_CLUSTER)) {
			int maxServers = license.getMaxServers();

			if (maxServers > 0) {
				List<ClusterNode> clusterNodes = ClusterExecutorUtil.getClusterNodes();

				if (clusterNodes.size() > maxServers) {
					java.util.Collections.sort(clusterNodes);

					ClusterNode lastClusterNode = clusterNodes.get(clusterNodes.size() - 1);

					try {
						if (lastClusterNode.equals(ClusterExecutorUtil.getLocalClusterNode())) {
							_log.error("Detected " + clusterNodes.size() + " nodes in the cluster. Your license allows a maximum of " + maxServers + " cluster nodes. Local server is shutting down.");

							System.exit(-20);
						}
					}
					catch (Exception e) {
					}
				}
			}
		}
	}

	public static void checkUserLicense() throws com.liferay.portal.kernel.exception.PortalException {
		try {
			License license = _getLicense(PRODUCT_ID_PORTAL);

			if (license == null) {
				return;
			}

			long maxUsersCount = license.getMaxUsers();

			if (maxUsersCount > 0) {
				java.sql.Connection con = null;
				java.sql.PreparedStatement ps = null;
				java.sql.ResultSet rs = null;

				try {
					con = com.liferay.portal.kernel.dao.jdbc.DataAccess.getConnection();

					ps = con.prepareStatement("select count(*) from User_ where (defaultUser = ?) and (status = ?)");

					ps.setBoolean(1, false);
					ps.setLong(2, com.liferay.portal.kernel.workflow.WorkflowConstants.STATUS_APPROVED);

					rs = ps.executeQuery();

					while (rs.next()) {
						long count = rs.getLong(1);

						if (count >= maxUsersCount) {
							throw new CompanyMaxUsersException();
						}
					}
				}
				finally {
					com.liferay.portal.kernel.dao.jdbc.DataAccess.cleanUp(con, ps, rs);
				}
			}
		}
		catch (java.sql.SQLException se) {
			throw new com.liferay.portal.kernel.exception.PortalException(se);
		}
	}

	public static void checkUserLicense(int authResult) throws com.liferay.portal.kernel.exception.PortalException {
		if (authResult != com.liferay.portal.security.auth.Authenticator.SUCCESS) {
			return;
		}

		License license = _getLicense(PRODUCT_ID_PORTAL);

		if (license == null) {
			return;
		}

		long maxConcurrentUsersCount = license.getMaxConcurrentUsers();

		if (maxConcurrentUsersCount > 0) {
			if (!PropsValues.LIVE_USERS_ENABLED) {
				_log.error("The property live.users.enabled must be true to use this license");

				throw new CompanyMaxUsersException();
			}

			if (com.liferay.portal.liveusers.LiveUsers.getUserIdsCount() >= maxConcurrentUsersCount) {
				throw new CompanyMaxUsersException();
			}
		}
	}

	public static com.liferay.portal.events.EventsProcessor getEventsProcessor() {
		ClassLoader classLoader = new com.liferay.portal.ee.license.classloader.DecryptorClassLoader();

		try {
			Class<?> eventProcessorClass = classLoader.loadClass(
				"com.liferay.portal.ee.license.EventsProcessorImpl");

			return (com.liferay.portal.events.EventsProcessor)eventProcessorClass.newInstance();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e);
		}
	}

	public static com.liferay.portal.events.StartupAction getStartupAction()
		throws Exception {

		ClassLoader classLoader = new com.liferay.portal.ee.license.classloader.DecryptorClassLoader();

		Class<?> startupActionClass = classLoader.loadClass(
			"com.liferay.portal.ee.license.StartupAction");

		com.liferay.portal.events.StartupAction startupAction =
			(com.liferay.portal.events.StartupAction)startupActionClass.newInstance();

		return startupAction;
	}

	public static void writeKey(
		HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {

		try {
			long userId = com.liferay.portal.util.PortalUtil.getBasicAuthUserId(request);

			if (userId <= 0) {
				return;
			}
		}
		catch (Exception e) {
			return;
		}

		String productId = com.liferay.portal.kernel.util.ParamUtil.getString(request, "productId");
		String uuid = com.liferay.portal.kernel.util.ParamUtil.getString(request, "uuid");

		if (Validator.isNull(productId) || Validator.isNull(uuid)) {
			return;
		}

		int licenseState = getLicenseState(productId);

		try {
			String digest = _digest(productId, uuid, licenseState);

			response.setContentType(com.liferay.portal.kernel.util.ContentTypes.TEXT);

			com.liferay.portal.kernel.servlet.ServletResponseUtil.write(response, digest);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static String _digest(
			String productId, String uuid, int licenseState)
		throws Exception {

		java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance("MD5");

		String digest = _digest(messageDigest, uuid + productId);

		int length = digest.length();

		StringBuilder sb = new StringBuilder(length + (length / 4));

		for (int i = 0; i < (length / 2); i++) {
			if ((i % 2) == 0) {
				sb.append(licenseState);
			}

			sb.append(digest.charAt(i));
			sb.append(digest.charAt(length - i - 1));
		}

		return _digest(messageDigest, sb.toString());
	}

	private static String _digest(java.security.MessageDigest messageDigest, String text) {
		messageDigest.update(text.getBytes());

		byte[] bytes = messageDigest.digest();

		StringBuilder sb = new StringBuilder(bytes.length << 1);

		for (int i = 0; i < bytes.length; i++) {
			int byte_ = bytes[i] & 0xff;

			sb.append(_HEX_CHARACTERS[byte_ >> 4]);
			sb.append(_HEX_CHARACTERS[byte_ & 0xf]);
		}

		return sb.toString();
	}

	private static final char[] _HEX_CHARACTERS = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
		'e', 'f'
	};

	public static void checkBinaryLicenses () {
		Set<License> licenses = _getBinaryLicenses();

		if (licenses.isEmpty()) {
			_log.error("No binary licenses found");

			return;
		}

		for (License license : licenses) {
			_checkBinaryLicense(license);
		}
	}

	public static List<Map<String, String>> getClusterLicenseProperties(
		String clusterNodeId) {

		List<ClusterNode> clusterNodes = ClusterExecutorUtil.getClusterNodes();

		ClusterNode clusterNode = null;

		for (ClusterNode curClusterNode : clusterNodes) {
			String curClusterNodeId = curClusterNode.getClusterNodeId();

			if (curClusterNodeId.equals(clusterNodeId)) {
				clusterNode = curClusterNode;

				break;
			}
		}

		if (clusterNode == null) {
			return null;
		}

		try {
			if (clusterNode.equals(ClusterExecutorUtil.getLocalClusterNode())) {
				return getLicenseProperties();
			}

			ClusterRequest clusterRequest = ClusterRequest.createUnicastRequest(
				_getLicensePropertiesMethodHandler, clusterNodeId);

			FutureClusterResponses futureClusterResponses =
				ClusterExecutorUtil.execute(clusterRequest);

			ClusterNodeResponses clusterNodeResponses =
				futureClusterResponses.get(20000, TimeUnit.MILLISECONDS);

			ClusterNodeResponse clusterNodeResponse =
				clusterNodeResponses.getClusterResponse(clusterNode);

			return (List<Map<String, String>>)clusterNodeResponse.getResult();
		}
		catch (Exception e) {
			_log.error(e, e);

			return null;
		}
	}

	public static LicenseInfo getLicenseInfo(String productId) {
		License license = _getLicense(productId);

		if (license == null) {
			return null;
		}

		return new LicenseInfo(
			license.getOwner(), license.getDescription(),
			license.getProductEntryName(), license.getProductId(),
			license.getProductVersion(), license.getLicenseEntryType(),
			license.getLicenseVersion(), license.getStartDate(),
			license.getExpirationDate(), license.getMaxUsers(),
			license.getHostNames(), license.getIpAddresses(),
			license.getMacAddresses());
	}

	public static List<Map<String, String>> getLicenseProperties() {
		List<Map<String, String>> licenseProperties =
			new ArrayList<Map<String, String>>();

		for (Map.Entry<String, AtomicStampedReference<License>> entry :
				_licenseStampedReferences.entrySet()) {

			String productId = entry.getKey();

			AtomicStampedReference<License> licenseStampedReference =
				entry.getValue();

			int[] stampHolder = new int[1];

			License license = licenseStampedReference.get(stampHolder);

			int licenseState = stampHolder[0];

			if ((license == null) || (licenseState == STATE_ABSENT)) {
				continue;
			}

			Map<String, String> curLicenseProperties =
				KeyValidator.getProperties(license);

			curLicenseProperties.put(
				"licenseState", String.valueOf(licenseState));
			curLicenseProperties.put("productId", productId);

			if (productId.equals(PRODUCT_ID_PORTAL)) {
				licenseProperties.add(0, curLicenseProperties);
			}
			else {
				licenseProperties.add(curLicenseProperties);
			}
		}

		return licenseProperties;
	}

	public static Map<String, String> getLicenseProperties(String productId) {
		Map<String, String> properties = new HashMap<String, String>();

		AtomicStampedReference<License> licenseStampedReference =
			_licenseStampedReferences.get(productId);

		if (licenseStampedReference == null) {
			return properties;
		}

		int[] stampHolder = new int[1];

		License license = licenseStampedReference.get(stampHolder);

		int licenseState = stampHolder[0];

		if ((license == null) || (licenseState == STATE_ABSENT)) {
			return properties;
		}
		else {
			return KeyValidator.getProperties(license);
		}
	}

	public static int getLicenseState() {
		return getLicenseState(null, PRODUCT_ID_PORTAL);
	}

	public static int getLicenseState(HttpServletRequest request) {
		return getLicenseState(request, PRODUCT_ID_PORTAL);
	}

	public static int getLicenseState(
		HttpServletRequest request, String productId) {

		AtomicStampedReference<License> licenseStampedReference =
			_licenseStampedReferences.get(productId);

		int[] stampHolder = new int[1];

		License license = null;

		if (licenseStampedReference != null) {
			license = licenseStampedReference.get(stampHolder);
		}

		int licenseState = stampHolder[0];

		if (license == null) {
			return STATE_ABSENT;
		}

		if (licenseState != STATE_GOOD) {
			return licenseState;
		}

		if (license.isExpired()) {
			_setLicense(license, STATE_EXPIRED, true);

			return STATE_EXPIRED;
		}

		String licenseEntryType = license.getLicenseEntryType();

		if (licenseEntryType.equals(License.TYPE_DEVELOPER) ||
			licenseEntryType.equals(License.TYPE_DEVELOPER_CLUSTER)) {

			if (request != null) {
				String remoteAddr = request.getRemoteAddr();

				_clientIPAddresses.add(remoteAddr);
			}

			if (_clientIPAddresses.size() > license.getMaxHttpSessions()) {
				_setLicense(license, STATE_OVERLOAD, true);

				return STATE_OVERLOAD;
			}
		}

		//  Good license state

		return licenseState;
	}

	public static int getLicenseState(String productId) {
		return getLicenseState(null, productId);
	}

	public static String getServerId() throws Exception {
		if (Validator.isNotNull(_serverId)) {
			return _serverId;
		}

		Properties serverProperties = _getServerProperties();

		_serverId = serverProperties.getProperty("serverId");

		if (Validator.isNull(_serverId)) {
			_serverId = _generateServerId();

			serverProperties.put("serverId", _serverId);

			_writeServerProperties(serverProperties);
		}
		else {
			byte[] serverIdBytes = (byte[])Base64.stringToObject(_serverId);

			for (Key key : _keys) {
				serverIdBytes = Encryptor.decryptUnencodedAsBytes(
					key, serverIdBytes);
			}

			Properties serverIdProperties = new Properties();

			PropertiesUtil.load(serverIdProperties, new String(serverIdBytes));

			String serverIdHostName = GetterUtil.getString(
				serverIdProperties.getProperty("hostName"));

			if (serverIdHostName.equalsIgnoreCase(LicenseUtil.getHostName())) {
				return _serverId;
			}

			List<String> serverIdIpAddresses = ListUtil.fromArray(
				StringUtil.split(
					serverIdProperties.getProperty("ipAddresses")));

			serverIdIpAddresses.retainAll(LicenseUtil.getIpAddresses());

			if (!serverIdIpAddresses.isEmpty()) {
				return _serverId;
			}

			List<String> serverIdMacAddresses = ListUtil.fromArray(
				StringUtil.split(
					serverIdProperties.getProperty("macAddresses")));

			serverIdMacAddresses.retainAll(LicenseUtil.getMacAddresses());

			if (!serverIdMacAddresses.isEmpty()) {
				return _serverId;
			}

			_serverId = _generateServerId();

			serverProperties.put("serverId", _serverId);

			_writeServerProperties(serverProperties);
		}

		return _serverId;
	}

	public static void writeBinaryLicense(License license) throws Exception {
		File licenseRepositoryDir = new File(
			LicenseUtil.LICENSE_REPOSITORY_DIR);

		if (!licenseRepositoryDir.exists()) {
			licenseRepositoryDir.mkdirs();
		}

		File binaryLicenseFile = _buildBinaryFile(license);

		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;

		try {
			fileOutputStream = new FileOutputStream(binaryLicenseFile);

			objectOutputStream = new ObjectOutputStream(
				new Base64OutputStream(fileOutputStream));

			license.setLastAccessedTime(System.currentTimeMillis());

			objectOutputStream.writeInt(3);
			objectOutputStream.writeUTF(
				GetterUtil.getString(license.getAccountEntryName()));
			objectOutputStream.writeUTF(
				GetterUtil.getString(license.getDescription()));
			objectOutputStream.writeObject(license.getExpirationDate());
			objectOutputStream.writeObject(license.getHostNames());
			objectOutputStream.writeObject(license.getIpAddresses());
			objectOutputStream.writeUTF(GetterUtil.getString(license.getKey()));
			objectOutputStream.writeLong(license.getLastAccessedTime());
			objectOutputStream.writeUTF(
				GetterUtil.getString(license.getLicenseEntryName()));
			objectOutputStream.writeUTF(
				GetterUtil.getString(license.getLicenseEntryType()));
			objectOutputStream.writeUTF(
				GetterUtil.getString(license.getLicenseVersion()));
			objectOutputStream.writeObject(license.getMacAddresses());
			objectOutputStream.writeInt(license.getMaxHttpSessions());
			objectOutputStream.writeInt(license.getMaxServers());
			objectOutputStream.writeLong(license.getMaxConcurrentUsers());
			objectOutputStream.writeLong(license.getMaxUsers());
			objectOutputStream.writeUTF(
				GetterUtil.getString(license.getOwner()));
			objectOutputStream.writeUTF(
				GetterUtil.getString(license.getProductEntryName()));
			objectOutputStream.writeUTF(
				GetterUtil.getString(license.getProductId()));
			objectOutputStream.writeUTF(
				GetterUtil.getString(license.getProductVersion()));
			objectOutputStream.writeObject(license.getServerIds());
			objectOutputStream.writeObject(license.getStartDate());
		}
		finally {
			if (objectOutputStream != null) {
				objectOutputStream.close();
			}

			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
	}

	private static File _buildBinaryFile(License license) {
		StringBundler sb = new StringBundler(6);

		String productId = license.getProductId();

		if (productId.equals(PRODUCT_ID_PORTAL)) {
			sb.append(StringUtil.extractChars(license.getAccountEntryName()));
			sb.append("_");
		}

		sb.append(StringUtil.extractChars(license.getProductEntryName()));
		sb.append("_");
		sb.append(StringUtil.extractChars(license.getLicenseEntryType()));
		sb.append(".li");

		return new File(LicenseUtil.LICENSE_REPOSITORY_DIR, sb.toString());
	}

	private static void _checkBinaryLicense(License license) {
		try {
			String serverId = getServerId();

			if (license == null) {
				return;
			}

			String version = license.getLicenseVersion();

			if (version.equals("3") || version.equals("4")) {
				String[] serverIds = license.getServerIds();

				if ((serverIds != null) && (serverIds.length > 0)) {
					if (!ArrayUtil.contains(serverIds, serverId)) {
						throw new Exception(
							"Server id matching failed. Allowed server ids: " +
								StringUtil.merge(serverIds));
					}

					if (!_isActiveLicense(license, false)) {
						_setLicense(license, STATE_INACTIVE);

						_log.error(
							license.getProductEntryName() +
								" license is inactive");

						return;
					}
				}
			}

			if (license.isExpired()) {
				_setLicense(license, STATE_EXPIRED);

				_log.error(
					license.getProductEntryName() + " license is expired");

				return;
			}

			_validatorChain.validate(license);

			_setLicense(license, STATE_GOOD);

			if (_log.isInfoEnabled()) {
				_log.info(
					license.getProductEntryName() +
						" license validation passed");
			}
		}
		catch (CompanyMaxUsersException cmue) {
			_setLicense(license, STATE_OVERLOAD);

			_log.error(
				license.getProductEntryName() + " license validation failed",
				cmue);
		}
		catch (Exception e) {
			_setLicense(license, STATE_INVALID);

			_log.error(
				license.getProductEntryName() + " license validation failed",
				e);
		}
	}

	private static void _connectMulticast() throws Exception {
		if (_multicastTransport == null) {
			DatagramHandler handler = new MulticastLicenseDatagramHandler();

			_multicastTransport = new MulticastTransport(
				handler, "239.255.0.5", 23305);
		}

		if (!_multicastTransport.isConnected()) {
			_multicastTransport.connect();
		}
	}

	private static void _disconnectMulticast() {
		if ((_multicastTransport != null) &&
			_multicastTransport.isConnected()) {

			_multicastTransport.disconnect();
		}

		_portalInstances.clear();
	}

	private static String _generateServerId() throws Exception {
		String hostName = LicenseUtil.getHostName();
		Set<String> ipAddresses = LicenseUtil.getIpAddresses();
		Set<String> macAddresses = LicenseUtil.getMacAddresses();

		Properties serverIdProperties = new Properties();

		serverIdProperties.put("hostName", hostName);
		serverIdProperties.put("ipAddresses", StringUtil.merge(ipAddresses));
		serverIdProperties.put("macAddresses", StringUtil.merge(macAddresses));
		serverIdProperties.put("salt", UUID.randomUUID().toString());

		String propertiesString = PropertiesUtil.toString(serverIdProperties);

		byte[] bytes = propertiesString.getBytes(StringPool.UTF8);

		for (int i = _keys.length - 1; i >= 0; i--) {
			bytes = Encryptor.encryptUnencoded(_keys[i], bytes);
		}

		return Base64.objectToString(bytes);
	}

	private static TreeSet<License> _getBinaryLicenses() {
		TreeSet<License> licenses = new TreeSet<License>();

		File licenseRepositoryDir = new File(
			LicenseUtil.LICENSE_REPOSITORY_DIR);

		if (!licenseRepositoryDir.exists() ||
			!licenseRepositoryDir.isDirectory()) {

			_log.info("Failed to find directory " + licenseRepositoryDir);

			return licenses;
		}

		File[] binaryLicenseFiles = licenseRepositoryDir.listFiles();

		if ((binaryLicenseFiles == null) || (binaryLicenseFiles.length == 0)) {
			_log.info(
				"Failed to find license files in directory " +
					licenseRepositoryDir);

			return licenses;
		}

		for (File binaryLicenseFile : binaryLicenseFiles) {
			if (binaryLicenseFile.isDirectory()) {
				continue;
			}

			FileInputStream fileInputStream = null;
			ObjectInputStream objectInputStream = null;

			try {
				fileInputStream = new FileInputStream(binaryLicenseFile);

				objectInputStream = new ObjectInputStream(
					new Base64InputStream(fileInputStream));

				int licenseVersion = objectInputStream.readInt();

				if (licenseVersion == 3) {
					licenses.add(_getBinaryLicenseVersion3(objectInputStream));
				}
			}
			catch (Exception e) {
				_log.error(
					"Failed to read license file " + binaryLicenseFile, e);
			}
			finally {
				if (objectInputStream != null) {
					try {
						objectInputStream.close();
					}
					catch (IOException ioe) {
					}
				}

				if (fileInputStream != null) {
					try {
						fileInputStream.close();
					}
					catch (IOException ioe) {
					}
				}
			}
		}

		long currentTime = System.currentTimeMillis();

		Iterator<License> licenseIterator = licenses.iterator();

		while (licenseIterator.hasNext()) {
			License license = licenseIterator.next();

			// Remove corrupted licenses

			if (!KeyValidator.validate(license)) {
				licenseIterator.remove();

				File file = _buildBinaryFile(license);

				file.delete();

				_log.error(
					"Corrupt license file. Removing license file " + license);

				continue;
			}

			// Skip licenses with future last accessed times

			long lastAccessedTime = license.getLastAccessedTime();

			if (currentTime < lastAccessedTime) {
				licenseIterator.remove();

				_log.error(
					"A license modified in the future was detected. License " +
						"Modified: " + lastAccessedTime + ". Current time: " +
							currentTime + ". Skipping license file " + license);

				continue;
			}

			// Touch license file

			try {
				writeBinaryLicense(license);
			}
			catch (Exception e) {
			}

			// Skip licenses that have not started yet

			Date startDate = license.getStartDate();

			if ((currentTime + (Time.DAY * 2)) < startDate.getTime()) {
				licenseIterator.remove();

				_log.error(
					"License has not reached start date yet. Skipping " +
						"license file " + license);

				continue;
			}
		}

		return licenses;
	}

	private static List<License> _getBinaryLicenses(String productId) {
		List<License> licenses = new ArrayList<License>();

		TreeSet<License> binaryLicenses = _getBinaryLicenses();

		if (binaryLicenses.isEmpty()) {
			if (productId.equals(PRODUCT_ID_PORTAL)) {
				return licenses;
			}
		}

		for (License binaryLicense : binaryLicenses) {
			String curProductId = binaryLicense.getProductId();

			if (curProductId.equals(productId)) {
				licenses.add(binaryLicense);
			}
		}

		return licenses;
	}

	private static License _getBinaryLicenseVersion3(
			ObjectInputStream objectInputStream)
		throws ClassNotFoundException, IOException {

		String accountEntryName = objectInputStream.readUTF();
		String description = objectInputStream.readUTF();
		Date expirationDate = (Date)objectInputStream.readObject();
		String[] hostNames = (String[])objectInputStream.readObject();
		String[] ipAddresses = (String[])objectInputStream.readObject();
		String key = objectInputStream.readUTF();
		long lastAccessedTime = objectInputStream.readLong();
		String licenseEntryName = objectInputStream.readUTF();
		String licenseEntryType = objectInputStream.readUTF();
		String licenseVersion = objectInputStream.readUTF();
		String[] macAddresses = (String[])objectInputStream.readObject();
		int maxHttpSessions = objectInputStream.readInt();
		int maxServers = objectInputStream.readInt();
		long maxConcurrentUsers = objectInputStream.readLong();
		long maxUsers = objectInputStream.readLong();
		String owner = objectInputStream.readUTF();
		String productEntryName = objectInputStream.readUTF();
		String productId = objectInputStream.readUTF();
		String productVersion = objectInputStream.readUTF();
		String[] serverIds = (String[])objectInputStream.readObject();
		Date startDate = (Date)objectInputStream.readObject();

		License license = new License(
			accountEntryName, owner, description, productEntryName, productId,
			productVersion, licenseEntryName, licenseEntryType, licenseVersion,
			startDate, expirationDate, maxServers, maxHttpSessions,
			maxConcurrentUsers, maxUsers, hostNames, ipAddresses, macAddresses,
			serverIds, key);

		license.setLastAccessedTime(lastAccessedTime);

		return license;
	}

	private static License _getLicense(String productId) {
		AtomicStampedReference<License> licenseStampedReference =
			_licenseStampedReferences.get(productId);

		if (licenseStampedReference == null) {
			return null;
		}

		int[] stampHolder = new int[1];

		return licenseStampedReference.get(stampHolder);
	}

	private static Properties _getServerProperties() throws Exception {
		Properties serverProperties = new Properties();

		File serverIdFile = new File(
			LicenseUtil.LICENSE_REPOSITORY_DIR + "/server/serverId");

		if (!serverIdFile.exists()) {
			return serverProperties;
		}

		byte[] bytes = FileUtil.getBytes(serverIdFile);

		for (Key key : _keys) {
			bytes = Encryptor.decryptUnencodedAsBytes(key, bytes);
		}

		PropertiesUtil.load(serverProperties, new String(bytes));

		return serverProperties;
	}

	private static void _initKeys() {
		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		if (_keys == null) {
			_keys = new Key[3];

			String content = null;

			try {
				content = StringUtil.read(
					classLoader,
					"com/liferay/portal/license/classloader/keys.txt");
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			String contentDigest = DigesterUtil.digestBase64(content);

			String[] keys = StringUtil.split(content, StringPool.NEW_LINE);

			int count = 0;
			int marker = 3;
			int pos = 0;

			char[] charArray = contentDigest.toCharArray();

			for (char c : charArray) {
				int x = c;

				count++;

				if ((count % marker) == 0) {
					_keys[((marker / 3) - 1)] = (Key)Base64.stringToObject(
						keys[pos]);

					count = 0;
					marker = marker + 3;
					pos = 0;
				}
				else {
					pos += x;
				}
			}
		}
	}

	private static boolean _isActiveLicense(
			License license, boolean scheduledCheck)
		throws Exception {

		long now = System.currentTimeMillis();

		Properties serverProperties = _getServerProperties();

		String serverId = serverProperties.getProperty("serverId");

		String productId = license.getProductId();

		String randomUuid = UUID.randomUUID().toString();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("version", 1);
		jsonObject.put("cmd", "VALIDATE");
		jsonObject.put("serverId", serverId);
		jsonObject.put("productId", productId);
		jsonObject.put("key", license.getKey());
		jsonObject.put("randomUuid", randomUuid);

		try {
			String response = LicenseUtil.sendRequest(jsonObject.toString());

			JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject(
				response);

			boolean active = responseJSONObject.getBoolean("active");
			String responseRandomUuid = responseJSONObject.getString(
				"randomUuid");

			if (active && responseRandomUuid.equals(randomUuid)) {
				serverProperties.put(
					productId + "_lastActiveTime", String.valueOf(now));

				_writeServerProperties(serverProperties);

				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			long graceTime = _LICENSE_ACTIVE_CHECK_GRACE_TIME;

			if (graceTime > _LICENSE_ACTIVE_CHECK_GRACE_MAX_TIME) {
				graceTime = _LICENSE_ACTIVE_CHECK_GRACE_MAX_TIME;
			}

			StringBundler sb = new StringBundler();

			sb.append("Unable to communicate with ");
			sb.append(LicenseUtil.LICENSE_SERVER_URL);
			sb.append(". Please check the connection.");

			long lastActiveTime = GetterUtil.getLong(
				serverProperties.getProperty(productId + "_lastActiveTime"));

			long diff = now - lastActiveTime;

			if ((lastActiveTime <= 0) || (diff > graceTime)) {
				throw new Exception(sb.toString());
			}
			else {
				sb.append(" You have a grace period of ");
				sb.append((graceTime - diff) / Time.DAY);
				sb.append(" days.");

				_log.error(sb.toString(), e);

				if (scheduledCheck) {
					throw e;
				}
				else {
					_scheduleActiveCheckDaily();
				}
			}
		}

		return true;
	}

	private static void _processMulticastPacket(DatagramPacket packet)
		throws Exception {

		byte[] bytes = packet.getData();

		if ((bytes == null) || (bytes.length <= _MULTICAST_HEADER.length) ||
			(packet.getLength() <= _MULTICAST_HEADER.length)) {

			return;
		}

		byte[] requestHeader = ArrayUtil.subset(
			bytes, 0, _MULTICAST_HEADER.length);

		// Ignore packets not for license checking

		if (!Arrays.equals(requestHeader, _MULTICAST_HEADER)) {
			return;
		}

		bytes = ArrayUtil.subset(
			bytes, _MULTICAST_HEADER.length, packet.getLength());

		// Decrypt packet

		bytes = Encryptor.decryptUnencodedAsBytes(_keys[2], bytes);

		String message = new String(bytes);

		if (Validator.isNull(message)) {
			return;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(message);

		String instanceId = jsonObject.getString("instanceId");

		// Ignore packets from self

		if (instanceId.equals(_INSTANCE_ID)) {
			return;
		}

		String key = jsonObject.getString("key");

		License license = _getLicense(PRODUCT_ID_PORTAL);

		// Ignore packets from instances with a different license key

		if (!key.equals(license.getKey())) {
			return;
		}

		String cmd = jsonObject.getString("cmd");

		if (cmd.equals("request")) {
			JSONObject responseJsonObject = JSONFactoryUtil.createJSONObject();

			responseJsonObject.put("cmd", "response");
			responseJsonObject.put("instanceId", _INSTANCE_ID);
			responseJsonObject.put("key", key);
			responseJsonObject.put("requestInstanceId", instanceId);

			_sendMulticastMessage(responseJsonObject.toString());
		}
		else if (cmd.equals("response")) {
			String requestInstanceId = jsonObject.getString(
				"requestInstanceId");

			// Ignore responses meant for other instances

			if (!requestInstanceId.equals(_INSTANCE_ID)) {
				return;
			}

			_portalInstances.add(instanceId);

			if ((_portalInstances.size() + 1) > license.getMaxServers()) {
				_setLicense(license, STATE_OVERLOAD, true);
			}
		}
	}

	private static void _scheduleActiveCheckDaily() {
		if (_scheduledThreadPoolExecutor != null) {
			return;
		}

		_scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(
			1,
			new ThreadFactory() {

				public Thread newThread(Runnable runnable) {
					Thread thread = new Thread(runnable, "");

					thread.setDaemon(true);

					return thread;
				}

			}
		);

		_scheduledThreadPoolExecutor.scheduleAtFixedRate(
			new Runnable() {

				public void run() {
					_verifyActiveLicenses();
				}

			}, _INITIAL_DELAY, _LICENSE_ACTIVE_CHECK_TIME, TimeUnit.MILLISECONDS
		);
	}

	private static void _sendEmail() throws PortalException, SystemException {
		String subject = "[$PORTAL_URL$] License Unable to Validate";

		StringBundler sb = new StringBundler(8);

		sb.append("Dear [$TO_NAME$],<br /><br />");
		sb.append("Your Liferay Portal instance with host name, ");
		sb.append("[$HOST_NAME$], is unable to contact [$SERVER_URL$]. ");
		sb.append("Please check its internet connection and make sure it is ");
		sb.append("able to connect to [$SERVER_URL$] otherwise your license ");
		sb.append("will become inactive.<br /><br />");
		sb.append("Sincerely,<br />[$FROM_NAME$]<br />[$FROM_ADDRESS$]<br />");
		sb.append("[$PORTAL_URL$]<br />");

		String body = sb.toString();

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setBody(body);
		subscriptionSender.setCompanyId(PortalInstances.getDefaultCompanyId());
		subscriptionSender.setContextAttributes(
			"[$HOST_NAME$]", LicenseUtil.getHostName(), "[$SERVER_URL$]",
			LicenseUtil.LICENSE_SERVER_URL);
		subscriptionSender.setFrom(
			PropsValues.ADMIN_EMAIL_FROM_ADDRESS,
			PropsValues.ADMIN_EMAIL_FROM_NAME);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("license", LicenseUtil.LICENSE_SERVER_URL);
		subscriptionSender.setReplyToAddress(
			PropsValues.ADMIN_EMAIL_FROM_ADDRESS);
		subscriptionSender.setSubject(subject);

		if (PropsValues.OMNIADMIN_USERS.length > 0) {
			for (long userId : PropsValues.OMNIADMIN_USERS) {
				try {
					User user = UserLocalServiceUtil.getUserById(userId);

					if (user.getCompanyId() ==
							PortalInstances.getDefaultCompanyId()) {

						subscriptionSender.addRuntimeSubscribers(
							user.getEmailAddress(), user.getFullName());
					}
				}
				catch (Exception e) {
				}
			}
		}
		else {
			Role role = RoleLocalServiceUtil.getRole(
				PortalInstances.getDefaultCompanyId(),
				RoleConstants.ADMINISTRATOR);

			List<User> users = UserLocalServiceUtil.getRoleUsers(
				role.getRoleId());

			for (User user : users) {
				subscriptionSender.addRuntimeSubscribers(
					user.getEmailAddress(), user.getFullName());
			}
		}

		subscriptionSender.flushNotificationsAsync();
	}

	private static void _sendMulticastMessage(String message) throws Exception {
		byte[] bytes = message.getBytes(StringPool.UTF8);

		bytes = Encryptor.encryptUnencoded(_keys[2], bytes);

		_multicastTransport.sendMessage(
			ArrayUtil.append(_MULTICAST_HEADER, bytes));
	}

	private static void _setLicense(License license, int licenseState) {
		_setLicense(license, licenseState, false);
	}

	private static void _setLicense(
		License license, int licenseState, boolean overWrite) {

		AtomicStampedReference<License> licenseStampedReference =
			_licenseStampedReferences.get(license.getProductId());

		if (licenseStampedReference == null) {
			licenseStampedReference = new AtomicStampedReference<License>(
				license, licenseState);

			_licenseStampedReferences.put(
				license.getProductId(), licenseStampedReference);

			if (licenseState == STATE_GOOD) {
				_verifyLocalAvailability(license);
			}

			return;
		}

		int[] stampHolder = new int[1];

		License curLicense = licenseStampedReference.get(stampHolder);

		int curLicenseState = stampHolder[0];

		if (overWrite ||
			((licenseState == STATE_GOOD) && (curLicenseState != STATE_GOOD)) ||
			((licenseState == curLicenseState) &&
			 (license.compareTo(curLicense) > 0))) {

			licenseStampedReference.set(license, licenseState);

			_licenseStampedReferences.put(
				license.getProductId(), licenseStampedReference);

			if (licenseState == STATE_GOOD) {
				_verifyLocalAvailability(license);
			}
		}
	}

	private static void _verifyActiveLicenses() {
		boolean connectionFailed = false;

		for (Map.Entry<String, AtomicStampedReference<License>> entry :
				_licenseStampedReferences.entrySet()) {

			AtomicStampedReference<License> licenseStampedReference =
				entry.getValue();

			int[] stampHolder = new int[1];

			License license = licenseStampedReference.get(stampHolder);

			try {
				_isActiveLicense(license, true);
			}
			catch (Exception e) {
				connectionFailed = true;
			}
		}

		if (!connectionFailed) {
			if (_scheduledThreadPoolExecutor != null) {
				_scheduledThreadPoolExecutor.shutdown();
			}
		}
		else {
			try {
				_sendEmail();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	private static void _verifyLocalAvailability(License license) {
		String productId = license.getProductId();

		if (!productId.equals(PRODUCT_ID_PORTAL)) {
			return;
		}

		String licenseVersion = license.getLicenseVersion();
		String licenseEntryType = license.getLicenseEntryType();

		if (!licenseVersion.equals("4") || (license.getMaxServers() <= 0) ||
			(!licenseEntryType.equals(License.TYPE_LIMITED) &&
			 !licenseEntryType.equals(License.TYPE_PRODUCTION))) {

			_disconnectMulticast();

			return;
		}

		try {
			_portalInstances.clear();

			_connectMulticast();

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("cmd", "request");
			jsonObject.put("instanceId", _INSTANCE_ID);
			jsonObject.put("key", license.getKey());

			_sendMulticastMessage(jsonObject.toString());
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static void _writeServerProperties(Properties serverProperties)
		throws Exception {

		File serverIdFile = new File(
			LicenseUtil.LICENSE_REPOSITORY_DIR + "/server/serverId");

		String serverPropertiesString = PropertiesUtil.toString(
			serverProperties);

		byte[] bytes = serverPropertiesString.getBytes(StringPool.UTF8);

		for (int i = _keys.length - 1; i >= 0; i--) {
			bytes = Encryptor.encryptUnencoded(_keys[i], bytes);
		}

		FileUtil.write(serverIdFile, bytes);
	}

	private static final long _INITIAL_DELAY = 60 * Time.SECOND;

	private static final String _INSTANCE_ID = UUID.randomUUID().toString();

	private static final long _LICENSE_ACTIVE_CHECK_GRACE_MAX_TIME =
		(long)(60 * Time.DAY);

	private static final long _LICENSE_ACTIVE_CHECK_GRACE_TIME =
		GetterUtil.getLong(
			PropsUtil.get("license.active.check.grace.time"),
			(long)(7.5 * Time.DAY));

	private static final long _LICENSE_ACTIVE_CHECK_TIME = GetterUtil.getLong(
		PropsUtil.get("license.active.check.time"), Time.DAY);

	private static final byte[] _MULTICAST_HEADER = "mlpv1".getBytes();

	private static Log _log = LogFactoryUtil.getLog(LicenseManager.class);

	private static Set<String> _clientIPAddresses = new HashSet<String>();
	private static MethodHandler _getLicensePropertiesMethodHandler =
		new MethodHandler(
			new MethodKey(
				LicenseManager.class.getName(), "getLicenseProperties"));
	private static Key[] _keys;
	private static Map<String, AtomicStampedReference<License>>
		_licenseStampedReferences =
			new HashMap<String, AtomicStampedReference<License>>();
	private static MulticastTransport _multicastTransport;
	private static Set<String> _portalInstances = new HashSet<String>();
	private static ScheduledThreadPoolExecutor _scheduledThreadPoolExecutor;
	private static String _serverId;
	private static LicenseValidator _validatorChain;

	static {
		_initKeys();

		LicenseTypeValidator licenseTypeValidator = new LicenseTypeValidator();
		ClusterValidator clusterValidator = new ClusterValidator();
		ProductionValidator productionValidator = new ProductionValidator();
		LimitedValidator limitedValidator = new LimitedValidator();
		PerUserValidator perUserValidator = new PerUserValidator();
		DeveloperValidator developerValidator = new DeveloperValidator();

		licenseTypeValidator.setNextValidator(clusterValidator);
		clusterValidator.setNextValidator(productionValidator);
		productionValidator.setNextValidator(limitedValidator);
		limitedValidator.setNextValidator(perUserValidator);
		perUserValidator.setNextValidator(developerValidator);

		_validatorChain = licenseTypeValidator;
	}

	private static class MulticastLicenseDatagramHandler
		implements DatagramHandler {

		public void errorReceived(Throwable t) {
			_log.error(t, t);
		}

		public void process(DatagramPacket packet) {
			try {
				_processMulticastPacket(packet);
			}
			catch (Exception e) {
			}
		}

		private MulticastLicenseDatagramHandler() {
		}

	}

}