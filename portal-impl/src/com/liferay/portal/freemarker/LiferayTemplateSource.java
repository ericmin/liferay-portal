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
import java.io.Reader;

/**
 * @author Mika Koivisto
 */
public class LiferayTemplateSource {

	public LiferayTemplateSource(
		FreeMarkerTemplateLoader freeMarkerTemplateLoader,
		Object templateSource) {

		_freeMarkerTemplateLoader = freeMarkerTemplateLoader;
		_templateSource = templateSource;
	}

	public void close() {
		_freeMarkerTemplateLoader.closeTemplateSource(_templateSource);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LiferayTemplateSource) {
			LiferayTemplateSource liferayTemplateSource =
				(LiferayTemplateSource)obj;

			if (liferayTemplateSource._freeMarkerTemplateLoader.equals(
					_freeMarkerTemplateLoader) &&
				liferayTemplateSource._templateSource.equals(_templateSource)) {

				return true;
			}
		}

		return false;
	}

	public long getLastModified() {
		return _freeMarkerTemplateLoader.getLastModified(_templateSource);
	}

	public Reader getReader(String encoding) throws IOException {
		return _freeMarkerTemplateLoader.getReader(_templateSource, encoding);
	}

	@Override
	public int hashCode() {
		return _freeMarkerTemplateLoader.hashCode() +
			(31 * _templateSource.hashCode());
	}

	@Override
	public String toString() {
		return _templateSource.toString();
	}

	private FreeMarkerTemplateLoader _freeMarkerTemplateLoader;
	private Object _templateSource;

}