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

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;

import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.myfaces.context.servlet.ServletFacesContextImpl;

/**
 * @author Brian Myunghun Kim
 */
public class MyFacesContextFactoryImpl extends FacesContextFactory {

	@Override
	public FacesContext getFacesContext(
			Object context, Object request, Object response,
			Lifecycle lifecycle)
		throws FacesException {

		if (context == null) {
			throw new NullPointerException("context");
		}

		if (request == null) {
			throw new NullPointerException("request");
		}

		if (response == null) {
			throw new NullPointerException("response");
		}

		if (lifecycle == null) {
			throw new NullPointerException("lifecycle");
		}

		if (context instanceof ServletContext) {
			return new ServletFacesContextImpl(
				(ServletContext)context, (ServletRequest)request,
				(ServletResponse)response);
		}

		if (context instanceof PortletContext) {
			return new MyFacesContextImpl(
				(PortletContext)context, (PortletRequest)request,
				(PortletResponse)response);
		}

		throw new FacesException(
			"Unsupported context type " + getClass().getName());
	}

}