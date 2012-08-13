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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.UserIdMapper;
import com.liferay.portal.model.UserIdMapperModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the UserIdMapper service. Represents a row in the &quot;UserIdMapper&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portal.model.UserIdMapperModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserIdMapperImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserIdMapperImpl
 * @see com.liferay.portal.model.UserIdMapper
 * @see com.liferay.portal.model.UserIdMapperModel
 * @generated
 */
public class UserIdMapperModelImpl extends BaseModelImpl<UserIdMapper>
	implements UserIdMapperModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user ID mapper model instance should use the {@link com.liferay.portal.model.UserIdMapper} interface instead.
	 */
	public static final String TABLE_NAME = "UserIdMapper";
	public static final Object[][] TABLE_COLUMNS = {
			{ "userIdMapperId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "type_", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "externalUserId", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table UserIdMapper (userIdMapperId LONG not null primary key,userId LONG,type_ VARCHAR(75) null,description VARCHAR(75) null,externalUserId VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table UserIdMapper";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.model.UserIdMapper"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.UserIdMapper"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.model.UserIdMapper"),
			true);
	public static long EXTERNALUSERID_COLUMN_BITMASK = 1L;
	public static long TYPE_COLUMN_BITMASK = 2L;
	public static long USERID_COLUMN_BITMASK = 4L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.UserIdMapper"));

	public UserIdMapperModelImpl() {
	}

	public long getPrimaryKey() {
		return _userIdMapperId;
	}

	public void setPrimaryKey(long primaryKey) {
		setUserIdMapperId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_userIdMapperId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return UserIdMapper.class;
	}

	public String getModelClassName() {
		return UserIdMapper.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userIdMapperId", getUserIdMapperId());
		attributes.put("userId", getUserId());
		attributes.put("type", getType());
		attributes.put("description", getDescription());
		attributes.put("externalUserId", getExternalUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userIdMapperId = (Long)attributes.get("userIdMapperId");

		if (userIdMapperId != null) {
			setUserIdMapperId(userIdMapperId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String externalUserId = (String)attributes.get("externalUserId");

		if (externalUserId != null) {
			setExternalUserId(externalUserId);
		}
	}

	public long getUserIdMapperId() {
		return _userIdMapperId;
	}

	public void setUserIdMapperId(long userIdMapperId) {
		_userIdMapperId = userIdMapperId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	public String getType() {
		if (_type == null) {
			return StringPool.BLANK;
		}
		else {
			return _type;
		}
	}

	public void setType(String type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (_originalType == null) {
			_originalType = _type;
		}

		_type = type;
	}

	public String getOriginalType() {
		return GetterUtil.getString(_originalType);
	}

	public String getDescription() {
		if (_description == null) {
			return StringPool.BLANK;
		}
		else {
			return _description;
		}
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getExternalUserId() {
		if (_externalUserId == null) {
			return StringPool.BLANK;
		}
		else {
			return _externalUserId;
		}
	}

	public void setExternalUserId(String externalUserId) {
		_columnBitmask |= EXTERNALUSERID_COLUMN_BITMASK;

		if (_originalExternalUserId == null) {
			_originalExternalUserId = _externalUserId;
		}

		_externalUserId = externalUserId;
	}

	public String getOriginalExternalUserId() {
		return GetterUtil.getString(_originalExternalUserId);
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			UserIdMapper.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserIdMapper toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (UserIdMapper)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public Object clone() {
		UserIdMapperImpl userIdMapperImpl = new UserIdMapperImpl();

		userIdMapperImpl.setUserIdMapperId(getUserIdMapperId());
		userIdMapperImpl.setUserId(getUserId());
		userIdMapperImpl.setType(getType());
		userIdMapperImpl.setDescription(getDescription());
		userIdMapperImpl.setExternalUserId(getExternalUserId());

		userIdMapperImpl.resetOriginalValues();

		return userIdMapperImpl;
	}

	public int compareTo(UserIdMapper userIdMapper) {
		long primaryKey = userIdMapper.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		UserIdMapper userIdMapper = null;

		try {
			userIdMapper = (UserIdMapper)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = userIdMapper.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		UserIdMapperModelImpl userIdMapperModelImpl = this;

		userIdMapperModelImpl._originalUserId = userIdMapperModelImpl._userId;

		userIdMapperModelImpl._setOriginalUserId = false;

		userIdMapperModelImpl._originalType = userIdMapperModelImpl._type;

		userIdMapperModelImpl._originalExternalUserId = userIdMapperModelImpl._externalUserId;

		userIdMapperModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<UserIdMapper> toCacheModel() {
		UserIdMapperCacheModel userIdMapperCacheModel = new UserIdMapperCacheModel();

		userIdMapperCacheModel.userIdMapperId = getUserIdMapperId();

		userIdMapperCacheModel.userId = getUserId();

		userIdMapperCacheModel.type = getType();

		String type = userIdMapperCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			userIdMapperCacheModel.type = null;
		}

		userIdMapperCacheModel.description = getDescription();

		String description = userIdMapperCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			userIdMapperCacheModel.description = null;
		}

		userIdMapperCacheModel.externalUserId = getExternalUserId();

		String externalUserId = userIdMapperCacheModel.externalUserId;

		if ((externalUserId != null) && (externalUserId.length() == 0)) {
			userIdMapperCacheModel.externalUserId = null;
		}

		return userIdMapperCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{userIdMapperId=");
		sb.append(getUserIdMapperId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", externalUserId=");
		sb.append(getExternalUserId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.model.UserIdMapper");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userIdMapperId</column-name><column-value><![CDATA[");
		sb.append(getUserIdMapperId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>externalUserId</column-name><column-value><![CDATA[");
		sb.append(getExternalUserId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = UserIdMapper.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			UserIdMapper.class
		};
	private long _userIdMapperId;
	private long _userId;
	private String _userUuid;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _type;
	private String _originalType;
	private String _description;
	private String _externalUserId;
	private String _originalExternalUserId;
	private long _columnBitmask;
	private UserIdMapper _escapedModelProxy;
}