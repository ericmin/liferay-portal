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

package com.liferay.portlet.documentlibrary.util;

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author Shinn Lok
 */
public abstract class BaseDLProcessorImpl implements DLProcessor {

	public boolean isEnabled() {
		Class<?> clazz = getClass();

		return _DL_FILE_ENTRY_PROCESSORS.contains(clazz.getName());
	}

	private static final String _DL_FILE_ENTRY_PROCESSORS = PropsUtil.get(
		PropsKeys.DL_FILE_ENTRY_PROCESSORS);

}