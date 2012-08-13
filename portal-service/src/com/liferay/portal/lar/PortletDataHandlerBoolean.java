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

package com.liferay.portal.lar;

/**
 * @author     Raymond Augé
 * @deprecated Moved to {@link
 *             com.liferay.portal.kernel.lar.PortletDataHandlerBoolean}
 */
public class PortletDataHandlerBoolean
	extends com.liferay.portal.kernel.lar.PortletDataHandlerBoolean {

	public PortletDataHandlerBoolean(String namespace, String controlName) {
		super(namespace, controlName);
	}

	public PortletDataHandlerBoolean(
		String namespace, String controlName, boolean defaultState) {

		super(namespace, controlName, defaultState);
	}

	public PortletDataHandlerBoolean(
		String namespace, String controlName, boolean defaultState,
		boolean disabled) {

		super(namespace, controlName, defaultState, disabled);
	}

	public PortletDataHandlerBoolean(
		String namespace, String controlName, boolean defaultState,
		PortletDataHandlerControl[] children) {

		super(namespace, controlName, defaultState, children);
	}

	public PortletDataHandlerBoolean(
		String namespace, String controlName, boolean defaultState,
		boolean disabled, PortletDataHandlerControl[] children) {

		super(namespace, controlName, defaultState, disabled, children);
	}

}