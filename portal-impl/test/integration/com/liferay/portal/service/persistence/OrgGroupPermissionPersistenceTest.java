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

package com.liferay.portal.service.persistence;

import com.liferay.portal.NoSuchOrgGroupPermissionException;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.OrgGroupPermission;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.service.persistence.PersistenceExecutionTestListener;
import com.liferay.portal.test.ExecutionTestListeners;
import com.liferay.portal.test.LiferayPersistenceIntegrationJUnitTestRunner;
import com.liferay.portal.test.persistence.TransactionalPersistenceAdvice;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
@ExecutionTestListeners(listeners =  {
	PersistenceExecutionTestListener.class})
@RunWith(LiferayPersistenceIntegrationJUnitTestRunner.class)
public class OrgGroupPermissionPersistenceTest {
	@After
	public void tearDown() throws Exception {
		Map<Serializable, BasePersistence<?>> basePersistences = _transactionalPersistenceAdvice.getBasePersistences();

		Set<Serializable> primaryKeys = basePersistences.keySet();

		for (Serializable primaryKey : primaryKeys) {
			BasePersistence<?> basePersistence = basePersistences.get(primaryKey);

			try {
				basePersistence.remove(primaryKey);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug("The model with primary key " + primaryKey +
						" was already deleted");
				}
			}
		}

		_transactionalPersistenceAdvice.reset();
	}

	@Test
	public void testCreate() throws Exception {
		OrgGroupPermissionPK pk = new OrgGroupPermissionPK(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextLong());

		OrgGroupPermission orgGroupPermission = _persistence.create(pk);

		Assert.assertNotNull(orgGroupPermission);

		Assert.assertEquals(orgGroupPermission.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		OrgGroupPermission newOrgGroupPermission = addOrgGroupPermission();

		_persistence.remove(newOrgGroupPermission);

		OrgGroupPermission existingOrgGroupPermission = _persistence.fetchByPrimaryKey(newOrgGroupPermission.getPrimaryKey());

		Assert.assertNull(existingOrgGroupPermission);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addOrgGroupPermission();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		OrgGroupPermissionPK pk = new OrgGroupPermissionPK(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextLong());

		OrgGroupPermission newOrgGroupPermission = _persistence.create(pk);

		_persistence.update(newOrgGroupPermission, false);

		OrgGroupPermission existingOrgGroupPermission = _persistence.findByPrimaryKey(newOrgGroupPermission.getPrimaryKey());

		Assert.assertEquals(existingOrgGroupPermission.getOrganizationId(),
			newOrgGroupPermission.getOrganizationId());
		Assert.assertEquals(existingOrgGroupPermission.getGroupId(),
			newOrgGroupPermission.getGroupId());
		Assert.assertEquals(existingOrgGroupPermission.getPermissionId(),
			newOrgGroupPermission.getPermissionId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		OrgGroupPermission newOrgGroupPermission = addOrgGroupPermission();

		OrgGroupPermission existingOrgGroupPermission = _persistence.findByPrimaryKey(newOrgGroupPermission.getPrimaryKey());

		Assert.assertEquals(existingOrgGroupPermission, newOrgGroupPermission);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		OrgGroupPermissionPK pk = new OrgGroupPermissionPK(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextLong());

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail(
				"Missing entity did not throw NoSuchOrgGroupPermissionException");
		}
		catch (NoSuchOrgGroupPermissionException nsee) {
		}
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		OrgGroupPermission newOrgGroupPermission = addOrgGroupPermission();

		OrgGroupPermission existingOrgGroupPermission = _persistence.fetchByPrimaryKey(newOrgGroupPermission.getPrimaryKey());

		Assert.assertEquals(existingOrgGroupPermission, newOrgGroupPermission);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		OrgGroupPermissionPK pk = new OrgGroupPermissionPK(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextLong());

		OrgGroupPermission missingOrgGroupPermission = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingOrgGroupPermission);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		OrgGroupPermission newOrgGroupPermission = addOrgGroupPermission();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(OrgGroupPermission.class,
				OrgGroupPermission.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.organizationId",
				newOrgGroupPermission.getOrganizationId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.groupId",
				newOrgGroupPermission.getGroupId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.permissionId",
				newOrgGroupPermission.getPermissionId()));

		List<OrgGroupPermission> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		OrgGroupPermission existingOrgGroupPermission = result.get(0);

		Assert.assertEquals(existingOrgGroupPermission, newOrgGroupPermission);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(OrgGroupPermission.class,
				OrgGroupPermission.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.organizationId",
				ServiceTestUtil.nextLong()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.groupId",
				ServiceTestUtil.nextLong()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.permissionId",
				ServiceTestUtil.nextLong()));

		List<OrgGroupPermission> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		OrgGroupPermission newOrgGroupPermission = addOrgGroupPermission();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(OrgGroupPermission.class,
				OrgGroupPermission.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.organizationId"));

		Object newOrganizationId = newOrgGroupPermission.getOrganizationId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.organizationId",
				new Object[] { newOrganizationId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingOrganizationId = result.get(0);

		Assert.assertEquals(existingOrganizationId, newOrganizationId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(OrgGroupPermission.class,
				OrgGroupPermission.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.organizationId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.organizationId",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected OrgGroupPermission addOrgGroupPermission()
		throws Exception {
		OrgGroupPermissionPK pk = new OrgGroupPermissionPK(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextLong());

		OrgGroupPermission orgGroupPermission = _persistence.create(pk);

		_persistence.update(orgGroupPermission, false);

		return orgGroupPermission;
	}

	private static Log _log = LogFactoryUtil.getLog(OrgGroupPermissionPersistenceTest.class);
	private OrgGroupPermissionPersistence _persistence = (OrgGroupPermissionPersistence)PortalBeanLocatorUtil.locate(OrgGroupPermissionPersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}