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

package com.liferay.portlet.journal.action;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.model.JournalTemplateConstants;
import com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil;
import com.liferay.portlet.journal.util.JournalUtil;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class GetTemplateAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		try {
			long groupId = ParamUtil.getLong(request, "groupId");
			String templateId = getTemplateId(request);

			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			Map<String, String> tokens = JournalUtil.getTokens(
				groupId, themeDisplay);

			tokens.put("template_id", templateId);

			String languageId = LanguageUtil.getLanguageId(request);

			boolean transform = ParamUtil.getBoolean(
				request, "transform", true);

			JournalTemplate template =
				JournalTemplateLocalServiceUtil.getTemplate(
					groupId, templateId, true);

			String script = JournalUtil.getTemplateScript(
				template, tokens, languageId, transform);

			String extension = JournalTemplateConstants.LANG_TYPE_VM;

			if (template.getLangType() != null) {
				extension = template.getLangType();
			}

			String fileName = null;
			byte[] bytes = script.getBytes();

			String contentType = ContentTypes.TEXT_PLAIN_UTF8;

			if (Validator.equals(
					extension, JournalTemplateConstants.LANG_TYPE_CSS)) {

				contentType = ContentTypes.TEXT_CSS_UTF8;
			}
			else if (Validator.equals(
					extension, JournalTemplateConstants.LANG_TYPE_XSL)) {

				contentType = ContentTypes.TEXT_XML_UTF8;
			}

			ServletResponseUtil.sendFile(
				request, response, fileName, bytes, contentType);

			return null;
		}
		catch (Exception e) {
			PortalUtil.sendError(e, request, response);

			return null;
		}
	}

	protected String getTemplateId(HttpServletRequest request) {
		String templateId = ParamUtil.getString(request, "templateId");

		// Backwards compatibility

		if (Validator.isNull(templateId)) {
			templateId = ParamUtil.getString(request, "template_id");
		}

		return templateId;
	}

}