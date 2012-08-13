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

package com.liferay.portlet.polls.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class PollsChoiceFinderUtil {
	public static com.liferay.portlet.polls.model.PollsChoice fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().fetchByUUID_G(uuid, groupId);
	}

	public static com.liferay.portlet.polls.model.PollsChoice findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.polls.NoSuchChoiceException {
		return getFinder().findByUUID_G(uuid, groupId);
	}

	public static PollsChoiceFinder getFinder() {
		if (_finder == null) {
			_finder = (PollsChoiceFinder)PortalBeanLocatorUtil.locate(PollsChoiceFinder.class.getName());

			ReferenceRegistry.registerReference(PollsChoiceFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(PollsChoiceFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(PollsChoiceFinderUtil.class,
			"_finder");
	}

	private static PollsChoiceFinder _finder;
}