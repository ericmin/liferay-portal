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
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eduardo Lundgren
 */
public class DDMUtil {

	public static DDM getDDM() {
		PortalRuntimePermission.checkGetBeanProperty(DDMUtil.class);

		return _ddm;
	}

	public static Fields getFields(
			long ddmStructureId, long ddmTemplateId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getDDM().getFields(
			ddmStructureId, ddmTemplateId, serviceContext);
	}

	public static Fields getFields(
			long ddmStructureId, long ddmTemplateId, String fieldNamespace,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getDDM().getFields(
			ddmStructureId, ddmTemplateId, fieldNamespace, serviceContext);
	}

	public static Fields getFields(
			long ddmStructureId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getDDM().getFields(ddmStructureId, serviceContext);
	}

	public static Fields getFields(
			long ddmStructureId, String fieldNamespace,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getDDM().getFields(
			ddmStructureId, fieldNamespace, serviceContext);
	}

	public static String getFileUploadPath(BaseModel<?> baseModel) {
		return getDDM().getFileUploadPath(baseModel);
	}

	public static void sendFieldFile(
			HttpServletRequest request, HttpServletResponse response,
			Field field)
		throws Exception {

		getDDM().sendFieldFile(request, response, field);
	}

	public static String uploadFieldFile(
			long structureId, long storageId, BaseModel<?> baseModel,
			String fieldName, ServiceContext serviceContext)
		throws Exception {

		return getDDM().uploadFieldFile(
			structureId, storageId, baseModel, fieldName, serviceContext);
	}

	public static String uploadFieldFile(
			long structureId, long storageId, BaseModel<?> baseModel,
			String fieldName, String fieldNamespace,
			ServiceContext serviceContext)
		throws Exception {

		return getDDM().uploadFieldFile(
			structureId, storageId, baseModel, fieldName, fieldNamespace,
			serviceContext);
	}

	public void setDDM(DDM ddm) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_ddm = ddm;
	}

	private static DDM _ddm;

}