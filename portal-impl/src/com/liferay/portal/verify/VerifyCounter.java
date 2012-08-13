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

package com.liferay.portal.verify;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.model.Permission;
import com.liferay.portal.model.Resource;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;

/**
 * @author Alexander Chow
 * @author Brian Wing Shun Chan
 */
public class VerifyCounter extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {

		// Resource

		long latestResourceId = ResourceLocalServiceUtil.getLatestResourceId();

		long counterResourceId = CounterLocalServiceUtil.increment(
			Resource.class.getName());

		if (latestResourceId > (counterResourceId - 1)) {
			CounterLocalServiceUtil.reset(
				Resource.class.getName(), latestResourceId);
		}

		// Permission

		long latestPermissionId =
			PermissionLocalServiceUtil.getLatestPermissionId();

		long counterPermissionId = CounterLocalServiceUtil.increment(
			Permission.class.getName());

		if (latestPermissionId > (counterPermissionId - 1)) {
			CounterLocalServiceUtil.reset(
				Permission.class.getName(), latestPermissionId);
		}
	}

}