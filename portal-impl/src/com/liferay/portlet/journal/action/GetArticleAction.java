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
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.ProcessingInstruction;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.dynamicdatamapping.util.DDMXMLUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.model.JournalTemplateConstants;
import com.liferay.portlet.journal.service.JournalArticleServiceUtil;
import com.liferay.portlet.journal.service.JournalTemplateServiceUtil;
import com.liferay.portlet.journal.util.JournalUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Raymond Augé
 */
public class GetArticleAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		try {
			long groupId = ParamUtil.getLong(request, "groupId");
			String articleId = ParamUtil.getString(request, "articleId");

			String languageId = LanguageUtil.getLanguageId(request);

			JournalArticle article = JournalArticleServiceUtil.getLatestArticle(
				groupId, articleId, WorkflowConstants.STATUS_APPROVED);

			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			Map<String, String> tokens = JournalUtil.getTokens(
				groupId, themeDisplay);

			String xml = article.getContentByLocale(languageId);

			Document doc = SAXReaderUtil.read(xml);

			Element root = doc.getRootElement();

			addProcessingInstructions(doc, request, themeDisplay, article);

			JournalUtil.addAllReservedEls(root, tokens, article, languageId);

			xml = DDMXMLUtil.formatXML(doc);

			String contentType = ContentTypes.TEXT_XML_UTF8;

			String fileName = null;
			byte[] bytes = xml.getBytes();

			ServletResponseUtil.sendFile(
				request, response, fileName, bytes, contentType);

			return null;
		}
		catch (Exception e) {
			PortalUtil.sendError(e, request, response);

			return null;
		}
	}

	protected void addProcessingInstructions(
		Document doc, HttpServletRequest request, ThemeDisplay themeDisplay,
		JournalArticle article) {

		// Add style sheets in the reverse order that they appear in the
		// document

		// Portal CSS

		String url = PortalUtil.getStaticResourceURL(
			request,
			themeDisplay.getCDNDynamicResourcesHost() +
				themeDisplay.getPathContext() + "/html/portal/css.jsp");

		Map<String, String> arguments = new LinkedHashMap<String, String>();

		arguments.put("type", "text/css");
		arguments.put("href", url);
		arguments.put("title", "portal css");
		arguments.put("alternate", "yes");

		addStyleSheet(doc, url, arguments);

		// Theme CSS

		url = PortalUtil.getStaticResourceURL(
			request, themeDisplay.getPathThemeCss() + "/main.css");

		arguments.clear();

		arguments.put("type", "text/css");
		arguments.put("href", url);
		arguments.put("title", "theme css");

		addStyleSheet(doc, url, arguments);

		// XSL template

		String templateId = article.getTemplateId();

		if (Validator.isNotNull(templateId)) {
			JournalTemplate template = null;

			try {
				template = JournalTemplateServiceUtil.getTemplate(
					article.getGroupId(), templateId);

				if (Validator.equals(
						template.getLangType(),
						JournalTemplateConstants.LANG_TYPE_XSL)) {

					url =
						themeDisplay.getPathMain() +
							"/journal/get_template?groupId=" +
								article.getGroupId() + "&templateId=" +
									templateId;

					arguments.clear();

					arguments.put("type", "text/xsl");
					arguments.put("href", url);
					arguments.put("title", "xsl");

					addStyleSheet(doc, url, arguments);
				}
			}
			catch (Exception e) {
			}
		}
	}

	protected void addStyleSheet(
		Document doc, String url, Map<String, String> arguments) {

		List<Node> content = doc.content();

		ProcessingInstruction processingInstruction =
			SAXReaderUtil.createProcessingInstruction(
				"xml-stylesheet", arguments);

		content.add(0, processingInstruction);
	}

}