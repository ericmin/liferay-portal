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

package com.liferay.portal.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the VirtualHost service. Represents a row in the &quot;VirtualHost&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.VirtualHostModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.VirtualHostImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VirtualHost
 * @see com.liferay.portal.model.impl.VirtualHostImpl
 * @see com.liferay.portal.model.impl.VirtualHostModelImpl
 * @generated
 */
public interface VirtualHostModel extends BaseModel<VirtualHost> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a virtual host model instance should use the {@link VirtualHost} interface instead.
	 */

	/**
	 * Returns the primary key of this virtual host.
	 *
	 * @return the primary key of this virtual host
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this virtual host.
	 *
	 * @param primaryKey the primary key of this virtual host
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the virtual host ID of this virtual host.
	 *
	 * @return the virtual host ID of this virtual host
	 */
	public long getVirtualHostId();

	/**
	 * Sets the virtual host ID of this virtual host.
	 *
	 * @param virtualHostId the virtual host ID of this virtual host
	 */
	public void setVirtualHostId(long virtualHostId);

	/**
	 * Returns the company ID of this virtual host.
	 *
	 * @return the company ID of this virtual host
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this virtual host.
	 *
	 * @param companyId the company ID of this virtual host
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the layout set ID of this virtual host.
	 *
	 * @return the layout set ID of this virtual host
	 */
	public long getLayoutSetId();

	/**
	 * Sets the layout set ID of this virtual host.
	 *
	 * @param layoutSetId the layout set ID of this virtual host
	 */
	public void setLayoutSetId(long layoutSetId);

	/**
	 * Returns the hostname of this virtual host.
	 *
	 * @return the hostname of this virtual host
	 */
	@AutoEscape
	public String getHostname();

	/**
	 * Sets the hostname of this virtual host.
	 *
	 * @param hostname the hostname of this virtual host
	 */
	public void setHostname(String hostname);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(VirtualHost virtualHost);

	public int hashCode();

	public CacheModel<VirtualHost> toCacheModel();

	public VirtualHost toEscapedModel();

	public String toString();

	public String toXmlString();
}