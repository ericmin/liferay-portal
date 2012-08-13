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

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public class ChannelHubAppShutdownAction extends SimpleAction {

	@Override
	public void run(String[] ids) {
		try {
			long companyId = GetterUtil.getLong(ids[0]);

			if (_log.isDebugEnabled()) {
				_log.debug("Destroying channel hub " + companyId);
			}

			ChannelHubManagerUtil.destroyChannelHub(companyId);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ChannelHubAppShutdownAction.class);

}