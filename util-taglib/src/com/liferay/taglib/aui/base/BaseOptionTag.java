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

package com.liferay.taglib.aui.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

/**
 * @author Eduardo Lundgren
 * @author Bruno Basto
 * @author Nathan Cavanaugh
 * @author Julio Camarero
 * @generated
 */
public class BaseOptionTag extends com.liferay.taglib.util.IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doStartTag();
	}

	public java.lang.String getCssClass() {
		return _cssClass;
	}

	public java.util.Map<java.lang.String, java.lang.Object> getData() {
		return _data;
	}

	public boolean getDisabled() {
		return _disabled;
	}

	public java.lang.Object getLabel() {
		return _label;
	}

	public boolean getSelected() {
		return _selected;
	}

	public java.lang.String getStyle() {
		return _style;
	}

	public java.lang.Object getValue() {
		return _value;
	}

	public void setCssClass(java.lang.String cssClass) {
		_cssClass = cssClass;

		setScopedAttribute("cssClass", cssClass);
	}

	public void setData(java.util.Map<java.lang.String, java.lang.Object> data) {
		_data = data;

		setScopedAttribute("data", data);
	}

	public void setDisabled(boolean disabled) {
		_disabled = disabled;

		setScopedAttribute("disabled", disabled);
	}

	public void setLabel(java.lang.Object label) {
		_label = label;

		setScopedAttribute("label", label);
	}

	public void setSelected(boolean selected) {
		_selected = selected;

		setScopedAttribute("selected", selected);
	}

	public void setStyle(java.lang.String style) {
		_style = style;

		setScopedAttribute("style", style);
	}

	public void setValue(java.lang.Object value) {
		_value = value;

		setScopedAttribute("value", value);
	}

	@Override
	protected void cleanUp() {
		_cssClass = null;
		_data = null;
		_disabled = false;
		_label = null;
		_selected = false;
		_style = null;
		_value = null;
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		setNamespacedAttribute(request, "cssClass", _cssClass);
		setNamespacedAttribute(request, "data", _data);
		setNamespacedAttribute(request, "disabled", _disabled);
		setNamespacedAttribute(request, "label", _label);
		setNamespacedAttribute(request, "selected", _selected);
		setNamespacedAttribute(request, "style", _style);
		setNamespacedAttribute(request, "value", _value);
	}

	protected static final String _ATTRIBUTE_NAMESPACE = "aui:option:";

	private static final String _END_PAGE =
		"/html/taglib/aui/option/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/aui/option/start.jsp";

	private java.lang.String _cssClass = null;
	private java.util.Map<java.lang.String, java.lang.Object> _data = null;
	private boolean _disabled = false;
	private java.lang.Object _label = null;
	private boolean _selected = false;
	private java.lang.String _style = null;
	private java.lang.Object _value = null;

}