/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.layoutconfiguration.util.xml;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portlet.layoutconfiguration.util.RuntimePortletUtil;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletLogic extends RuntimeLogic {

	public static final String CLOSE_1_TAG = "</runtime-portlet>";

	public static final String CLOSE_2_TAG = "/>";

	public static final String OPEN_TAG = "<runtime-portlet";

	public static String getRuntimePortletIds(String content) throws Exception {
		StringBundler sb = new StringBundler();

		int index = 0;

		while (index != -1) {
			index = content.indexOf(OPEN_TAG, index);

			if (index != -1) {
				int close1 = content.indexOf(CLOSE_1_TAG, index);
				int close2 = content.indexOf(CLOSE_2_TAG, index);

				int closeIndex;

				if ((close2 == -1) || ((close1 != -1) && (close1 < close2))) {
					closeIndex = close1 + CLOSE_1_TAG.length();
				}
				else {
					closeIndex = close2 + CLOSE_2_TAG.length();
				}

				if (closeIndex != -1) {
					if (sb.length() > 0) {
						sb.append(StringPool.COMMA);
					}

					sb.append(
						getRuntimePortletId(
							content.substring(index, closeIndex)));
				}

				index = closeIndex;
			}
		}

		if (sb.length() == 0) {
			return null;
		}

		return sb.toString();
	}

	public PortletLogic(
		ServletContext servletContext, HttpServletRequest request,
		HttpServletResponse response, RenderRequest renderRequest,
		RenderResponse renderResponse) {

		_servletContext = servletContext;
		_request = request;
		_response = response;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
	}

	@Override
	public String getClose1Tag() {
		return CLOSE_1_TAG;
	}

	@Override
	public String getOpenTag() {
		return OPEN_TAG;
	}

	@Override
	public String processXML(String xml) throws Exception {
		Document doc = SAXReaderUtil.read(xml);

		Element root = doc.getRootElement();

		String rootPortletId = root.attributeValue("name");
		String instanceId = root.attributeValue("instance");
		String queryString = root.attributeValue("queryString");

		String portletId = rootPortletId;

		if (Validator.isNotNull(instanceId)) {
			portletId += PortletConstants.INSTANCE_SEPARATOR + instanceId;
		}

		return RuntimePortletUtil.processPortlet(
			_servletContext, _request, _response, _renderRequest,
			_renderResponse, portletId, queryString, false);
	}

	protected static String getRuntimePortletId(String xml) throws Exception {
		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		String rootPortletId = rootElement.attributeValue("name");
		String instanceId = rootElement.attributeValue("instance");

		String portletId = rootPortletId;

		if (Validator.isNotNull(instanceId)) {
			portletId += PortletConstants.INSTANCE_SEPARATOR + instanceId;
		}

		return portletId;
	}

	private RenderRequest _renderRequest;
	private RenderResponse _renderResponse;
	private HttpServletRequest _request;
	private HttpServletResponse _response;
	private ServletContext _servletContext;

}