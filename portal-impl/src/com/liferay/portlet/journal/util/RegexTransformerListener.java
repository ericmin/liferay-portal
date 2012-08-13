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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.templateparser.BaseTransformerListener;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brian Wing Shun Chan
 */
public class RegexTransformerListener extends BaseTransformerListener {

	@Override
	public String onOutput(String s) {
		if (_log.isDebugEnabled()) {
			_log.debug("onOutput");
		}

		s = replace(s);

		return s;
	}

	@Override
	public String onScript(String s) {
		if (_log.isDebugEnabled()) {
			_log.debug("onScript");
		}

		s = replace(s);

		return s;
	}

	@Override
	public String onXml(String s) {
		if (_log.isDebugEnabled()) {
			_log.debug("onXml");
		}

		return s;
	}

	protected String replace(String s) {
		if (s == null) {
			return s;
		}

		List<Pattern> patterns = RegexTransformerUtil.getPatterns();
		List<String> replacements = RegexTransformerUtil.getReplacements();

		for (int i = 0; i < patterns.size(); i++) {
			Pattern pattern = patterns.get(i);
			String replacement = replacements.get(i);

			Matcher matcher = pattern.matcher(s);

			s = matcher.replaceAll(replacement);
		}

		return s;
	}

	private static Log _log = LogFactoryUtil.getLog(
		RegexTransformerListener.class);

}