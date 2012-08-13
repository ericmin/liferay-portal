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

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public interface TransformerListener {

	public String getLanguageId();

	public Map<String, String> getTokens();

	public boolean isTemplateDriven();

	public String onOutput(String s);

	public String onScript(String s);

	public String onXml(String s);

	public void setLanguageId(String languageId);

	public void setTemplateDriven(boolean templateDriven);

	public void setTokens(Map<String, String> tokens);

}