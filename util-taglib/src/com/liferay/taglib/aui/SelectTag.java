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

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.aui.base.BaseSelectTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class SelectTag extends BaseSelectTag {

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		super.setAttributes(request);

		Object bean = getBean();

		if (bean == null) {
			bean = pageContext.getAttribute("aui:model-context:bean");
		}

		String name = getName();

		int pos = name.indexOf(StringPool.DOUBLE_DASH);

		if (pos != -1) {
			name = name.substring(pos + 2, name.length() - 2);
		}

		String id = getId();

		if (Validator.isNull(id)) {
			id = name;
		}

		String label = getLabel();

		if (label == null) {
			label = TextFormatter.format(name, TextFormatter.K);
		}

		String listType = getListType();
		String listTypeFieldName = getListTypeFieldName();

		if (Validator.isNotNull(listType) &&
			Validator.isNull(listTypeFieldName)) {

			listTypeFieldName = "typeId";
		}

		String value = StringPool.BLANK;

		if (Validator.isNull(listType)) {
			if (bean != null) {
				value = BeanPropertiesUtil.getStringSilent(bean, name, value);
			}

			if (!getIgnoreRequestValue()) {
				value = ParamUtil.getString(request, name, value);
			}
		}

		setNamespacedAttribute(request, "bean", bean);
		setNamespacedAttribute(request, "id", id);
		setNamespacedAttribute(request, "label", label);
		setNamespacedAttribute(request, "listTypeFieldName", listTypeFieldName);
		setNamespacedAttribute(request, "value", value);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

}