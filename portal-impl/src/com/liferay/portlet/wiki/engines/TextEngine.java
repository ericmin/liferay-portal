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

package com.liferay.portlet.wiki.engines;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.wiki.model.WikiPage;

import java.util.Collections;
import java.util.Map;

import javax.portlet.PortletURL;

/**
 * @author Jorge Ferrer
 */
public class TextEngine implements WikiEngine {

	public String convert(
		WikiPage page, PortletURL viewPageURL, PortletURL editPageURL,
		String attachmentURLPrefix) {

		if (page.getContent() == null) {
			return StringPool.BLANK;
		}
		else {
			return "<pre>" + page.getContent() + "</pre>";
		}
	}

	public Map<String, Boolean> getOutgoingLinks(WikiPage page) {
		return Collections.emptyMap();
	}

	public void setInterWikiConfiguration(String interWikiConfiguration) {
	}

	public void setMainConfiguration(String mainConfiguration) {
	}

	public boolean validate(long nodeId, String newContent) {
		return true;
	}

}