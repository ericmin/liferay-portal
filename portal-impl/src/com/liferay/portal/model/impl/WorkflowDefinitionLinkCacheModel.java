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
import com.liferay.portal.model.WorkflowDefinitionLink;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing WorkflowDefinitionLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowDefinitionLink
 * @generated
 */
public class WorkflowDefinitionLinkCacheModel implements CacheModel<WorkflowDefinitionLink>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{workflowDefinitionLinkId=");
		sb.append(workflowDefinitionLinkId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", typePK=");
		sb.append(typePK);
		sb.append(", workflowDefinitionName=");
		sb.append(workflowDefinitionName);
		sb.append(", workflowDefinitionVersion=");
		sb.append(workflowDefinitionVersion);
		sb.append("}");

		return sb.toString();
	}

	public WorkflowDefinitionLink toEntityModel() {
		WorkflowDefinitionLinkImpl workflowDefinitionLinkImpl = new WorkflowDefinitionLinkImpl();

		workflowDefinitionLinkImpl.setWorkflowDefinitionLinkId(workflowDefinitionLinkId);
		workflowDefinitionLinkImpl.setGroupId(groupId);
		workflowDefinitionLinkImpl.setCompanyId(companyId);
		workflowDefinitionLinkImpl.setUserId(userId);

		if (userName == null) {
			workflowDefinitionLinkImpl.setUserName(StringPool.BLANK);
		}
		else {
			workflowDefinitionLinkImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			workflowDefinitionLinkImpl.setCreateDate(null);
		}
		else {
			workflowDefinitionLinkImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			workflowDefinitionLinkImpl.setModifiedDate(null);
		}
		else {
			workflowDefinitionLinkImpl.setModifiedDate(new Date(modifiedDate));
		}

		workflowDefinitionLinkImpl.setClassNameId(classNameId);
		workflowDefinitionLinkImpl.setClassPK(classPK);
		workflowDefinitionLinkImpl.setTypePK(typePK);

		if (workflowDefinitionName == null) {
			workflowDefinitionLinkImpl.setWorkflowDefinitionName(StringPool.BLANK);
		}
		else {
			workflowDefinitionLinkImpl.setWorkflowDefinitionName(workflowDefinitionName);
		}

		workflowDefinitionLinkImpl.setWorkflowDefinitionVersion(workflowDefinitionVersion);

		workflowDefinitionLinkImpl.resetOriginalValues();

		return workflowDefinitionLinkImpl;
	}

	public long workflowDefinitionLinkId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public long typePK;
	public String workflowDefinitionName;
	public int workflowDefinitionVersion;
}