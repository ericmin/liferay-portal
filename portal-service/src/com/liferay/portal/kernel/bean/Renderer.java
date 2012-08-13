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

package com.liferay.portal.kernel.bean;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Raymond Augé
 */
public interface Renderer {

	public static final String RENDERER_TEMPLATE_PREFIX = "renderer.template.";

	public String renderBean(
			HttpServletRequest request, HttpServletResponse response,
			Object bean)
		throws RendererException;

	public String renderBean(
			HttpServletRequest request, HttpServletResponse response,
			Object bean, String varientSuffix)
		throws RendererException;

	public String renderBean(
			HttpServletRequest request, HttpServletResponse response,
			String servletContextName, Object bean)
		throws RendererException;

	public String renderBean(
			HttpServletRequest request, HttpServletResponse response,
			String servletContextName, Object bean, String varientSuffix)
		throws RendererException;

	public String renderBean(
			PortletRequest portletRequest, PortletResponse portletResponse,
			Object bean)
		throws RendererException;

	public String renderBean(
			PortletRequest portletRequest, PortletResponse portletResponse,
			Object bean, String varientSuffix)
		throws RendererException;

	public String renderBean(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String servletContextName, Object bean)
		throws RendererException;

	public String renderBean(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String servletContextName, Object bean, String varientSuffix)
		throws RendererException;

	public String renderEntity(
			HttpServletRequest request, HttpServletResponse response,
			String className, Object classPK)
		throws RendererException;

	public String renderEntity(
			HttpServletRequest request, HttpServletResponse response,
			String className, Object classPK, String varientSuffix)
		throws RendererException;

	public String renderEntity(
			HttpServletRequest request, HttpServletResponse response,
			String servletContextName, String className, Object classPK)
		throws RendererException;

	public String renderEntity(
			HttpServletRequest request, HttpServletResponse response,
			String servletContextName, String className, Object classPK,
			String varientSuffix)
		throws RendererException;

	public String renderEntity(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String className, Object classPK)
		throws RendererException;

	public String renderEntity(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String className, Object classPK, String varientSuffix)
		throws RendererException;

	public String renderEntity(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String servletContextName, String className, Object classPK)
		throws RendererException;

	public String renderEntity(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String servletContextName, String className, Object classPK,
			String varientSuffix)
		throws RendererException;

}