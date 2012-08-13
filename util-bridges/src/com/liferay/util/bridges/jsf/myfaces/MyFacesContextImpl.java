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

package com.liferay.util.bridges.jsf.myfaces;

import javax.faces.context.ResponseWriter;

import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.apache.myfaces.context.ReleaseableExternalContext;
import org.apache.myfaces.context.servlet.ServletFacesContextImpl;

/**
 * @author Brian Myunghun Kim
 */
public class MyFacesContextImpl extends ServletFacesContextImpl {

	public MyFacesContextImpl(
		PortletContext portletContext, PortletRequest portletRequest,
		PortletResponse portletResponse) {

		super(portletContext, portletRequest, portletResponse);
	}

	@Override
	public ResponseWriter getResponseWriter() {
		return _responseWriter;
	}

	@Override
	public void release() {
		super.release();

		_responseWriter = null;
	}

	@Override
	public void setExternalContext(ReleaseableExternalContext extContext) {
		_responseWriter = null;

		super.setExternalContext(extContext);
	}

	@Override
	public void setResponseWriter(ResponseWriter responseWriter) {
		if (responseWriter == null) {
			throw new NullPointerException("responseWriter");
		}

		_responseWriter = responseWriter;
	}

	private ResponseWriter _responseWriter = null;

}