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

import com.liferay.portal.kernel.concurrent.LockRegistry;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.PortalPreferences;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.service.base.PortalPreferencesLocalServiceBaseImpl;
import com.liferay.portlet.PortalPreferencesImpl;
import com.liferay.portlet.PortalPreferencesWrapper;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.PortletPreferencesThreadLocal;

import java.util.concurrent.locks.Lock;

import javax.portlet.PortletPreferences;

/**
 * @author Alexander Chow
 */
public class PortalPreferencesLocalServiceImpl
	extends PortalPreferencesLocalServiceBaseImpl {

	public PortalPreferences addPortalPreferences(
			long companyId, long ownerId, int ownerType,
			String defaultPreferences)
		throws SystemException {

		long portalPreferencesId = counterLocalService.increment();

		PortalPreferences portalPreferences =
			portalPreferencesPersistence.create(portalPreferencesId);

		portalPreferences.setOwnerId(ownerId);
		portalPreferences.setOwnerType(ownerType);

		if (Validator.isNull(defaultPreferences)) {
			defaultPreferences = PortletConstants.DEFAULT_PREFERENCES;
		}

		portalPreferences.setPreferences(defaultPreferences);

		try {
			portalPreferencesPersistence.update(portalPreferences, false);
		}
		catch (SystemException se) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Add failed, fetch {ownerId=" + ownerId + ", ownerType=" +
						ownerType + "}");
			}

			portalPreferences = portalPreferencesPersistence.fetchByO_O(
				ownerId, ownerType, false);

			if (portalPreferences == null) {
				throw se;
			}
		}

		return portalPreferences;
	}

	public PortletPreferences getPreferences(
			long companyId, long ownerId, int ownerType)
		throws SystemException {

		return getPreferences(companyId, ownerId, ownerType, null);
	}

	public PortletPreferences getPreferences(
			long companyId, long ownerId, int ownerType,
			String defaultPreferences)
		throws SystemException {

		DB db = DBFactoryUtil.getDB();

		String dbType = db.getType();

		if (!dbType.equals(DB.TYPE_HYPERSONIC)) {
			return doGetPreferences(
				companyId, ownerId, ownerType, defaultPreferences);
		}

		StringBundler sb = new StringBundler(4);

		sb.append(ownerId);
		sb.append(StringPool.POUND);
		sb.append(ownerType);
		sb.append(StringPool.POUND);

		String groupName = getClass().getName();
		String key = sb.toString();

		Lock lock = LockRegistry.allocateLock(groupName, key);

		lock.lock();

		try {
			return doGetPreferences(
				companyId, ownerId, ownerType, defaultPreferences);
		}
		finally {
			lock.unlock();

			LockRegistry.freeLock(groupName, key);
		}
	}

	public PortalPreferences updatePreferences(
			long ownerId, int ownerType,
			com.liferay.portlet.PortalPreferences portalPreferences)
		throws SystemException {

		String xml = PortletPreferencesFactoryUtil.toXML(portalPreferences);

		return updatePreferences(ownerId, ownerType, xml);
	}

	public PortalPreferences updatePreferences(
			long ownerId, int ownerType, String xml)
		throws SystemException {

		PortalPreferences portalPreferences =
			portalPreferencesPersistence.fetchByO_O(ownerId, ownerType);

		if (portalPreferences == null) {
			long portalPreferencesId = counterLocalService.increment();

			portalPreferences = portalPreferencesPersistence.create(
				portalPreferencesId);

			portalPreferences.setOwnerId(ownerId);
			portalPreferences.setOwnerType(ownerType);
		}

		portalPreferences.setPreferences(xml);

		portalPreferencesPersistence.update(portalPreferences, false);

		return portalPreferences;
	}

	protected PortletPreferences doGetPreferences(
			long companyId, long ownerId, int ownerType,
			String defaultPreferences)
		throws SystemException {

		PortalPreferences portalPreferences =
			portalPreferencesPersistence.fetchByO_O(ownerId, ownerType);

		if (portalPreferences == null) {
			if (PortletPreferencesThreadLocal.isStrict() &&
				Validator.isNull(defaultPreferences)) {

				return new PortalPreferencesWrapper(
					new PortalPreferencesImpl());
			}

			portalPreferences =
				portalPreferencesLocalService.addPortalPreferences(
					companyId, ownerId, ownerType, defaultPreferences);
		}

		PortalPreferencesImpl portalPreferencesImpl =
			(PortalPreferencesImpl)PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType,
				portalPreferences.getPreferences());

		return new PortalPreferencesWrapper(portalPreferencesImpl);
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortalPreferencesLocalServiceImpl.class);

}