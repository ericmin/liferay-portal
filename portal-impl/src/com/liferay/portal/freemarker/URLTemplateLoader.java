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

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import java.net.URL;

/**
 * @author Mika Koivisto
 */
public abstract class URLTemplateLoader extends FreeMarkerTemplateLoader {

	@Override
	public void closeTemplateSource(Object templateSource) {
		if (templateSource instanceof URLTemplateSource) {
			URLTemplateSource urlTemplateSource =
				(URLTemplateSource)templateSource;

			try {
				urlTemplateSource.closeStream();
			}
			catch (IOException ioe) {
			}
		}
	}

	@Override
	public Object findTemplateSource(String name) throws IOException {
		URL url = getURL(name);

		if (url != null) {
			return new URLTemplateSource(url);
		}

		return null;
	}

	@Override
	public long getLastModified(Object templateSource) {
		if (templateSource instanceof URLTemplateSource) {
			URLTemplateSource urlTemplateSource =
				(URLTemplateSource)templateSource;

			return urlTemplateSource.getLastModified();
		}

		return super.getLastModified(templateSource);
	}

	@Override
	public Reader getReader(Object templateSource, String encoding)
		throws IOException {

		if (templateSource instanceof URLTemplateSource) {
			URLTemplateSource urlTemplateSource =
				(URLTemplateSource)templateSource;

			return new UnsyncBufferedReader(
				new InputStreamReader(
					urlTemplateSource.getInputStream(), encoding));
		}

		return null;
	}

	public abstract URL getURL(String name) throws IOException;

}