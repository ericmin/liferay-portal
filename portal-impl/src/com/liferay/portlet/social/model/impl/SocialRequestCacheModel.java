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

package com.liferay.portlet.social.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.social.model.SocialRequest;

import java.io.Serializable;

/**
 * The cache model class for representing SocialRequest in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SocialRequest
 * @generated
 */
public class SocialRequestCacheModel implements CacheModel<SocialRequest>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", requestId=");
		sb.append(requestId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", extraData=");
		sb.append(extraData);
		sb.append(", receiverUserId=");
		sb.append(receiverUserId);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	public SocialRequest toEntityModel() {
		SocialRequestImpl socialRequestImpl = new SocialRequestImpl();

		if (uuid == null) {
			socialRequestImpl.setUuid(StringPool.BLANK);
		}
		else {
			socialRequestImpl.setUuid(uuid);
		}

		socialRequestImpl.setRequestId(requestId);
		socialRequestImpl.setGroupId(groupId);
		socialRequestImpl.setCompanyId(companyId);
		socialRequestImpl.setUserId(userId);
		socialRequestImpl.setCreateDate(createDate);
		socialRequestImpl.setModifiedDate(modifiedDate);
		socialRequestImpl.setClassNameId(classNameId);
		socialRequestImpl.setClassPK(classPK);
		socialRequestImpl.setType(type);

		if (extraData == null) {
			socialRequestImpl.setExtraData(StringPool.BLANK);
		}
		else {
			socialRequestImpl.setExtraData(extraData);
		}

		socialRequestImpl.setReceiverUserId(receiverUserId);
		socialRequestImpl.setStatus(status);

		socialRequestImpl.resetOriginalValues();

		return socialRequestImpl;
	}

	public String uuid;
	public long requestId;
	public long groupId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public int type;
	public String extraData;
	public long receiverUserId;
	public int status;
}