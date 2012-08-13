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

package com.liferay.util.bridges.jsf.common;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * <p>
 * This class provides a convenient way of building lists of JSF SelectItem
 * objects, and convenience method for operating against them.
 * </p>
 *
 * @author Neil Griffin
 */
public class SelectItemList extends ArrayList<SelectItem> {

	public void prependEmptySelectItem(FacesContext facesContext) {
		Locale locale = facesContext.getExternalContext().getRequestLocale();

		Object value = StringPool.BLANK;
		String label = LanguageUtil.get(locale, "select");

		add(0, new SelectItem(value, label));
	}

}