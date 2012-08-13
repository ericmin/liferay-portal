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

package com.liferay.taglib.portlet;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Brian Wing Shun Chan
 */
public class RenderURLParamsTag extends TagSupport {

	public static String doTag(String varImpl, PageContext pageContext)
		throws Exception {

		PortletURL portletURL = (PortletURL)pageContext.getAttribute(varImpl);

		String params = StringPool.BLANK;

		if (portletURL != null) {
			params = _toParamsString(portletURL, pageContext);

			JspWriter jspWriter = pageContext.getOut();

			jspWriter.write(params);
		}

		return params;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			doTag(_varImpl, pageContext);

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	public void setVarImpl(String varImpl) {
		_varImpl = varImpl;
	}

	private static String _toParamsString(
			PortletURL portletURL, PageContext pageContext)
		throws Exception {

		StringBundler sb = new StringBundler();

		String url = portletURL.toString();

		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		if (ParamUtil.getBoolean(request, "wsrp")) {
			int x = url.indexOf("/wsrp_rewrite");

			url = url.substring(0, x);
		}

		String queryString = HttpUtil.getQueryString(url);

		String[] parameters = StringUtil.split(queryString, CharPool.AMPERSAND);

		for (String parameter : parameters) {
			if (parameter.length() > 0) {
				String[] kvp = StringUtil.split(parameter, CharPool.EQUAL);

				if ((kvp != null) && (kvp.length > 0)) {
					String key = kvp[0];
					String value = StringPool.BLANK;

					if (kvp.length > 1) {
						value = kvp[1];
					}

					value = HttpUtil.decodeURL(value);

					sb.append("<input name=\"");
					sb.append(key);
					sb.append("\" type=\"hidden\" value=\"");
					sb.append(HtmlUtil.escapeAttribute(value));
					sb.append("\" />");
				}
			}
		}

		return sb.toString();
	}

	private String _varImpl;

}