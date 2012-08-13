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

import java.io.Reader;

import java.util.List;

/**
 * <p>
 * This class can compare two different versions of a text. Source refers to the
 * earliest version of the text and target refers to a modified version of
 * source. Changes are considered either as a removal from the source or as an
 * addition to the target. This class detects changes to an entire line and also
 * detects changes within lines, such as, removal or addition of characters.
 * Take a look at <code>DiffTest</code> to see the expected inputs and outputs.
 * </p>
 *
 * @author Bruno Farache
 * @see    com.liferay.portal.kernel.util.DiffUtil
 */
public class DiffUtil {

	public static List<DiffResult>[] diff(Reader source, Reader target) {
		return getDiff().diff(source, target);
	}

	public static List<DiffResult>[] diff(
		Reader source, Reader target, String addedMarkerStart,
		String addedMarkerEnd, String deletedMarkerStart,
		String deletedMarkerEnd, int margin) {

		return getDiff().diff(
			source, target, addedMarkerStart, addedMarkerEnd,
			deletedMarkerStart, deletedMarkerEnd, margin);
	}

	public static Diff getDiff() {
		PortalRuntimePermission.checkGetBeanProperty(DiffUtil.class);

		return _diff;
	}

	public void setDiff(Diff diff) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_diff = diff;
	}

	private static Diff _diff;

}