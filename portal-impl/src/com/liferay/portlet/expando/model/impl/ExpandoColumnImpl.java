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

package com.liferay.portlet.expando.model.impl;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoValue;

import java.io.IOException;
import java.io.Serializable;

import java.util.Locale;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public class ExpandoColumnImpl extends ExpandoColumnBaseImpl {

	public ExpandoColumnImpl() {
	}

	public Serializable getDefaultValue() {
		try {
			ExpandoValue value = new ExpandoValueImpl();

			value.setColumnId(getColumnId());
			value.setData(getDefaultData());

			int type = getType();

			if (type == ExpandoColumnConstants.BOOLEAN) {
				return value.getBoolean();
			}
			else if (type == ExpandoColumnConstants.BOOLEAN_ARRAY) {
				return value.getBooleanArray();
			}
			else if (type == ExpandoColumnConstants.DATE) {
				return value.getDate();
			}
			else if (type == ExpandoColumnConstants.DATE_ARRAY) {
				return value.getDateArray();
			}
			else if (type == ExpandoColumnConstants.DOUBLE) {
				return value.getDouble();
			}
			else if (type == ExpandoColumnConstants.DOUBLE_ARRAY) {
				return value.getDoubleArray();
			}
			else if (type == ExpandoColumnConstants.FLOAT) {
				return value.getFloat();
			}
			else if (type == ExpandoColumnConstants.FLOAT_ARRAY) {
				return value.getFloatArray();
			}
			else if (type == ExpandoColumnConstants.INTEGER) {
				return value.getInteger();
			}
			else if (type == ExpandoColumnConstants.INTEGER_ARRAY) {
				return value.getIntegerArray();
			}
			else if (type == ExpandoColumnConstants.LONG) {
				return value.getLong();
			}
			else if (type == ExpandoColumnConstants.LONG_ARRAY) {
				return value.getLongArray();
			}
			else if (type == ExpandoColumnConstants.NUMBER) {
				return value.getNumber();
			}
			else if (type == ExpandoColumnConstants.NUMBER_ARRAY) {
				return value.getNumberArray();
			}
			else if (type == ExpandoColumnConstants.SHORT) {
				return value.getShort();
			}
			else if (type == ExpandoColumnConstants.SHORT_ARRAY) {
				return value.getShortArray();
			}
			else if (type == ExpandoColumnConstants.STRING_ARRAY) {
				return value.getStringArray();
			}
			else {
				return value.getString();
			}
		}
		catch (Exception e) {
			return null;
		}
	}

	public String getDisplayName(Locale locale) {
		String name = getName();

		String displayName = LanguageUtil.get(locale, name);

		if (name.equals(displayName)) {
			displayName = TextFormatter.format(name, TextFormatter.J);
		}

		return displayName;
	}

	@Override
	public String getTypeSettings() {
		if (_typeSettingsProperties == null) {
			return super.getTypeSettings();
		}
		else {
			return _typeSettingsProperties.toString();
		}
	}

	public UnicodeProperties getTypeSettingsProperties() {
		if (_typeSettingsProperties == null) {
			_typeSettingsProperties = new UnicodeProperties(true);

			try {
				_typeSettingsProperties.load(super.getTypeSettings());
			}
			catch (IOException ioe) {
				_log.error(ioe, ioe);
			}
		}

		return _typeSettingsProperties;
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		_typeSettingsProperties = null;

		super.setTypeSettings(typeSettings);
	}

	public void setTypeSettingsProperties(
		UnicodeProperties typeSettingsProperties) {

		_typeSettingsProperties = typeSettingsProperties;

		super.setTypeSettings(_typeSettingsProperties.toString());
	}

	private static Log _log = LogFactoryUtil.getLog(ExpandoColumnImpl.class);

	private UnicodeProperties _typeSettingsProperties;

}