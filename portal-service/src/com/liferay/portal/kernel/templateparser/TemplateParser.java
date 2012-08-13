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

package com.liferay.portal.kernel.templateparser;

import com.liferay.portal.theme.ThemeDisplay;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public interface TemplateParser {

	public String getLanguageId();

	public String getScript();

	public ThemeDisplay getThemeDisplay();

	public Map<String, String> getTokens();

	public String getViewMode();

	public String getXML();

	public void setLanguageId(String languageId);

	public void setScript(String script);

	public void setThemeDisplay(ThemeDisplay themeDisplay);

	public void setTokens(Map<String, String> tokens);

	public void setViewMode(String viewMode);

	public void setXML(String xml);

	public String transform() throws TransformException;

}