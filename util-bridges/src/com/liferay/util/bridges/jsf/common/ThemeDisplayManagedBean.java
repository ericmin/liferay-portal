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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import javax.faces.context.FacesContext;

/**
 * <p>
 * This class is designed to be a convenient JSF managed bean that can be used
 * to get portal related information (such as the user's time zone).
 * </p>
 *
 * @author Neil Griffin
 */
public class ThemeDisplayManagedBean {

	public User getUser() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		String remoteUser = facesContext.getExternalContext().getRemoteUser();

		try {
			long userId = GetterUtil.getLong(remoteUser);

			return UserLocalServiceUtil.getUserById(userId);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(User.class);

}