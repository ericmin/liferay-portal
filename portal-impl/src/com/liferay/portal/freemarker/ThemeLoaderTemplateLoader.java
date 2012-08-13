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

package com.liferay.portal.freemarker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.theme.ThemeLoader;
import com.liferay.portal.theme.ThemeLoaderFactory;

import java.io.File;
import java.io.IOException;

import java.net.URL;

/**
 * @author Mika Koivisto
 */
public class ThemeLoaderTemplateLoader extends URLTemplateLoader {

	@Override
	public URL getURL(String name) throws IOException {
		int pos = name.indexOf(THEME_LOADER_SEPARATOR);

		if (pos != -1) {
			String ctxName = name.substring(0, pos);

			ThemeLoader themeLoader = ThemeLoaderFactory.getThemeLoader(
				ctxName);

			if (themeLoader != null) {
				String templateName = name.substring(
					pos + THEME_LOADER_SEPARATOR.length());

				String themesPath = themeLoader.getThemesPath();

				if (templateName.startsWith(themesPath)) {
					name = templateName.substring(themesPath.length());
				}

				if (_log.isDebugEnabled()) {
					_log.debug(
						name + " is associated with the theme loader " +
							ctxName + " " + themeLoader);
				}

				File fileStorage = themeLoader.getFileStorage();

				return new File(fileStorage.getPath() + name).toURI().toURL();

			}
			else {
				_log.error(
					name + " is not valid because " + ctxName +
						" does not map to a theme loader");
			}
		}

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(
		ThemeLoaderTemplateLoader.class);

}