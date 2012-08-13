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

package com.liferay.taglib.aui;

import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.taglib.aui.base.BaseFieldWrapperTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

/**
 * @author Julio Camarero
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class FieldWrapperTag extends BaseFieldWrapperTag {

	@Override
	public int doEndTag() throws JspException {
		setCalledSetAttributes(false);

		return super.doEndTag();
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		super.setAttributes(request);

		String label = getLabel();

		if (label == null) {
			label = TextFormatter.format(getName(), TextFormatter.K);
		}

		setNamespacedAttribute(request, "label", label);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

}