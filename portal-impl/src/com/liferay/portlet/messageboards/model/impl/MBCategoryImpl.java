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

package com.liferay.portlet.messageboards.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class MBCategoryImpl extends MBCategoryBaseImpl {

	public MBCategoryImpl() {
	}

	public List<Long> getAncestorCategoryIds()
		throws PortalException, SystemException {

		List<Long> ancestorCategoryIds = new ArrayList<Long>();

		MBCategory category = this;

		while (true) {
			if (!category.isRoot()) {
				category = MBCategoryLocalServiceUtil.getCategory(
					category.getParentCategoryId());

				ancestorCategoryIds.add(category.getCategoryId());
			}
			else {
				break;
			}
		}

		return ancestorCategoryIds;
	}

	public List<MBCategory> getAncestors()
		throws PortalException, SystemException {

		List<MBCategory> ancestors = new ArrayList<MBCategory>();

		MBCategory category = this;

		while (true) {
			if (!category.isRoot()) {
				category = MBCategoryLocalServiceUtil.getCategory(
					category.getParentCategoryId());

				ancestors.add(category);
			}
			else {
				break;
			}
		}

		return ancestors;
	}

	public boolean isRoot() {
		if (getParentCategoryId() ==
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {

			return true;
		}
		else {
			return false;
		}
	}

}