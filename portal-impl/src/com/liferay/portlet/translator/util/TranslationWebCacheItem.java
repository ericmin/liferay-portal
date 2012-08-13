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

package com.liferay.portlet.translator.util;

import com.liferay.portal.kernel.microsofttranslator.MicrosoftTranslator;
import com.liferay.portal.kernel.microsofttranslator.MicrosoftTranslatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portlet.translator.model.Translation;

/**
 * @author Brian Wing Shun Chan
 * @author Hugo Huijser
 */
public class TranslationWebCacheItem implements WebCacheItem {

	public TranslationWebCacheItem(String translationId, String fromText) {
		_translationId = translationId;
		_fromText = fromText;
	}

	public Object convert(String key) throws WebCacheException {
		Translation translation = new Translation(_translationId, _fromText);

		try {
			MicrosoftTranslator microsoftTranslator =
				MicrosoftTranslatorFactoryUtil.getMicrosoftTranslator();

			int x = _translationId.indexOf(StringPool.UNDERLINE);

			if ((x == -1) || ((x + 1) == _translationId.length())) {
				throw new WebCacheException(
					"Invalid translation ID " + _translationId);
			}

			if (Character.isUpperCase(_translationId.charAt(x + 1))) {
				x = _translationId.indexOf(StringPool.UNDERLINE, x + 1);

				if ((x == -1) || ((x + 1) == _translationId.length())) {
					throw new WebCacheException(
						"Invalid translation ID " + _translationId);
				}
			}

			String fromLanguage = _translationId.substring(0, x);
			String toLanguage = _translationId.substring(x + 1);

			String toText = microsoftTranslator.translate(
				fromLanguage, toLanguage, _fromText);

			translation.setToText(toText);
		}
		catch (Exception e) {
			throw new WebCacheException(e);
		}

		return translation;
	}

	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.DAY * 90;

	private String _fromText;
	private String _translationId;

}