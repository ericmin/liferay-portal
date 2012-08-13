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

package com.liferay.portlet.dynamicdatamapping.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.dynamicdatamapping.model.DDMStorageLink;

import java.io.Serializable;

/**
 * The cache model class for representing DDMStorageLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DDMStorageLink
 * @generated
 */
public class DDMStorageLinkCacheModel implements CacheModel<DDMStorageLink>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", storageLinkId=");
		sb.append(storageLinkId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", structureId=");
		sb.append(structureId);
		sb.append("}");

		return sb.toString();
	}

	public DDMStorageLink toEntityModel() {
		DDMStorageLinkImpl ddmStorageLinkImpl = new DDMStorageLinkImpl();

		if (uuid == null) {
			ddmStorageLinkImpl.setUuid(StringPool.BLANK);
		}
		else {
			ddmStorageLinkImpl.setUuid(uuid);
		}

		ddmStorageLinkImpl.setStorageLinkId(storageLinkId);
		ddmStorageLinkImpl.setClassNameId(classNameId);
		ddmStorageLinkImpl.setClassPK(classPK);
		ddmStorageLinkImpl.setStructureId(structureId);

		ddmStorageLinkImpl.resetOriginalValues();

		return ddmStorageLinkImpl;
	}

	public String uuid;
	public long storageLinkId;
	public long classNameId;
	public long classPK;
	public long structureId;
}