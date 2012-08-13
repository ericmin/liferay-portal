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

package com.liferay.taglib.util;

import com.liferay.portal.kernel.servlet.taglib.CustomAttributes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;

/**
 * @author Eduardo Lundgren
 * @author Shuyang Zhou
 */
public class AttributesTagSupport
	extends ParamAndPropertyAncestorTagImpl implements DynamicAttributes {

	public void clearDynamicAttributes() {
		_dynamicAttributes.clear();
	}

	public String getAttributeNamespace() {
		return _attributeNamespace;
	}

	public CustomAttributes getCustomAttributes() {
		return _customAttributes;
	}

	public Object getDynamicAttribute(String key) {
		return _dynamicAttributes.get(key);
	}

	public Object getNamespacedAttribute(
		HttpServletRequest request, String key) {

		return request.getAttribute(_encodeKey(key));
	}

	public Object getScopedAttribute(String key) {
		return _scopedAttributes.get(key);
	}

	public Map<String, Object> getScopedAttributes() {
		return _scopedAttributes;
	}

	public void setAttributeNamespace(String attributeNamespace) {
		_attributeNamespace = attributeNamespace;
	}

	public void setCustomAttributes(CustomAttributes customAttributes) {
		_customAttributes = customAttributes;
	}

	public void setDynamicAttribute(
		String uri, String localName, Object value) {

		_dynamicAttributes.put(localName, value);
	}

	public void setNamespacedAttribute(
		HttpServletRequest request, String key, Object value) {

		if (value instanceof Boolean) {
			value = String.valueOf(value);
		}
		else if (value instanceof Number) {
			value = String.valueOf(value);
		}

		request.setAttribute(_encodeKey(key), value);
	}

	public void setScopedAttribute(String name, Object value) {
		_scopedAttributes.put(name, value);
	}

	protected Map<String, Object> getDynamicAttributes() {
		return _dynamicAttributes;
	}

	protected void writeDynamicAttributes(JspWriter jspWriter)
		throws IOException {

		String dynamicAttributesString = InlineUtil.buildDynamicAttributes(
			getDynamicAttributes());

		if (Validator.isNotNull(dynamicAttributesString)) {
			jspWriter.write(dynamicAttributesString);
		}
	}

	private String _encodeKey(String key) {
		if (_attributeNamespace.length() == 0) {
			return key;
		}
		else {
			return _attributeNamespace.concat(key);
		}
	}

	private String _attributeNamespace = StringPool.BLANK;
	private CustomAttributes _customAttributes;
	private Map<String, Object> _dynamicAttributes =
		new HashMap<String, Object>();
	private Map<String, Object> _scopedAttributes =
		new HashMap<String, Object>();

}