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

package com.liferay.portal.kernel.editor;

import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 */
public class EditorUtil {

	public static String getEditorValue(
		HttpServletRequest request, String editorImpl) {

		if (Validator.isNotNull(editorImpl)) {
			editorImpl = PropsUtil.get(editorImpl);
		}

		if (!BrowserSnifferUtil.isRtf(request)) {
			if (BrowserSnifferUtil.isSafari(request) &&
				BrowserSnifferUtil.isMobile(request)) {

				editorImpl = "simple";
			}
			else if (BrowserSnifferUtil.isSafari(request) &&
					 !editorImpl.contains("simple")) {

				editorImpl = "tinymce_simple";
			}
			else {
				editorImpl = "simple";
			}
		}

		if (Validator.isNull(editorImpl)) {
			editorImpl = _EDITOR_WYSIWYG_DEFAULT;
		}

		return editorImpl;
	}

	private static final String _EDITOR_WYSIWYG_DEFAULT = PropsUtil.get(
		PropsKeys.EDITOR_WYSIWYG_DEFAULT);

}