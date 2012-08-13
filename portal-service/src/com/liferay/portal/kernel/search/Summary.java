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

package com.liferay.portal.kernel.search;

import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public class Summary {

	public Summary(String title, String content, PortletURL portletURL) {
		_title = title;
		_content = content;
		_portletURL = portletURL;
	}

	public String getContent() {
		return _content;
	}

	public PortletURL getPortletURL() {
		return _portletURL;
	}

	public String getTitle() {
		return _title;
	}

	public void setContent(String content) {
		_content = content;
	}

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	public void setTitle(String title) {
		_title = title;
	}

	private String _content;
	private PortletURL _portletURL;
	private String _title;

}