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

package com.liferay.portlet.journal.util;

import com.liferay.portal.kernel.templateparser.TemplateContext;

import javax.xml.transform.Transformer;

/**
 * @author Brian Wing Shun Chan
 */
public class XSLContext implements TemplateContext {

	public XSLContext(Transformer transformer) {
		_transformer = transformer;
	}

	public Object get(String key) {
		return _transformer.getParameter(key);
	}

	public Transformer getTransformer() {
		return _transformer;
	}

	public void put(String key, Object value) {
		_transformer.setParameter(key, value);
	}

	private Transformer _transformer;

}