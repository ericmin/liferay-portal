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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

/**
 * The base implementation for all model classes. This class should never need
 * to be used directly.
 *
 * @author Brian Wing Shun Chan
 */
public abstract class BaseModelImpl<T> implements BaseModel<T> {

	public BaseModelImpl() {
	}

	@Override
	public abstract Object clone();

	public ExpandoBridge getExpandoBridge() {
		throw new UnsupportedOperationException();
	}

	public Map<String, Object> getModelAttributes() {
		return Collections.emptyMap();
	}

	public boolean isCachedModel() {
		return _cachedModel;
	}

	public boolean isEscapedModel() {
		return _ESCAPED_MODEL;
	}

	public boolean isNew() {
		return _new;
	}

	public void resetOriginalValues() {
	}

	public void setCachedModel(boolean cachedModel) {
		_cachedModel = cachedModel;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		throw new UnsupportedOperationException();
	}

	public void setModelAttributes(Map<String, Object> attributes) {
	}

	public void setNew(boolean n) {
		_new = n;
	}

	public CacheModel<T> toCacheModel() {
		throw new UnsupportedOperationException();
	}

	public T toEscapedModel() {
		throw new UnsupportedOperationException();
	}

	protected Locale getLocale(String languageId) {
		Locale locale = null;

		if (languageId != null) {
			locale = LocaleUtil.fromLanguageId(languageId);
		}

		if (locale == null) {
			locale = LocaleUtil.getMostRelevantLocale();
		}

		return locale;
	}

	private static final boolean _ESCAPED_MODEL = false;

	private boolean _cachedModel;
	private boolean _new;

}