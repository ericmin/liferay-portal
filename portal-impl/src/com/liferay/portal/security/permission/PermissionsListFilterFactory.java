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

package com.liferay.portal.security.permission;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.pacl.PACLClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Brian Wing Shun Chan
 */
public class PermissionsListFilterFactory {

	public static PermissionsListFilter getInstance() {
		if (_permissionsListFilter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Instantiate " + PropsValues.PERMISSIONS_LIST_FILTER);
			}

			ClassLoader classLoader =
				PACLClassLoaderUtil.getPortalClassLoader();

			try {
				_permissionsListFilter =
					(PermissionsListFilter)classLoader.loadClass(
						PropsValues.PERMISSIONS_LIST_FILTER).newInstance();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Return " + _permissionsListFilter.getClass().getName());
		}

		return _permissionsListFilter;
	}

	public static void setInstance(
		PermissionsListFilter permissionsListFilter) {

		if (_log.isDebugEnabled()) {
			_log.debug("Set " + permissionsListFilter.getClass().getName());
		}

		_permissionsListFilter = permissionsListFilter;
	}

	private static Log _log = LogFactoryUtil.getLog(
		PermissionsListFilterFactory.class);

	private static PermissionsListFilter _permissionsListFilter;

}