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

package com.liferay.taglib.portletext;

import com.liferay.portal.kernel.servlet.taglib.FileAvailabilityUtil;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.taglib.ui.IconTag;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class IconPrintTag extends IconTag {

	@Override
	protected String getPage() {
		if (FileAvailabilityUtil.isAvailable(getServletContext(), _PAGE)) {
			return _PAGE;
		}

		PortletDisplay portletDisplay =
			(PortletDisplay)pageContext.getAttribute("portletDisplay");

		if (!portletDisplay.isShowPrintIcon()) {
			return null;
		}

		setCssClass("portlet-print portlet-print-icon");
		setImage("../portlet/print");
		setMessage("print");

		String onClick = "location.href = '".concat(
			portletDisplay.getURLPrint()).concat("'; return false;");

		setOnClick(onClick);

		setTarget("_blank");
		setToolTip(false);
		setUrl(portletDisplay.getURLPrint());

		return super.getPage();
	}

	private static final String _PAGE =
		"/html/taglib/portlet/icon_print/page.jsp";

}