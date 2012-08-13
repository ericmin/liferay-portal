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

package com.liferay.portlet.layoutconfiguration.util.velocity;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.CustomizedPages;
import com.liferay.portal.model.Layout;
import com.liferay.portlet.sites.util.SitesUtil;
import com.liferay.taglib.aui.InputTag;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * @author Raymond Augé
 */
public class CustomizationSettingsProcessor implements ColumnProcessor {

	public CustomizationSettingsProcessor(
		HttpServletRequest request, PageContext pageContext, Writer writer) {

		_pageContext = pageContext;
		_request = request;
		_writer = writer;

		Layout selLayout = (Layout)_request.getAttribute(
			"edit_pages.jsp-selLayout");

		_layoutTypeSettings = selLayout.getTypeSettingsProperties();

		_customizationEnabled = true;

		if (!SitesUtil.isLayoutUpdateable(selLayout)) {
			_customizationEnabled = false;
		}

		if (selLayout.isLayoutPrototypeLinkActive()) {
			_customizationEnabled = false;
		}
	}

	public String processColumn(String columnId) throws Exception {
		return processColumn(columnId, StringPool.BLANK);
	}

	public String processColumn(String columnId, String classNames)
		throws Exception {

		String customizableKey = CustomizedPages.namespaceColumnId(columnId);

		boolean customizable = false;

		if (_customizationEnabled) {
			customizable = GetterUtil.getBoolean(
				_layoutTypeSettings.getProperty(
					customizableKey, String.valueOf(false)));
		}

		_writer.append("<div class=\"");
		_writer.append(classNames);
		_writer.append("\">");

		_writer.append("<h1>");
		_writer.append(columnId);
		_writer.append("</h1>");

		InputTag inputTag = new InputTag();

		inputTag.setDisabled(!_customizationEnabled);
		inputTag.setLabel("customizable");
		inputTag.setName(
			"TypeSettingsProperties--".concat(customizableKey).concat("--"));
		inputTag.setPageContext(_pageContext);
		inputTag.setType("checkbox");
		inputTag.setValue(customizable);

		int result = inputTag.doStartTag();

		if (result == Tag.EVAL_BODY_INCLUDE) {
			inputTag.doEndTag();
		}

		_writer.append("</div>");

		return StringPool.BLANK;
	}

	public String processMax() throws Exception {
		return processMax(StringPool.BLANK);
	}

	public String processMax(String classNames) throws Exception {
		return StringPool.BLANK;
	}

	public String processPortlet(String portletId) throws Exception {
		_writer.append("<div class=\"portlet\">");
		_writer.append(portletId);
		_writer.append("</div>");

		return StringPool.BLANK;
	}

	private boolean _customizationEnabled;
	private UnicodeProperties _layoutTypeSettings;
	private PageContext _pageContext;
	private HttpServletRequest _request;
	private Writer _writer;

}