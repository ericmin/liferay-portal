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

import com.liferay.portal.kernel.util.Validator;

import freemarker.cache.TemplateLoader;

import java.io.Reader;
import java.io.StringReader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Michael C. Han
 */
public class StringTemplateLoader implements TemplateLoader {

	public void closeTemplateSource(Object templateSource) {
	}

	public Object findTemplateSource(String name) {
		return _templates.get(name);
	}

	public long getLastModified(Object templateSource) {
		StringTemplateSource stringTemplateSource =
			(StringTemplateSource)templateSource;

		return stringTemplateSource._lastModified;
	}

	public Reader getReader(Object templateSource, String encoding) {
		StringTemplateSource stringTemplateSource =
			(StringTemplateSource)templateSource;

		return new StringReader(stringTemplateSource._templateSource);
	}

	public void putTemplate(String name, String templateSource) {
		putTemplate(name, templateSource, System.currentTimeMillis());
	}

	public void putTemplate(
		String name, String templateSource, long lastModified) {

		_templates.put(
			name, new StringTemplateSource(name, templateSource, lastModified));
	}

	public void removeTemplate(String name) {
		_templates.remove(name);
	}

	private Map<String, StringTemplateSource> _templates =
		new ConcurrentHashMap<String, StringTemplateSource>();

	private class StringTemplateSource {

		public StringTemplateSource(
			String name, String templateSource, long lastModified) {

			if (name == null) {
				throw new IllegalArgumentException("Name is null");
			}

			if (templateSource == null) {
				throw new IllegalArgumentException("Template source is null");
			}

			if (lastModified < -1) {
				throw new IllegalArgumentException(
					"Last modified is less than -1");
			}

			_name = name;
			_templateSource = templateSource;
			_lastModified = lastModified;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof StringTemplateSource)) {
				return false;
			}

			StringTemplateSource stringTemplateSource =
				(StringTemplateSource)obj;

			if (Validator.equals(_name, stringTemplateSource._name)) {
				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			return _name.hashCode();
		}

		private long _lastModified;
		private String _name;
		private String _templateSource;

	}

}