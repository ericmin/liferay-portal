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

package com.liferay.portlet.documentlibrary.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata;
import com.liferay.portlet.documentlibrary.service.DLFileEntryMetadataLocalServiceUtil;

/**
 * The extended model base implementation for the DLFileEntryMetadata service. Represents a row in the &quot;DLFileEntryMetadata&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DLFileEntryMetadataImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryMetadataImpl
 * @see com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata
 * @generated
 */
public abstract class DLFileEntryMetadataBaseImpl
	extends DLFileEntryMetadataModelImpl implements DLFileEntryMetadata {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a document library file entry metadata model instance should use the {@link DLFileEntryMetadata} interface instead.
	 */
	public void persist() throws SystemException {
		if (this.isNew()) {
			DLFileEntryMetadataLocalServiceUtil.addDLFileEntryMetadata(this);
		}
		else {
			DLFileEntryMetadataLocalServiceUtil.updateDLFileEntryMetadata(this);
		}
	}
}