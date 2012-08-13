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

package com.liferay.portal.lar;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.UserIdStrategy;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.persistence.UserUtil;

import java.util.List;

/**
 * @author Bruno Farache
 */
public class CurrentUserIdStrategy implements UserIdStrategy {

	public CurrentUserIdStrategy(User user) {
		_user = user;
	}

	public long getUserId(String userUuid) throws SystemException {
		if (Validator.isNull(userUuid)) {
			return _user.getUserId();
		}

		List<User> users = UserUtil.findByUuid(userUuid);

		for (User user : users) {
			if (user.getCompanyId() == _user.getCompanyId()) {
				return user.getUserId();
			}
		}

		return _user.getUserId();
	}

	private User _user;

}