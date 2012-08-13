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

package com.liferay.util.bridges.jsf.common;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;

/**
 * <p>
 * This class serves as a bridge between the JSF Expression Language (EL) and
 * Liferay's Language.properties resource bundle.
 * </p>
 *
 * @author Neil Griffin
 */
public class LanguageManagedBean implements Map<String, String> {

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public boolean containsKey(Object key) {
		throw new UnsupportedOperationException();
	}

	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();
	}

	public Set<Entry<String, String>> entrySet() {
		throw new UnsupportedOperationException();
	}

	public String get(Object key) {
		String value = null;

		if (key != null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			Locale locale = facesContext.getViewRoot().getLocale();

			if (locale == null) {
				locale = facesContext.getApplication().getDefaultLocale();
			}

			value = LanguageUtil.get(locale, key.toString());

			if (_log.isDebugEnabled()) {
				_log.debug(
					"{locale=" + locale + ", key=" + key + ", value=" + value);
			}
		}

		return value;
	}

	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	public Set<String> keySet() {
		throw new UnsupportedOperationException();
	}

	public String put(String key, String value) {
		throw new UnsupportedOperationException();
	}

	public void putAll(Map<? extends String, ? extends String> map) {
		throw new UnsupportedOperationException();
	}

	public String remove(Object key) {
		throw new UnsupportedOperationException();
	}

	public int size() {
		throw new UnsupportedOperationException();
	}

	public Collection<String> values() {
		throw new UnsupportedOperationException();
	}

	private static Log _log = LogFactoryUtil.getLog(LanguageManagedBean.class);

}