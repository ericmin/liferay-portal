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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.LayoutBranch;
import com.liferay.portal.service.LayoutBranchLocalServiceUtil;

/**
 * The extended model base implementation for the LayoutBranch service. Represents a row in the &quot;LayoutBranch&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutBranchImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutBranchImpl
 * @see com.liferay.portal.model.LayoutBranch
 * @generated
 */
public abstract class LayoutBranchBaseImpl extends LayoutBranchModelImpl
	implements LayoutBranch {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout branch model instance should use the {@link LayoutBranch} interface instead.
	 */
	public void persist() throws SystemException {
		if (this.isNew()) {
			LayoutBranchLocalServiceUtil.addLayoutBranch(this);
		}
		else {
			LayoutBranchLocalServiceUtil.updateLayoutBranch(this);
		}
	}
}