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

package com.liferay.portlet.asset.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portlet.asset.model.AssetTagProperty;
import com.liferay.portlet.asset.service.AssetTagPropertyLocalServiceUtil;

/**
 * The extended model base implementation for the AssetTagProperty service. Represents a row in the &quot;AssetTagProperty&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AssetTagPropertyImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetTagPropertyImpl
 * @see com.liferay.portlet.asset.model.AssetTagProperty
 * @generated
 */
public abstract class AssetTagPropertyBaseImpl extends AssetTagPropertyModelImpl
	implements AssetTagProperty {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a asset tag property model instance should use the {@link AssetTagProperty} interface instead.
	 */
	public void persist() throws SystemException {
		if (this.isNew()) {
			AssetTagPropertyLocalServiceUtil.addAssetTagProperty(this);
		}
		else {
			AssetTagPropertyLocalServiceUtil.updateAssetTagProperty(this);
		}
	}
}