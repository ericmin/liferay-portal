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

package com.liferay.portlet.dynamicdatamapping.storage;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.dynamicdatamapping.StorageException;
import com.liferay.portlet.dynamicdatamapping.StorageFieldNameException;
import com.liferay.portlet.dynamicdatamapping.StorageFieldRequiredException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStorageLink;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStorageLinkLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.storage.query.Condition;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public abstract class BaseStorageAdapter implements StorageAdapter {

	public long create(
			long companyId, long ddmStructureId, Fields fields,
			ServiceContext serviceContext)
		throws StorageException {

		try {
			validateDDMStructureFields(ddmStructureId, fields);

			return doCreate(companyId, ddmStructureId, fields, serviceContext);
		}
		catch (StorageException se) {
			throw se;
		}
		catch (Exception e) {
			throw new StorageException(e);
		}
	}

	public void deleteByClass(long classPK) throws StorageException {
		try {
			doDeleteByClass(classPK);
		}
		catch (StorageException se) {
			throw se;
		}
		catch (Exception e) {
			throw new StorageException(e);
		}
	}

	public void deleteByDDMStructure(long ddmStructureId)
		throws StorageException {

		try {
			doDeleteByDDMStructure(ddmStructureId);
		}
		catch (StorageException se) {
			throw se;
		}
		catch (Exception e) {
			throw new StorageException(e);
		}
	}

	public Fields getFields(long classPK) throws StorageException {
		return getFields(classPK, null);
	}

	public Fields getFields(long classPK, List<String> fieldNames)
		throws StorageException {

		try {
			DDMStorageLink ddmStorageLink =
				DDMStorageLinkLocalServiceUtil.getClassStorageLink(classPK);

			Map<Long, Fields> fieldsMapByClasses = getFieldsMap(
				ddmStorageLink.getStructureId(), new long[] {classPK},
				fieldNames);

			return fieldsMapByClasses.get(classPK);
		}
		catch (StorageException se) {
			throw se;
		}
		catch (Exception e) {
			throw new StorageException(e);
		}
	}

	public List<Fields> getFieldsList(
			long ddmStructureId, List<String> fieldNames)
		throws StorageException {

		return getFieldsList(ddmStructureId, fieldNames, null);
	}

	public List<Fields> getFieldsList(
			long ddmStructureId, List<String> fieldNames,
			OrderByComparator orderByComparator)
		throws StorageException {

		try {
			return doGetFieldsListByDDMStructure(
				ddmStructureId, fieldNames, orderByComparator);
		}
		catch (StorageException se) {
			throw se;
		}
		catch (Exception e) {
			throw new StorageException(e);
		}
	}

	public List<Fields> getFieldsList(
			long ddmStructureId, long[] classPKs, List<String> fieldNames,
			OrderByComparator orderByComparator)
		throws StorageException {

		try {
			return doGetFieldsListByClasses(
				ddmStructureId, classPKs, fieldNames, orderByComparator);
		}
		catch (StorageException se) {
			throw se;
		}
		catch (Exception e) {
			throw new StorageException(e);
		}
	}

	public List<Fields> getFieldsList(
			long ddmStructureId, long[] classPKs,
			OrderByComparator orderByComparator)
		throws StorageException {

		return getFieldsList(ddmStructureId, classPKs, null, orderByComparator);
	}

	public Map<Long, Fields> getFieldsMap(long ddmStructureId, long[] classPKs)
		throws StorageException {

		return getFieldsMap(ddmStructureId, classPKs, null);
	}

	public Map<Long, Fields> getFieldsMap(
			long ddmStructureId, long[] classPKs, List<String> fieldNames)
		throws StorageException {

		try {
			return doGetFieldsMapByClasses(
				ddmStructureId, classPKs, fieldNames);
		}
		catch (StorageException se) {
			throw se;
		}
		catch (Exception e) {
			throw new StorageException(e);
		}
	}

	public List<Fields> query(
			long ddmStructureId, List<String> fieldNames, Condition condition,
			OrderByComparator orderByComparator)
		throws StorageException {

		try {
			return doQuery(
				ddmStructureId, fieldNames, condition, orderByComparator);
		}
		catch (StorageException se) {
			throw se;
		}
		catch (Exception e) {
			throw new StorageException(e);
		}
	}

	public int queryCount(long ddmStructureId, Condition condition)
		throws StorageException {

		try {
			return doQueryCount(ddmStructureId, condition);
		}
		catch (StorageException se) {
			throw se;
		}
		catch (Exception e) {
			throw new StorageException(e);
		}
	}

	public void update(
			long classPK, Fields fields, boolean mergeFields,
			ServiceContext serviceContext)
		throws StorageException {

		try {
			validateClassFields(classPK, fields);

			doUpdate(classPK, fields, mergeFields, serviceContext);
		}
		catch (StorageException se) {
			throw se;
		}
		catch (Exception e) {
			throw new StorageException(e);
		}
	}

	public void update(
			long classPK, Fields fields, ServiceContext serviceContext)
		throws StorageException {

		update(classPK, fields, false, serviceContext);
	}

	protected abstract long doCreate(
			long companyId, long ddmStructureId, Fields fields,
			ServiceContext serviceContext)
		throws Exception;

	protected abstract void doDeleteByClass(long classPK) throws Exception;

	protected abstract void doDeleteByDDMStructure(long ddmStructureId)
		throws Exception;

	protected abstract List<Fields> doGetFieldsListByClasses(
			long ddmStructureId, long[] classPKs, List<String> fieldNames,
			OrderByComparator orderByComparator)
		throws Exception;

	protected abstract List<Fields> doGetFieldsListByDDMStructure(
			long ddmStructureId, List<String> fieldNames,
			OrderByComparator orderByComparator)
		throws Exception;

	protected abstract Map<Long, Fields> doGetFieldsMapByClasses(
			long ddmStructureId, long[] classPKs, List<String> fieldNames)
		throws Exception;

	protected abstract List<Fields> doQuery(
			long ddmStructureId, List<String> fieldNames, Condition condition,
			OrderByComparator orderByComparator)
		throws Exception;

	protected abstract int doQueryCount(
			long ddmStructureId, Condition condition)
		throws Exception;

	protected abstract void doUpdate(
			long classPK, Fields fields, boolean mergeFields,
			ServiceContext serviceContext)
		throws Exception;

	protected void validateClassFields(long classPK, Fields fields)
		throws PortalException, SystemException {

		DDMStorageLink ddmStorageLink =
			DDMStorageLinkLocalServiceUtil.getClassStorageLink(classPK);

		validateDDMStructureFields(ddmStorageLink.getStructureId(), fields);
	}

	protected void validateDDMStructureFields(
			long ddmStructureId, Fields fields)
		throws PortalException, SystemException {

		DDMStructure ddmStructure =
			DDMStructureLocalServiceUtil.getDDMStructure(ddmStructureId);

		Iterator<Field> itr = fields.iterator();

		while (itr.hasNext()) {
			Field field = itr.next();

			if (!ddmStructure.hasField(field.getName())) {
				throw new StorageFieldNameException();
			}

			if (ddmStructure.getFieldRequired(field.getName()) &&
				Validator.isNull(field.getValue())) {

				throw new StorageFieldRequiredException();
			}
		}
	}

}