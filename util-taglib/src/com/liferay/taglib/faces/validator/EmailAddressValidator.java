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

package com.liferay.taglib.faces.validator;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.StateHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.commons.validator.EmailValidator;

/**
 * @author Neil Griffin
 */
public class EmailAddressValidator
	implements StateHolder, javax.faces.validator.Validator {

	public boolean isTransient() {
		return _transient;
	}

	public void restoreState(FacesContext facesContext, Object obj) {
	}

	public Object saveState(FacesContext facesContext) {
		return null;
	}

	public void setTransient(boolean value) {
		_transient = value;
	}

	public void validate(
			FacesContext facesContext, UIComponent uiComponent, Object obj)
		throws ValidatorException {

		ExternalContext externalContext = facesContext.getExternalContext();

		Locale locale = externalContext.getRequestLocale();

		if (obj instanceof String) {
			String emailAddress = (String)obj;

			if (Validator.isNotNull(emailAddress)) {
				if (!EmailValidator.getInstance().isValid(emailAddress)) {
					String summary = LanguageUtil.get(
						locale, "please-enter-a-valid-email-address");

					FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, summary, null);

					throw new ValidatorException(facesMessage);
				}
			}
		}
		else {
			String summary = LanguageUtil.format(
				locale,
				"validator-expected-type-string,-but-instead-received-type-x",
				obj.getClass().getName());

			FacesMessage facesMessage = new FacesMessage(
				FacesMessage.SEVERITY_ERROR, summary, null);

			throw new ValidatorException(facesMessage);
		}
	}

	private boolean _transient;

}