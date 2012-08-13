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

package com.liferay.taglib.faces.util;

import com.liferay.portal.kernel.util.Validator;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

/**
 * @author Neil Griffin
 */
public class JSFTagUtil {

	public static String eval(String expr) {
		if (Validator.isNotNull(expr) &&
			UIComponentTag.isValueReference(expr)) {

			FacesContext facesContext = FacesContext.getCurrentInstance();

			Application application = facesContext.getApplication();
			ValueBinding valueBinding = application.createValueBinding(expr);

			expr = String.valueOf(valueBinding.getValue(facesContext));
		}

		return expr;
	}

}