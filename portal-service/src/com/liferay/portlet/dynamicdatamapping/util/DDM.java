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

package com.liferay.portlet.dynamicdatamapping.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eduardo Lundgren
 */
public interface DDM {

	public Fields getFields(
			long ddmStructureId, long ddmTemplateId,
			ServiceContext serviceContext)
		throws PortalException, SystemException;

	public Fields getFields(
			long ddmStructureId, long ddmTemplateId, String fieldNamespace,
			ServiceContext serviceContext)
		throws PortalException, SystemException;

	public Fields getFields(long ddmStructureId, ServiceContext serviceContext)
		throws PortalException, SystemException;

	public Fields getFields(
			long ddmStructureId, String fieldNamespace,
			ServiceContext serviceContext)
		throws PortalException, SystemException;

	public String getFileUploadPath(BaseModel<?> baseModel);

	public void sendFieldFile(
			HttpServletRequest request, HttpServletResponse response,
			Field field)
		throws Exception;

	public String uploadFieldFile(
			long structureId, long storageId, BaseModel<?> baseModel,
			String fieldName, ServiceContext serviceContext)
		throws Exception;

	public String uploadFieldFile(
			long structureId, long storageId, BaseModel<?> baseModel,
			String fieldName, String fieldNamespace,
			ServiceContext serviceContext)
		throws Exception;

}