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

import com.liferay.portlet.wiki.PageContentException;
import com.liferay.portlet.wiki.model.WikiPage;

import java.util.Map;

import javax.portlet.PortletURL;

/**
 * @author Jorge Ferrer
 */
public interface WikiEngine {

	/**
	 * Convert the content of the given page to HTML using the view and edit
	 * URLs to build links.
	 *
	 * @return HTML string
	 */
	public String convert(
			WikiPage page, PortletURL viewPageURL, PortletURL editPageURL,
			String attachmentURLPrefix)
		throws PageContentException;

	/**
	 * Get a map with the links included in the given page. The key of each map
	 * entry is the title of the linked page. The value is a Boolean object that
	 * indicates if the linked page exists or not.
	 *
	 * @return a map of links
	 */
	public Map<String, Boolean> getOutgoingLinks(WikiPage page)
		throws PageContentException;

	/**
	 * Set the configuraton to support quick links to other wikis. The format of
	 * the configuration is specific to the wiki engine.
	 */
	public void setInterWikiConfiguration(String interWikiConfiguration);

	/**
	 * Set the main wiki configuraiton as a String. The format of the
	 * configuration is specific to the wiki engine.
	 */
	public void setMainConfiguration(String mainConfiguration);

	/**
	 * Validate the content of a wiki page for this engine.
	 *
	 * @return <code>true</code> if the content is valid
	 */
	public boolean validate(long nodeId, String content);

}