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

package com.liferay.portlet.translator.model;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * @author Brian Wing Shun Chan
 */
public class Translation implements Serializable {

	public Translation(String translationId, String fromText) {
		_translationId = translationId;
		setFromText(fromText);
	}

	public Translation(String translationId, String fromText, String toText) {
		_translationId = translationId;
		setFromText(fromText);
		setToText(toText);
	}

	public String getFromText() {
		return _fromText;
	}

	public String getToText() {
		return _toText;
	}

	public String getTranslationId() {
		return _translationId;
	}

	public void setFromText(String fromText) {
		try {
			_fromText = new String(fromText.getBytes(), StringPool.UTF8);
		}
		catch (UnsupportedEncodingException uee) {
		}
	}

	public void setToText(String toText) {
		try {
			_toText = new String(toText.getBytes(), StringPool.UTF8);
		}
		catch (UnsupportedEncodingException uee) {
		}
	}

	private String _fromText;
	private String _toText;
	private String _translationId;

}