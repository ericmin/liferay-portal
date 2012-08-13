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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Julio Camarero
 * @author Samuel Kong
 */
public class FriendlyURLNormalizerUtil {

	public static FriendlyURLNormalizer getFriendlyURLNormalizer() {
		PortalRuntimePermission.checkGetBeanProperty(
			FriendlyURLNormalizerUtil.class);

		return _friendlyURLNormalizer;
	}

	public static String normalize(String friendlyURL) {
		return getFriendlyURLNormalizer().normalize(friendlyURL);
	}

	public static String normalize(String friendlyURL, char[] replaceChars) {
		return getFriendlyURLNormalizer().normalize(friendlyURL, replaceChars);
	}

	public void setFriendlyURLNormalizer(
		FriendlyURLNormalizer friendlyURLNormalizer) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_friendlyURLNormalizer = friendlyURLNormalizer;
	}

	private static FriendlyURLNormalizer _friendlyURLNormalizer;

}