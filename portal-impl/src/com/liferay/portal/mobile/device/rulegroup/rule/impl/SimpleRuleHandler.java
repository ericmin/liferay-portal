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

package com.liferay.portal.mobile.device.rulegroup.rule.impl;

import com.liferay.portal.kernel.mobile.device.Device;
import com.liferay.portal.kernel.mobile.device.rulegroup.rule.RuleHandler;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.mobiledevicerules.model.MDRRule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Edward Han
 */
public class SimpleRuleHandler implements RuleHandler {

	public static String getHandlerType() {
		return SimpleRuleHandler.class.getName();
	}

	public boolean evaluateRule(MDRRule mdrRule, ThemeDisplay themeDisplay) {
		Device device = themeDisplay.getDevice();

		if ((device == null) || Validator.isNull(device.getOS())) {
			return false;
		}

		UnicodeProperties typeSettingsProperties =
			mdrRule.getTypeSettingsProperties();

		boolean result = true;

		String os = typeSettingsProperties.get("os");

		if (Validator.isNotNull(os)) {
			String[] operatingSystems = StringUtil.split(os);

			if (ArrayUtil.contains(operatingSystems, device.getOS())) {
				result = true;
			}
			else {
				result = false;
			}
		}

		String tablet = typeSettingsProperties.get("tablet");

		if (Validator.isNotNull(tablet)) {
			boolean tabletBoolean = GetterUtil.getBoolean(tablet);

			if (result && (tabletBoolean == device.isTablet())) {
				result = true;
			}
			else {
				result = false;
			}
		}

		return result;
	}

	public Collection<String> getPropertyNames() {
		return _propertyNames;
	}

	public String getType() {
		return getHandlerType();
	}

	private static Collection<String> _propertyNames;

	static {
		_propertyNames = new ArrayList<String>(2);

		_propertyNames.add("os");
		_propertyNames.add("tablet");

		_propertyNames = Collections.unmodifiableCollection(_propertyNames);
	}

}