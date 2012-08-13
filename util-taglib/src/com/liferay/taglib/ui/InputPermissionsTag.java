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

import com.liferay.portal.kernel.servlet.PortalIncludeUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.taglib.util.IncludeTag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 * @author Wilson S. Man
 * @see    com.liferay.portal.servlet.taglib.ui.InputPermissionsTagUtil
 */
public class InputPermissionsTag extends IncludeTag {

	public static String doTag(
			String formName, String modelName, PageContext pageContext)
		throws Exception {

		return doTag(_PAGE, formName, modelName, pageContext);
	}

	public static String doTag(
			String page, String formName, String modelName,
			PageContext pageContext)
		throws Exception {

		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		request.setAttribute("liferay-ui:input-permissions:formName", formName);

		if (modelName != null) {
			List<String> supportedActions =
				ResourceActionsUtil.getModelResourceActions(modelName);
			List<String> groupDefaultActions =
				ResourceActionsUtil.getModelResourceGroupDefaultActions(
					modelName);
			List<String> guestDefaultActions =
				ResourceActionsUtil.getModelResourceGuestDefaultActions(
					modelName);
			List<String> guestUnsupportedActions =
				ResourceActionsUtil.getModelResourceGuestUnsupportedActions(
					modelName);

			request.setAttribute(
				"liferay-ui:input-permissions:modelName", modelName);
			request.setAttribute(
				"liferay-ui:input-permissions:supportedActions",
				supportedActions);
			request.setAttribute(
				"liferay-ui:input-permissions:groupDefaultActions",
				groupDefaultActions);
			request.setAttribute(
				"liferay-ui:input-permissions:guestDefaultActions",
				guestDefaultActions);
			request.setAttribute(
				"liferay-ui:input-permissions:guestUnsupportedActions",
				guestUnsupportedActions);
		}

		PortalIncludeUtil.include(pageContext, page);

		return StringPool.BLANK;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			doTag(getPage(), _formName, _modelName, pageContext);

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	public void setFormName(String formName) {
		_formName = formName;
	}

	public void setModelName(String modelName) {
		_modelName = modelName;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	private static final String _PAGE =
		"/html/taglib/ui/input_permissions/page.jsp";

	private String _formName = "fm";
	private String _modelName;

}