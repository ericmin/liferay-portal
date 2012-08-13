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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.LayoutBranch;

import java.io.Serializable;

/**
 * The cache model class for representing LayoutBranch in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutBranch
 * @generated
 */
public class LayoutBranchCacheModel implements CacheModel<LayoutBranch>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{LayoutBranchId=");
		sb.append(LayoutBranchId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", layoutSetBranchId=");
		sb.append(layoutSetBranchId);
		sb.append(", plid=");
		sb.append(plid);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", master=");
		sb.append(master);
		sb.append("}");

		return sb.toString();
	}

	public LayoutBranch toEntityModel() {
		LayoutBranchImpl layoutBranchImpl = new LayoutBranchImpl();

		layoutBranchImpl.setLayoutBranchId(LayoutBranchId);
		layoutBranchImpl.setGroupId(groupId);
		layoutBranchImpl.setCompanyId(companyId);
		layoutBranchImpl.setUserId(userId);

		if (userName == null) {
			layoutBranchImpl.setUserName(StringPool.BLANK);
		}
		else {
			layoutBranchImpl.setUserName(userName);
		}

		layoutBranchImpl.setLayoutSetBranchId(layoutSetBranchId);
		layoutBranchImpl.setPlid(plid);

		if (name == null) {
			layoutBranchImpl.setName(StringPool.BLANK);
		}
		else {
			layoutBranchImpl.setName(name);
		}

		if (description == null) {
			layoutBranchImpl.setDescription(StringPool.BLANK);
		}
		else {
			layoutBranchImpl.setDescription(description);
		}

		layoutBranchImpl.setMaster(master);

		layoutBranchImpl.resetOriginalValues();

		return layoutBranchImpl;
	}

	public long LayoutBranchId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long layoutSetBranchId;
	public long plid;
	public String name;
	public String description;
	public boolean master;
}