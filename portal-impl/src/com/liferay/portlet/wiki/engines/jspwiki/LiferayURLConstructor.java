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

package com.liferay.portlet.wiki.engines.jspwiki;

import com.ecyrd.jspwiki.WikiContext;
import com.ecyrd.jspwiki.url.URLConstructor;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jorge Ferrer
 */
public class LiferayURLConstructor implements URLConstructor {

	public String getForwardPage(HttpServletRequest request) {
		return "Wiki.jsp";
	}

	public void initialize(
		com.ecyrd.jspwiki.WikiEngine engine, Properties props) {
	}

	public String makeURL(
		String context, String name, boolean absolute, String parameters) {

		if (Validator.isNotNull(parameters)) {
			if (context.equals(WikiContext.ATTACH)) {
				parameters = StringPool.QUESTION + parameters;
			}
			else if (context.equals(WikiContext.NONE)) {
				if (name.indexOf(CharPool.QUESTION) != -1) {
					parameters = "&amp;" + parameters;
				}
				else {
					parameters = StringPool.QUESTION + parameters;
				}
			}
			else {
				parameters = "&amp;" + parameters;
			}
		}
		else {
			parameters = StringPool.BLANK;
		}

		String path;

		if (context.equals(WikiContext.EDIT)) {
			path =
				"[$BEGIN_PAGE_TITLE_EDIT$]" +
					JSPWikiEngine.decodeJSPWikiName(name) +
						"[$END_PAGE_TITLE_EDIT$]";
		}
		else if (context.equals(WikiContext.VIEW)) {
			path =
				"[$BEGIN_PAGE_TITLE$]" +
					_escapeName(JSPWikiEngine.decodeJSPWikiName(name)) +
						"[$END_PAGE_TITLE$]";
		}
		else if (context.equals(WikiContext.ATTACH)) {
			if (name.indexOf(CharPool.SLASH) == -1) {
				path =
					"[$ATTACHMENT_URL_PREFIX$][$WIKI_PAGE_NAME$]/" +
						HttpUtil.encodeURL(
							JSPWikiEngine.decodeJSPWikiName(name));
			}
			else {
				path =
					"[$ATTACHMENT_URL_PREFIX$]" +
						HttpUtil.encodeURL(
							JSPWikiEngine.decodeJSPWikiName(name));
			}
		}
		else {
			path = JSPWikiEngine.decodeJSPWikiName(name);
		}

		return path + parameters;
	}

	public String parsePage(
		String context, HttpServletRequest request, String encoding) {

		return "Wiki.jsp";
	}

	private static String _escapeName(String name) {
		return StringUtil.replace(name, _UNESCAPED_CHARS, _ESCAPED_CHARS);
	}

	private static final String[] _ESCAPED_CHARS = new String[] {
		"<PLUS>", "<QUESTION>", "<SLASH>"
	};

	private static final String[] _UNESCAPED_CHARS = new String[] {
		StringPool.PLUS, StringPool.QUESTION, StringPool.SLASH
	};

}