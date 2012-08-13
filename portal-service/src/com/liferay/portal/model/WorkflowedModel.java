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

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public interface WorkflowedModel {

	public int getStatus();

	public long getStatusByUserId();

	public String getStatusByUserName();

	public Date getStatusDate();

	public boolean isApproved();

	public boolean isDraft();

	public boolean isExpired();

	public boolean isPending();

	public void setStatus(int status);

	public void setStatusByUserId(long statusByUserId);

	public void setStatusByUserName(String statusByUserName);

	public void setStatusDate(Date statusDate);

}