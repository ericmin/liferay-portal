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

package com.liferay.portal.servlet.filters.validhtml;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.servlet.StringServletResponse;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.servlet.filters.BasePortalFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class ValidHtmlFilter extends BasePortalFilter {

	public static final String SKIP_FILTER =
		ValidHtmlFilter.class.getName() + "SKIP_FILTER";

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		if (isAlreadyFiltered(request)) {
			return false;
		}
		else {
			return true;
		}
	}

	protected String getContent(HttpServletRequest request, String content) {
		content = StringUtil.replaceLast(
			content, _CLOSE_BODY, StringPool.BLANK);
		content = StringUtil.replaceLast(
			content, _CLOSE_HTML, _CLOSE_BODY + _CLOSE_HTML);

		return content;
	}

	protected boolean isAlreadyFiltered(HttpServletRequest request) {
		if (request.getAttribute(SKIP_FILTER) != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		request.setAttribute(SKIP_FILTER, Boolean.TRUE);

		if (_log.isDebugEnabled()) {
			String completeURL = HttpUtil.getCompleteURL(request);

			_log.debug("Ensuring valid HTML " + completeURL);
		}

		StringServletResponse stringServerResponse = new StringServletResponse(
			response);

		processFilter(
			ValidHtmlFilter.class, request, stringServerResponse, filterChain);

		String contentType = response.getContentType();

		if ((contentType != null) &&
			contentType.startsWith(ContentTypes.TEXT_HTML)) {

			String content = getContent(
				request, stringServerResponse.getString());

			ServletResponseUtil.write(response, content);
		}
		else {
			ServletResponseUtil.write(response, stringServerResponse);
		}
	}

	private static final String _CLOSE_BODY = "</body>";

	private static final String _CLOSE_HTML = "</html>";

	private static Log _log = LogFactoryUtil.getLog(ValidHtmlFilter.class);

}