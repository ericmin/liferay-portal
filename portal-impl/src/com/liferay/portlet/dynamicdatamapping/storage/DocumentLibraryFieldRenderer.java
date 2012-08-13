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

package com.liferay.portlet.dynamicdatamapping.storage;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;

import java.io.Serializable;

import java.util.Locale;

/**
 * @author Bruno Basto
 */
public class DocumentLibraryFieldRenderer extends BaseFieldRenderer {

	@Override
	protected String doRender(Field field, Locale locale) {
		Serializable fieldValue = field.getValue();

		if (Validator.isNull(fieldValue) ||
			fieldValue.equals(JSONFactoryUtil.getNullJSON())) {

			return StringPool.BLANK;
		}

		JSONObject fieldValueJSONObject = null;

		try {
			fieldValueJSONObject = JSONFactoryUtil.createJSONObject(
				String.valueOf(fieldValue));
		}
		catch (JSONException jsone) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to parse JSON", jsone);
			}

			return StringPool.BLANK;
		}

		long fileEntryGroupId = fieldValueJSONObject.getLong("groupId");
		String fileEntryUUID = fieldValueJSONObject.getString("uuid");

		try {
			FileEntry fileEntry = DLAppServiceUtil.getFileEntryByUuidAndGroupId(
				fileEntryUUID, fileEntryGroupId);

			return fileEntry.getTitle();
		}
		catch (Exception e) {
			if (e instanceof NoSuchFileEntryException ||
				e instanceof PrincipalException) {

				return LanguageUtil.format(
					locale, "is-temporarily-unavailable", "content");
			}
		}

		return StringPool.BLANK;
	}

	private static Log _log = LogFactoryUtil.getLog(
		DocumentLibraryFieldRenderer.class);

}