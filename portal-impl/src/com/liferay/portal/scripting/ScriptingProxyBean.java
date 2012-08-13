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

package com.liferay.portal.scripting;

import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.kernel.scripting.Scripting;
import com.liferay.portal.kernel.scripting.ScriptingExecutor;

import java.util.Map;
import java.util.Set;

import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Michael C. Han
 */
public class ScriptingProxyBean extends BaseProxyBean implements Scripting {

	public void addScriptionExecutor(
		String language, ScriptingExecutor scriptingExecutor) {

		throw new UnsupportedOperationException();
	}

	public void clearCache(String language) {
		throw new UnsupportedOperationException();
	}

	public Map<String, Object> eval(
		Set<String> allowedClasses, Map<String, Object> inputObjects,
		Set<String> outputNames, String language, String script,
		ClassLoader... classLoaders) {

		throw new UnsupportedOperationException();
	}

	public void exec(
		Set<String> allowedClasses, Map<String, Object> inputObjects,
		String language, String script, ClassLoader... classLoaders) {

		throw new UnsupportedOperationException();
	}

	public Map<String, Object> getPortletObjects(
		PortletConfig portletConfig, PortletContext portletContext,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		throw new UnsupportedOperationException();
	}

	public Set<String> getSupportedLanguages() {
		throw new UnsupportedOperationException();
	}

	public void setScriptingExecutors(
		Map<String, ScriptingExecutor> scriptingExecutors) {

		throw new UnsupportedOperationException();
	}

}