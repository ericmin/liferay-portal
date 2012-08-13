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

package com.liferay.util.bridges.jsf.sun;

import com.sun.faces.RIConstants;
import com.sun.faces.application.ApplicationAssociate;
import com.sun.faces.config.ConfigureListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * @author Brian Myunghun Kim
 */
public class LiferayConfigureListener extends ConfigureListener {

	public static final String ASSOCIATE_KEY =
		RIConstants.FACES_PREFIX + "ApplicationAssociate";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();

		super.contextInitialized(sce);

		ApplicationAssociate associate = ApplicationAssociate.getInstance(
			new LiferayServletContextAdapter(sc));

		if (associate != null) {
			sc.setAttribute(ASSOCIATE_KEY, associate);
		}
	}

}