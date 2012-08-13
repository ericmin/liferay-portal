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

package com.liferay.portal.kernel.servlet.filters.compoundsessionid;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Michael C. Han
 */
public class CompoundSessionIdSplitterUtil {

	public static CompoundSessionIdSplitter getCompoundSessionIdSplitter() {
		PortalRuntimePermission.checkGetBeanProperty(
			CompoundSessionIdSplitterUtil.class);

		return _compoundSessionIdSplitter;
	}

	public static String getSessionIdDelimiter() {
		return getCompoundSessionIdSplitter().getSessionIdDelimiter();
	}

	public static boolean hasSessionDelimiter() {
		return getCompoundSessionIdSplitter().hasSessionDelimiter();
	}

	public static String parseSessionId(String sessionId) {
		return getCompoundSessionIdSplitter().parseSessionId(sessionId);
	}

	public void setCompoundSessionIdSplitter(
		CompoundSessionIdSplitter compoundSessionIdSplitter) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_compoundSessionIdSplitter = compoundSessionIdSplitter;
	}

	private static CompoundSessionIdSplitter _compoundSessionIdSplitter;

}