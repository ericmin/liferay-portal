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

/**
 * @author Raymond Augé
 */
public class PortletDataHandlerBoolean extends PortletDataHandlerControl {

	public PortletDataHandlerBoolean(String namespace, String controlName) {
		this(namespace, controlName, true);
	}

	public PortletDataHandlerBoolean(
		String namespace, String controlName, boolean defaultState) {

		this(namespace, controlName, defaultState, false);
	}

	public PortletDataHandlerBoolean(
		String namespace, String controlName, boolean defaultState,
		boolean disabled) {

		this(namespace, controlName, defaultState, disabled, null);
	}

	public PortletDataHandlerBoolean(
		String namespace, String controlName, boolean defaultState,
		PortletDataHandlerControl[] children) {

		this(namespace, controlName, defaultState, false, children);
	}

	public PortletDataHandlerBoolean(
		String namespace, String controlName, boolean defaultState,
		boolean disabled, PortletDataHandlerControl[] children) {

		super(namespace, controlName, disabled);

		_children = children;
		_defaultState = defaultState;
	}

	public PortletDataHandlerControl[] getChildren() {
		return _children;
	}

	public boolean getDefaultState() {
		return _defaultState;
	}

	private PortletDataHandlerControl[] _children;
	private boolean _defaultState;

}