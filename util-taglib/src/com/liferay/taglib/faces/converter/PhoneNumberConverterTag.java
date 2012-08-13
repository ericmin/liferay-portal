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

package com.liferay.taglib.faces.converter;

import com.liferay.taglib.faces.util.JSFTagUtil;

import javax.faces.convert.Converter;
import javax.faces.webapp.ConverterTag;

import javax.servlet.jsp.JspException;

/**
 * @author Neil Griffin
 */
public class PhoneNumberConverterTag extends ConverterTag {

	public PhoneNumberConverterTag() {
		setConverterId(PhoneNumberConverter.class.getName());
	}

	@Override
	public void release() {
		_unitedStatesFormat = null;
	}

	public void setUnitedStatesFormat(String unitedStatesFormat) {
		_unitedStatesFormat = unitedStatesFormat;
	}

	@Override
	protected Converter createConverter() throws JspException {
		PhoneNumberConverter converter =
			(PhoneNumberConverter)super.createConverter();

		converter.setUnitedStatesFormat(JSFTagUtil.eval(_unitedStatesFormat));

		return converter;
	}

	private String _unitedStatesFormat = "(###) ###-####";

}