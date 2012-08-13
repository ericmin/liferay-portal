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

package com.liferay.portlet.expando.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

/**
 * The extended model base implementation for the ExpandoValue service. Represents a row in the &quot;ExpandoValue&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ExpandoValueImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoValueImpl
 * @see com.liferay.portlet.expando.model.ExpandoValue
 * @generated
 */
public abstract class ExpandoValueBaseImpl extends ExpandoValueModelImpl
	implements ExpandoValue {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a expando value model instance should use the {@link ExpandoValue} interface instead.
	 */
	public void persist() throws SystemException {
		if (this.isNew()) {
			ExpandoValueLocalServiceUtil.addExpandoValue(this);
		}
		else {
			ExpandoValueLocalServiceUtil.updateExpandoValue(this);
		}
	}
}