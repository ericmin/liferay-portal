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

package com.liferay.portlet.announcements.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.announcements.model.AnnouncementsFlag;
import com.liferay.portlet.announcements.model.AnnouncementsFlagModel;
import com.liferay.portlet.announcements.model.AnnouncementsFlagSoap;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the AnnouncementsFlag service. Represents a row in the &quot;AnnouncementsFlag&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portlet.announcements.model.AnnouncementsFlagModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AnnouncementsFlagImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnnouncementsFlagImpl
 * @see com.liferay.portlet.announcements.model.AnnouncementsFlag
 * @see com.liferay.portlet.announcements.model.AnnouncementsFlagModel
 * @generated
 */
@JSON(strict = true)
public class AnnouncementsFlagModelImpl extends BaseModelImpl<AnnouncementsFlag>
	implements AnnouncementsFlagModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a announcements flag model instance should use the {@link com.liferay.portlet.announcements.model.AnnouncementsFlag} interface instead.
	 */
	public static final String TABLE_NAME = "AnnouncementsFlag";
	public static final Object[][] TABLE_COLUMNS = {
			{ "flagId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "entryId", Types.BIGINT },
			{ "value", Types.INTEGER }
		};
	public static final String TABLE_SQL_CREATE = "create table AnnouncementsFlag (flagId LONG not null primary key,userId LONG,createDate DATE null,entryId LONG,value INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table AnnouncementsFlag";
	public static final String ORDER_BY_JPQL = " ORDER BY announcementsFlag.userId ASC, announcementsFlag.createDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY AnnouncementsFlag.userId ASC, AnnouncementsFlag.createDate ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portlet.announcements.model.AnnouncementsFlag"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portlet.announcements.model.AnnouncementsFlag"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portlet.announcements.model.AnnouncementsFlag"),
			true);
	public static long ENTRYID_COLUMN_BITMASK = 1L;
	public static long USERID_COLUMN_BITMASK = 2L;
	public static long VALUE_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static AnnouncementsFlag toModel(AnnouncementsFlagSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		AnnouncementsFlag model = new AnnouncementsFlagImpl();

		model.setFlagId(soapModel.getFlagId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setEntryId(soapModel.getEntryId());
		model.setValue(soapModel.getValue());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<AnnouncementsFlag> toModels(
		AnnouncementsFlagSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<AnnouncementsFlag> models = new ArrayList<AnnouncementsFlag>(soapModels.length);

		for (AnnouncementsFlagSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portlet.announcements.model.AnnouncementsFlag"));

	public AnnouncementsFlagModelImpl() {
	}

	public long getPrimaryKey() {
		return _flagId;
	}

	public void setPrimaryKey(long primaryKey) {
		setFlagId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_flagId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return AnnouncementsFlag.class;
	}

	public String getModelClassName() {
		return AnnouncementsFlag.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("flagId", getFlagId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("entryId", getEntryId());
		attributes.put("value", getValue());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long flagId = (Long)attributes.get("flagId");

		if (flagId != null) {
			setFlagId(flagId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long entryId = (Long)attributes.get("entryId");

		if (entryId != null) {
			setEntryId(entryId);
		}

		Integer value = (Integer)attributes.get("value");

		if (value != null) {
			setValue(value);
		}
	}

	@JSON
	public long getFlagId() {
		return _flagId;
	}

	public void setFlagId(long flagId) {
		_flagId = flagId;
	}

	@JSON
	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_columnBitmask = -1L;

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

	@JSON
	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@JSON
	public long getEntryId() {
		return _entryId;
	}

	public void setEntryId(long entryId) {
		_columnBitmask |= ENTRYID_COLUMN_BITMASK;

		if (!_setOriginalEntryId) {
			_setOriginalEntryId = true;

			_originalEntryId = _entryId;
		}

		_entryId = entryId;
	}

	public long getOriginalEntryId() {
		return _originalEntryId;
	}

	@JSON
	public int getValue() {
		return _value;
	}

	public void setValue(int value) {
		_columnBitmask |= VALUE_COLUMN_BITMASK;

		if (!_setOriginalValue) {
			_setOriginalValue = true;

			_originalValue = _value;
		}

		_value = value;
	}

	public int getOriginalValue() {
		return _originalValue;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			AnnouncementsFlag.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AnnouncementsFlag toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (AnnouncementsFlag)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public Object clone() {
		AnnouncementsFlagImpl announcementsFlagImpl = new AnnouncementsFlagImpl();

		announcementsFlagImpl.setFlagId(getFlagId());
		announcementsFlagImpl.setUserId(getUserId());
		announcementsFlagImpl.setCreateDate(getCreateDate());
		announcementsFlagImpl.setEntryId(getEntryId());
		announcementsFlagImpl.setValue(getValue());

		announcementsFlagImpl.resetOriginalValues();

		return announcementsFlagImpl;
	}

	public int compareTo(AnnouncementsFlag announcementsFlag) {
		int value = 0;

		if (getUserId() < announcementsFlag.getUserId()) {
			value = -1;
		}
		else if (getUserId() > announcementsFlag.getUserId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = DateUtil.compareTo(getCreateDate(),
				announcementsFlag.getCreateDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		AnnouncementsFlag announcementsFlag = null;

		try {
			announcementsFlag = (AnnouncementsFlag)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = announcementsFlag.getPrimaryKey();

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
		AnnouncementsFlagModelImpl announcementsFlagModelImpl = this;

		announcementsFlagModelImpl._originalUserId = announcementsFlagModelImpl._userId;

		announcementsFlagModelImpl._setOriginalUserId = false;

		announcementsFlagModelImpl._originalEntryId = announcementsFlagModelImpl._entryId;

		announcementsFlagModelImpl._setOriginalEntryId = false;

		announcementsFlagModelImpl._originalValue = announcementsFlagModelImpl._value;

		announcementsFlagModelImpl._setOriginalValue = false;

		announcementsFlagModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<AnnouncementsFlag> toCacheModel() {
		AnnouncementsFlagCacheModel announcementsFlagCacheModel = new AnnouncementsFlagCacheModel();

		announcementsFlagCacheModel.flagId = getFlagId();

		announcementsFlagCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			announcementsFlagCacheModel.createDate = createDate.getTime();
		}
		else {
			announcementsFlagCacheModel.createDate = Long.MIN_VALUE;
		}

		announcementsFlagCacheModel.entryId = getEntryId();

		announcementsFlagCacheModel.value = getValue();

		return announcementsFlagCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{flagId=");
		sb.append(getFlagId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", entryId=");
		sb.append(getEntryId());
		sb.append(", value=");
		sb.append(getValue());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portlet.announcements.model.AnnouncementsFlag");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>flagId</column-name><column-value><![CDATA[");
		sb.append(getFlagId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>entryId</column-name><column-value><![CDATA[");
		sb.append(getEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>value</column-name><column-value><![CDATA[");
		sb.append(getValue());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = AnnouncementsFlag.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			AnnouncementsFlag.class
		};
	private long _flagId;
	private long _userId;
	private String _userUuid;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private Date _createDate;
	private long _entryId;
	private long _originalEntryId;
	private boolean _setOriginalEntryId;
	private int _value;
	private int _originalValue;
	private boolean _setOriginalValue;
	private long _columnBitmask;
	private AnnouncementsFlag _escapedModelProxy;
}