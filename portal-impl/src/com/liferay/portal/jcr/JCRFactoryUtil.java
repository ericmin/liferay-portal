/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.jcr;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.security.pacl.PACLClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

import java.io.Closeable;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * @author Michael Young
 */
public class JCRFactoryUtil {

	public static void closeSession(Session session) {
		if (session != null) {
			session.logout();
		}
	}

	public static Session createSession() throws RepositoryException {
		return createSession(null);
	}

	public static Session createSession(String workspaceName)
		throws RepositoryException {

		if (workspaceName == null) {
			workspaceName = JCRFactory.WORKSPACE_NAME;
		}

		if (!PropsValues.JCR_WRAP_SESSION) {
			return getJCRFactory().createSession(workspaceName);
		}

		CloseableSessionMap closableSessionMap = _closeableSessionMaps.get();

		if (!closableSessionMap.containsKey(workspaceName)) {
			Session session = getJCRFactory().createSession(workspaceName);

			Object sessionProxy = ProxyUtil.newProxyInstance(
				PACLClassLoaderUtil.getPortalClassLoader(),
				new Class<?>[] {Closeable.class, Map.class, Session.class},
				new JCRSessionInvocationHandler(session));

			closableSessionMap.put(workspaceName, (Closeable)sessionProxy);
		}

		return (Session)closableSessionMap.get(workspaceName);
	}

	public static JCRFactory getJCRFactory() {
		if (_jcrFactory == null) {
			_jcrFactory = (JCRFactory)PortalBeanLocatorUtil.locate(
				JCRFactory.class.getName());
		}

		return _jcrFactory;
	}

	public static void initialize() throws RepositoryException {
		getJCRFactory().initialize();
	}

	public static void prepare() throws RepositoryException {
		getJCRFactory().prepare();
	}

	public static void shutdown() {
		getJCRFactory().shutdown();
	}

	private static ThreadLocal<CloseableSessionMap> _closeableSessionMaps =
		new AutoResetThreadLocal<CloseableSessionMap>(
			JCRFactoryUtil.class + "._session", new CloseableSessionMap());
	private static JCRFactory _jcrFactory;

	private static class CloseableSessionMap extends HashMap<String, Closeable>
		implements Closeable {

		public void close() throws IOException {
			for (Closeable closeableSession : values()) {
				closeableSession.close();
			}
		}

	}

}