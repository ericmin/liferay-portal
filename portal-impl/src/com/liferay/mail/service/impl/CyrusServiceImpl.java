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

package com.liferay.mail.service.impl;

import com.liferay.mail.NoSuchCyrusUserException;
import com.liferay.mail.model.CyrusUser;
import com.liferay.mail.model.CyrusVirtual;
import com.liferay.mail.service.CyrusService;
import com.liferay.mail.service.persistence.CyrusUserUtil;
import com.liferay.mail.service.persistence.CyrusVirtualUtil;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Alexander Chow
 */
public class CyrusServiceImpl implements CyrusService, IdentifiableBean {

	public void addUser(long userId, String emailAddress, String password)
		throws SystemException {

		// User

		CyrusUser user = new CyrusUser(userId, password);

		CyrusUserUtil.update(user);

		// Virtual

		CyrusVirtual virtual = new CyrusVirtual(emailAddress, userId);

		CyrusVirtualUtil.update(virtual);
	}

	public void deleteEmailAddress(long companyId, long userId)
		throws SystemException {

		CyrusVirtualUtil.removeByUserId(userId);
	}

	public void deleteUser(long userId) throws SystemException {

		// User

		try {
			CyrusUserUtil.remove(userId);
		}
		catch (NoSuchCyrusUserException nscue) {
		}

		// Virtual

		CyrusVirtualUtil.removeByUserId(userId);
	}

	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	public void updateEmailAddress(
			long companyId, long userId, String emailAddress)
		throws SystemException {

		CyrusVirtualUtil.removeByUserId(userId);

		CyrusVirtual virtual = new CyrusVirtual(emailAddress, userId);

		CyrusVirtualUtil.update(virtual);
	}

	public void updatePassword(long companyId, long userId, String password)
		throws SystemException {

		CyrusUser user = null;

		try {
			user = CyrusUserUtil.findByPrimaryKey(userId);
		}
		catch (NoSuchCyrusUserException nscue) {
			user = new CyrusUser(userId, password);
		}

		user.setPassword(password);

		CyrusUserUtil.update(user);
	}

	private String _beanIdentifier;

}