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

package com.liferay.portlet.dynamicdatamapping.storage;

import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.text.Format;

import java.util.Locale;

/**
 * @author Bruno Basto
 * @author Manuel de la Peña
 */
public class DateFieldRenderer extends BaseFieldRenderer {

	@Override
	protected String doRender(Field field, Locale locale) {
		Serializable value = field.getValue();

		if (Validator.isNull(value)) {
			return StringPool.BLANK;
		}

		Format format = FastDateFormatFactoryUtil.getDate(locale);

		return format.format(value);
	}

}