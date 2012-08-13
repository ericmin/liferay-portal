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

package com.liferay.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.pacl.PACLClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Brian Wing Shun Chan
 */
public class DefaultControlPanelEntryFactory {

	public static ControlPanelEntry getInstance() {
		if (_controlPanelEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Instantiate " +
						PropsValues.CONTROL_PANEL_DEFAULT_ENTRY_CLASS);
			}

			ClassLoader classLoader =
				PACLClassLoaderUtil.getPortalClassLoader();

			try {
				_controlPanelEntry = (ControlPanelEntry)classLoader.loadClass(
					PropsValues.CONTROL_PANEL_DEFAULT_ENTRY_CLASS).
						newInstance();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Return " + _controlPanelEntry.getClass().getName());
		}

		return _controlPanelEntry;
	}

	public static void setInstance(ControlPanelEntry controlPanelEntryFactory) {
		if (_log.isDebugEnabled()) {
			_log.debug("Set " + controlPanelEntryFactory.getClass().getName());
		}

		_controlPanelEntry = controlPanelEntryFactory;
	}

	private static Log _log = LogFactoryUtil.getLog(
		DefaultControlPanelEntryFactory.class);

	private static ControlPanelEntry _controlPanelEntry = null;

}