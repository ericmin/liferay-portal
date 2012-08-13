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

package com.liferay.portal.kernel.lar;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Raymond Augé
 */
public class PortletDataHandlerControl {

	public static String getNamespacedControlName(
		String namespace, String controlName) {

		StringBuilder sb = new StringBuilder();

		sb.append(StringPool.UNDERLINE);
		sb.append(namespace);
		sb.append(StringPool.UNDERLINE);
		sb.append(controlName);

		return sb.toString();
	}

	public PortletDataHandlerControl(String namespace, String controlName) {
		this(namespace, controlName, false);
	}

	public PortletDataHandlerControl(
		String namespace, String controlName, boolean disabled) {

		_namespace = namespace;
		_controlName = controlName;
		_disabled = disabled;
	}

	public String getControlName() {
		return _controlName;
	}

	public String getNamespace() {
		return _namespace;
	}

	public String getNamespacedControlName() {
		return getNamespacedControlName(_namespace, getControlName());
	}

	public boolean isDisabled() {
		return _disabled;
	}

	private String _controlName;
	private boolean _disabled;
	private String _namespace;

}