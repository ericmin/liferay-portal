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

package com.liferay.portal.kernel.scripting;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Map;
import java.util.Set;

import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Alberto Montero
 * @author Brian Wing Shun Chan
 */
public class ScriptingUtil {

	public static void clearCache(String language) throws ScriptingException {
		getScripting().clearCache(language);
	}

	public static Map<String, Object> eval(
			Set<String> allowedClasses, Map<String, Object> inputObjects,
			Set<String> outputNames, String language, String script,
			ClassLoader... classLoaders)
		throws ScriptingException {

		return getScripting().eval(
			allowedClasses, inputObjects, outputNames, language, script,
			classLoaders);
	}

	public static void exec(
			Set<String> allowedClasses, Map<String, Object> inputObjects,
			String language, String script, ClassLoader... classLoaders)
		throws ScriptingException {

		getScripting().exec(
			allowedClasses, inputObjects, language, script, classLoaders);
	}

	public static Map<String, Object> getPortletObjects(
		PortletConfig portletConfig, PortletContext portletContext,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return getScripting().getPortletObjects(
			portletConfig, portletContext, portletRequest, portletResponse);
	}

	public static Scripting getScripting() {
		PortalRuntimePermission.checkGetBeanProperty(ScriptingUtil.class);

		return _scripting;
	}

	public static Set<String> getSupportedLanguages() {
		return getScripting().getSupportedLanguages();
	}

	public void setScripting(Scripting scripting) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_scripting = scripting;
	}

	private static Scripting _scripting;

}