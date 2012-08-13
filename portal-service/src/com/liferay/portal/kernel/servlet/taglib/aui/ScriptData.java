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

package com.liferay.portal.kernel.servlet.taglib.aui;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Brian Wing Shun Chan
 */
public class ScriptData {

	public void append(String content, String use) {
		if (Validator.isNull(use)) {
			_rawSB.append(content);
		}
		else {
			_callbackSB.append("(function() {");
			_callbackSB.append(content);
			_callbackSB.append("})();");

			String[] useArray = StringUtil.split(use);

			for (int i = 0; i < useArray.length; i++) {
				_useSet.add(useArray[i]);
			}
		}
	}

	public void append(StringBundler contentSB, String use) {
		if (Validator.isNull(use)) {
			_rawSB.append(contentSB);
		}
		else {
			_callbackSB.append("(function() {");
			_callbackSB.append(contentSB);
			_callbackSB.append("})();");

			String[] useArray = StringUtil.split(use);

			for (int i = 0; i < useArray.length; i++) {
				_useSet.add(useArray[i]);
			}
		}
	}

	public StringBundler getCallbackSB() {
		return _callbackSB;
	}

	public StringBundler getRawSB() {
		return _rawSB;
	}

	public Set<String> getUseSet() {
		return _useSet;
	}

	private StringBundler _callbackSB = new StringBundler();
	private StringBundler _rawSB = new StringBundler();
	private Set<String> _useSet = new TreeSet<String>();

}