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

package com.liferay.portlet.journalcontent;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletLayoutListener;
import com.liferay.portal.kernel.portlet.PortletLayoutListenerException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.journal.NoSuchContentSearchException;
import com.liferay.portlet.journal.service.JournalContentSearchLocalServiceUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 */
public class JournalContentPortletLayoutListener
	implements PortletLayoutListener {

	public void onAddToLayout(String portletId, long plid)
		throws PortletLayoutListenerException {

		if (_log.isDebugEnabled()) {
			_log.debug("Add " + portletId + " to layout " + plid);
		}
	}

	public void onMoveInLayout(String portletId, long plid)
		throws PortletLayoutListenerException {

		if (_log.isDebugEnabled()) {
			_log.debug("Move " + portletId + " from in " + plid);
		}
	}

	public void onRemoveFromLayout(String portletId, long plid)
		throws PortletLayoutListenerException {

		if (_log.isDebugEnabled()) {
			_log.debug("Remove " + portletId + " from layout " + plid);
		}

		try {
			deleteContentSearch(portletId, plid);
		}
		catch (Exception e) {
			throw new PortletLayoutListenerException(e);
		}
	}

	protected void deleteContentSearch(String portletId, long plid)
		throws Exception {

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				layout, portletId, StringPool.BLANK);

		String articleId = preferences.getValue("articleId", null);

		if (Validator.isNull(articleId)) {
			return;
		}

		try {
			JournalContentSearchLocalServiceUtil.deleteArticleContentSearch(
				layout.getGroupId(), layout.isPrivateLayout(),
				layout.getLayoutId(), portletId, articleId);
		}
		catch (NoSuchContentSearchException nscse) {
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		JournalContentPortletLayoutListener.class);

}