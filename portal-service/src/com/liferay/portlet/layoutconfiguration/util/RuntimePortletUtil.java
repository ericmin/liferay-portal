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

package com.liferay.portlet.layoutconfiguration.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.Portlet;
import com.liferay.portlet.layoutconfiguration.util.xml.RuntimeLogic;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Shuyang Zhou
 */
public class RuntimePortletUtil {

	public static RuntimePortlet getRuntimePortlet() {
		PortalRuntimePermission.checkGetBeanProperty(RuntimePortletUtil.class);

		return _runtimePortlet;
	}

	public static String processCustomizationSettings(
			ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response, PageContext pageContext,
			String velocityTemplateId, String velocityTemplateContent)
		throws Exception {

		return getRuntimePortlet().processCustomizationSettings(
			servletContext, request, response, pageContext, velocityTemplateId,
			velocityTemplateContent);
	}

	public static String processPortlet(
			ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response, Portlet portlet, String queryString,
			String columnId, Integer columnPos, Integer columnCount,
			String path, boolean writeOutput)
		throws Exception {

		return getRuntimePortlet().processPortlet(
			servletContext, request, response, portlet, queryString, columnId,
			columnPos, columnCount, path, writeOutput);
	}

	public static String processPortlet(
			ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response, RenderRequest renderRequest,
			RenderResponse renderResponse, Portlet portlet, String portletId,
			String queryString, String columnId, Integer columnPos,
			Integer columnCount, String path, boolean writeOutput)
		throws Exception {

		return getRuntimePortlet().processPortlet(
			servletContext, request, response, renderRequest, renderResponse,
			portlet, portletId, queryString, columnId, columnPos, columnCount,
			path, writeOutput);
	}

	public static String processPortlet(
			ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response, RenderRequest renderRequest,
			RenderResponse renderResponse, String portletId, String queryString,
			boolean writeOutput)
		throws Exception {

		return getRuntimePortlet().processPortlet(
			servletContext, request, response, renderRequest, renderResponse,
			portletId, queryString, writeOutput);
	}

	public static String processPortlet(
			ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response, RenderRequest renderRequest,
			RenderResponse renderResponse, String portletId, String queryString,
			String columnId, Integer columnPos, Integer columnCount,
			boolean writeOutput)
		throws Exception {

		return getRuntimePortlet().processPortlet(
			servletContext, request, response, renderRequest, renderResponse,
			portletId, queryString, columnId, columnPos, columnCount,
			writeOutput);
	}

	public static void processTemplate(
			ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response, PageContext pageContext,
			JspWriter jspWriter, String velocityTemplateId,
			String velocityTemplateContent)
		throws Exception {

		getRuntimePortlet().processTemplate(
			servletContext, request, response, pageContext, jspWriter,
			velocityTemplateId, velocityTemplateContent);
	}

	public static void processTemplate(
			ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response, PageContext pageContext,
			JspWriter jspWriter, String portletId, String velocityTemplateId,
			String velocityTemplateContent)
		throws Exception {

		getRuntimePortlet().processTemplate(
			servletContext, request, response, pageContext, jspWriter,
			portletId, velocityTemplateId, velocityTemplateContent);
	}

	public static String processXML(
			HttpServletRequest request, String content,
			RuntimeLogic runtimeLogic)
		throws Exception {

		return getRuntimePortlet().processXML(request, content, runtimeLogic);
	}

	public static String processXML(
			ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response, RenderRequest renderRequest,
			RenderResponse renderResponse, String content)
		throws Exception {

		return getRuntimePortlet().processXML(
			servletContext, request, response, renderRequest, renderResponse,
			content);
	}

	public void setRuntimePortlet(RuntimePortlet runtimePortlet) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_runtimePortlet = runtimePortlet;
	}

	private static RuntimePortlet _runtimePortlet;

}