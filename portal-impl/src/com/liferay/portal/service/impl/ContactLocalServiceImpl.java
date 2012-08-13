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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Contact;
import com.liferay.portal.service.base.ContactLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class ContactLocalServiceImpl extends ContactLocalServiceBaseImpl {

	@Override
	public Contact deleteContact(Contact contact) throws SystemException {

		// Addresses

		addressLocalService.deleteAddresses(
			contact.getCompanyId(), Contact.class.getName(),
			contact.getContactId());

		// Email addresses

		emailAddressLocalService.deleteEmailAddresses(
			contact.getCompanyId(), Contact.class.getName(),
			contact.getContactId());

		// Phone

		phoneLocalService.deletePhones(
			contact.getCompanyId(), Contact.class.getName(),
			contact.getContactId());

		// Website

		websiteLocalService.deleteWebsites(
			contact.getCompanyId(), Contact.class.getName(),
			contact.getContactId());

		// Contact

		return contactPersistence.remove(contact);
	}

	@Override
	public Contact deleteContact(long contactId) throws SystemException {
		Contact contact = contactPersistence.fetchByPrimaryKey(contactId);

		if (contact != null) {
			deleteContact(contact);
		}

		return contact;
	}

}