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

package com.liferay.portal.theme;

import com.liferay.portal.kernel.templateparser.TemplateContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class RequestVars {

	public RequestVars(
		HttpServletRequest request, ThemeDisplay themeDisplay,
		long ancestorPlid, long ancestorLayoutId,
		TemplateContext templateContext) {

		_request = request;
		_themeDisplay = themeDisplay;
		_ancestorPlid = ancestorPlid;
		_ancestorLayoutId = ancestorLayoutId;
		_templateContext = templateContext;
	}

	public long getAncestorLayoutId() {
		return _ancestorLayoutId;
	}

	public long getAncestorPlid() {
		return _ancestorPlid;
	}

	public HttpServletRequest getRequest() {
		return _request;
	}

	public TemplateContext getTemplateContext() {
		return _templateContext;
	}

	public ThemeDisplay getThemeDisplay() {
		return _themeDisplay;
	}

	private long _ancestorLayoutId;
	private long _ancestorPlid;
	private HttpServletRequest _request;
	private TemplateContext _templateContext;
	private ThemeDisplay _themeDisplay;

}