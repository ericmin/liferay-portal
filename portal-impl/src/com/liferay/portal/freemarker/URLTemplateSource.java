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

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;

/**
 * @author Mika Koivisto
 */
public class URLTemplateSource {

	public URLTemplateSource(URL url) throws IOException {
		_url = url;
		_urlConnection = url.openConnection();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof URLTemplateSource) {
			URLTemplateSource urlTemplateSource = (URLTemplateSource)obj;

			if (_url.equals(urlTemplateSource._url)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public int hashCode() {
		return _url.hashCode();
	}

	@Override
	public String toString() {
		return _url.toString();
	}

	protected void closeStream() throws IOException {
		try {
			if (_inputStream != null) {
				_inputStream.close();
			}
			else {
				_urlConnection.getInputStream().close();
			}
		}
		finally {
			_inputStream = null;
			_urlConnection = null;
		}
	}

	protected InputStream getInputStream() throws IOException {
		_inputStream = _urlConnection.getInputStream();

		return _inputStream;
	}

	protected long getLastModified() {
		return _urlConnection.getLastModified();
	}

	private InputStream _inputStream;
	private URL _url;
	private URLConnection _urlConnection;

}