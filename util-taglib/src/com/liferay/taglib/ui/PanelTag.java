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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.servlet.taglib.BaseBodyTagSupport;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;
import com.liferay.util.PwdGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class PanelTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		if (Validator.isNull(_id)) {
			_id = PwdGenerator.getPassword(PwdGenerator.KEY3, 4);
		}

		BaseBodyTagSupport baseBodyTagSupport =
			(BaseBodyTagSupport)findAncestorWithClass(
				this, BaseBodyTagSupport.class);

		String parentId = StringPool.BLANK;

		if (baseBodyTagSupport instanceof PanelContainerTag) {
			PanelContainerTag panelContainerTag =
				(PanelContainerTag)baseBodyTagSupport;

			parentId = panelContainerTag.getId();
		}
		else if (baseBodyTagSupport instanceof PanelFloatingContainerTag) {
			PanelFloatingContainerTag panelFloatingContainerTag =
				(PanelFloatingContainerTag)baseBodyTagSupport;

			parentId = panelFloatingContainerTag.getId();
		}

		request.setAttribute("liferay-ui:panel:helpMessage", _helpMessage);
		request.setAttribute("liferay-ui:panel:id", _id);
		request.setAttribute("liferay-ui:panel:parentId", parentId);
		request.setAttribute("liferay-ui:panel:title", _title);
		request.setAttribute(
			"liferay-ui:panel:collapsible", String.valueOf(_collapsible));
		request.setAttribute("liferay-ui:panel:defaultState", _defaultState);
		request.setAttribute(
			"liferay-ui:panel:persistState", String.valueOf(_persistState));
		request.setAttribute(
			"liferay-ui:panel:extended", String.valueOf(_extended));
		request.setAttribute("liferay-ui:panel:cssClass", _cssClass);

		super.doStartTag();

		return EVAL_BODY_INCLUDE;
	}

	public void setCollapsible(boolean collapsible) {
		_collapsible = collapsible;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setDefaultState(String defaultState) {
		_defaultState = defaultState;
	}

	public void setEndPage(String endPage) {
		_endPage = endPage;
	}

	public void setExtended(boolean extended) {
		_extended = extended;
	}

	public void setHelpMessage(String helpMessage) {
		_helpMessage = helpMessage;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setPersistState(boolean persistState) {
		_persistState = persistState;
	}

	public void setStartPage(String startPage) {
		_startPage = startPage;
	}

	public void setTitle(String title) {
		_title = title;
	}

	@Override
	protected String getEndPage() {
		if (Validator.isNull(_endPage)) {
			return _END_PAGE;
		}
		else {
			return _endPage;
		}
	}

	@Override
	protected String getStartPage() {
		if (Validator.isNull(_startPage)) {
			return _START_PAGE;
		}
		else {
			return _startPage;
		}
	}

	private static final String _END_PAGE = "/html/taglib/ui/panel/end.jsp";

	private static final String _START_PAGE = "/html/taglib/ui/panel/start.jsp";

	private boolean _collapsible = true;
	private String _cssClass = StringPool.BLANK;
	private String _defaultState = "open";
	private String _endPage;
	private boolean _extended;
	private String _helpMessage;
	private String _id;
	private boolean _persistState = true;
	private String _startPage;
	private String _title;

}