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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.ResourceModel;
import com.liferay.portal.model.ResourceSoap;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Resource service. Represents a row in the &quot;Resource_&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portal.model.ResourceModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ResourceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceImpl
 * @see com.liferay.portal.model.Resource
 * @see com.liferay.portal.model.ResourceModel
 * @generated
 */
@JSON(strict = true)
public class ResourceModelImpl extends BaseModelImpl<Resource>
	implements ResourceModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a resource model instance should use the {@link com.liferay.portal.model.Resource} interface instead.
	 */
	public static final String TABLE_NAME = "Resource_";
	public static final Object[][] TABLE_COLUMNS = {
			{ "resourceId", Types.BIGINT },
			{ "codeId", Types.BIGINT },
			{ "primKey", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table Resource_ (resourceId LONG not null primary key,codeId LONG,primKey VARCHAR(255) null)";
	public static final String TABLE_SQL_DROP = "drop table Resource_";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.model.Resource"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.Resource"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.model.Resource"),
			true);
	public static long CODEID_COLUMN_BITMASK = 1L;
	public static long PRIMKEY_COLUMN_BITMASK = 2L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Resource toModel(ResourceSoap soapModel) {
		Resource model = new ResourceImpl();

		model.setResourceId(soapModel.getResourceId());
		model.setCodeId(soapModel.getCodeId());
		model.setPrimKey(soapModel.getPrimKey());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Resource> toModels(ResourceSoap[] soapModels) {
		List<Resource> models = new ArrayList<Resource>(soapModels.length);

		for (ResourceSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.Resource"));

	public ResourceModelImpl() {
	}

	public long getPrimaryKey() {
		return _resourceId;
	}

	public void setPrimaryKey(long primaryKey) {
		setResourceId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_resourceId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return Resource.class;
	}

	public String getModelClassName() {
		return Resource.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("resourceId", getResourceId());
		attributes.put("codeId", getCodeId());
		attributes.put("primKey", getPrimKey());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long resourceId = (Long)attributes.get("resourceId");

		if (resourceId != null) {
			setResourceId(resourceId);
		}

		Long codeId = (Long)attributes.get("codeId");

		if (codeId != null) {
			setCodeId(codeId);
		}

		String primKey = (String)attributes.get("primKey");

		if (primKey != null) {
			setPrimKey(primKey);
		}
	}

	@JSON
	public long getResourceId() {
		return _resourceId;
	}

	public void setResourceId(long resourceId) {
		_resourceId = resourceId;
	}

	@JSON
	public long getCodeId() {
		return _codeId;
	}

	public void setCodeId(long codeId) {
		_columnBitmask |= CODEID_COLUMN_BITMASK;

		if (!_setOriginalCodeId) {
			_setOriginalCodeId = true;

			_originalCodeId = _codeId;
		}

		_codeId = codeId;
	}

	public long getOriginalCodeId() {
		return _originalCodeId;
	}

	@JSON
	public String getPrimKey() {
		if (_primKey == null) {
			return StringPool.BLANK;
		}
		else {
			return _primKey;
		}
	}

	public void setPrimKey(String primKey) {
		_columnBitmask |= PRIMKEY_COLUMN_BITMASK;

		if (_originalPrimKey == null) {
			_originalPrimKey = _primKey;
		}

		_primKey = primKey;
	}

	public String getOriginalPrimKey() {
		return GetterUtil.getString(_originalPrimKey);
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public Resource toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (Resource)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Resource.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Object clone() {
		ResourceImpl resourceImpl = new ResourceImpl();

		resourceImpl.setResourceId(getResourceId());
		resourceImpl.setCodeId(getCodeId());
		resourceImpl.setPrimKey(getPrimKey());

		resourceImpl.resetOriginalValues();

		return resourceImpl;
	}

	public int compareTo(Resource resource) {
		long primaryKey = resource.getPrimaryKey();

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

		Resource resource = null;

		try {
			resource = (Resource)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = resource.getPrimaryKey();

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
		ResourceModelImpl resourceModelImpl = this;

		resourceModelImpl._originalCodeId = resourceModelImpl._codeId;

		resourceModelImpl._setOriginalCodeId = false;

		resourceModelImpl._originalPrimKey = resourceModelImpl._primKey;

		resourceModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Resource> toCacheModel() {
		ResourceCacheModel resourceCacheModel = new ResourceCacheModel();

		resourceCacheModel.resourceId = getResourceId();

		resourceCacheModel.codeId = getCodeId();

		resourceCacheModel.primKey = getPrimKey();

		String primKey = resourceCacheModel.primKey;

		if ((primKey != null) && (primKey.length() == 0)) {
			resourceCacheModel.primKey = null;
		}

		return resourceCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{resourceId=");
		sb.append(getResourceId());
		sb.append(", codeId=");
		sb.append(getCodeId());
		sb.append(", primKey=");
		sb.append(getPrimKey());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.model.Resource");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>resourceId</column-name><column-value><![CDATA[");
		sb.append(getResourceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>codeId</column-name><column-value><![CDATA[");
		sb.append(getCodeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>primKey</column-name><column-value><![CDATA[");
		sb.append(getPrimKey());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Resource.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			Resource.class
		};
	private long _resourceId;
	private long _codeId;
	private long _originalCodeId;
	private boolean _setOriginalCodeId;
	private String _primKey;
	private String _originalPrimKey;
	private long _columnBitmask;
	private Resource _escapedModelProxy;
}