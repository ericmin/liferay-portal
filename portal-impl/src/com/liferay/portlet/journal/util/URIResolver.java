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

package com.liferay.portlet.journal.util;

import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;

import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

/**
 * @author Brian Wing Shun Chan
 */
public class URIResolver implements javax.xml.transform.URIResolver {

	public URIResolver(Map<String, String> tokens, String languageId) {
		_tokens = tokens;
		_languageId = languageId;
	}

	public Source resolve(String href, String base) {
		try {
			String content = null;

			int templatePathIndex = href.indexOf(_GET_TEMPLATE_PATH);

			if (templatePathIndex >= 0) {
				int templateIdIndex =
					templatePathIndex + _GET_TEMPLATE_PATH.length();

				long groupId = GetterUtil.getLong(_tokens.get("group_id"));
				String templateId = href.substring(templateIdIndex);

				content = JournalUtil.getTemplateScript(
					groupId, templateId, _tokens, _languageId);
			}
			else {
				content = HttpUtil.URLtoString(href);
			}

			return new StreamSource(new UnsyncStringReader(content));
		}
		catch (Exception e) {
			_log.error(href + " does not reference a valid template");

			return null;
		}
	}

	private static final String _GET_TEMPLATE_PATH =
		"/c/journal/get_template?template_id=";

	private static Log _log = LogFactoryUtil.getLog(URIResolver.class);

	private String _languageId;
	private Map<String, String> _tokens;

}