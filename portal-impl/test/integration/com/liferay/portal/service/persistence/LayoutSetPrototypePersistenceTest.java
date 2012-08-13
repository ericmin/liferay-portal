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

import com.liferay.portal.NoSuchLayoutSetPrototypeException;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.model.LayoutSetPrototype;
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
public class LayoutSetPrototypePersistenceTest {
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
		long pk = ServiceTestUtil.nextLong();

		LayoutSetPrototype layoutSetPrototype = _persistence.create(pk);

		Assert.assertNotNull(layoutSetPrototype);

		Assert.assertEquals(layoutSetPrototype.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LayoutSetPrototype newLayoutSetPrototype = addLayoutSetPrototype();

		_persistence.remove(newLayoutSetPrototype);

		LayoutSetPrototype existingLayoutSetPrototype = _persistence.fetchByPrimaryKey(newLayoutSetPrototype.getPrimaryKey());

		Assert.assertNull(existingLayoutSetPrototype);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLayoutSetPrototype();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		LayoutSetPrototype newLayoutSetPrototype = _persistence.create(pk);

		newLayoutSetPrototype.setUuid(ServiceTestUtil.randomString());

		newLayoutSetPrototype.setCompanyId(ServiceTestUtil.nextLong());

		newLayoutSetPrototype.setCreateDate(ServiceTestUtil.nextDate());

		newLayoutSetPrototype.setModifiedDate(ServiceTestUtil.nextDate());

		newLayoutSetPrototype.setName(ServiceTestUtil.randomString());

		newLayoutSetPrototype.setDescription(ServiceTestUtil.randomString());

		newLayoutSetPrototype.setSettings(ServiceTestUtil.randomString());

		newLayoutSetPrototype.setActive(ServiceTestUtil.randomBoolean());

		_persistence.update(newLayoutSetPrototype, false);

		LayoutSetPrototype existingLayoutSetPrototype = _persistence.findByPrimaryKey(newLayoutSetPrototype.getPrimaryKey());

		Assert.assertEquals(existingLayoutSetPrototype.getUuid(),
			newLayoutSetPrototype.getUuid());
		Assert.assertEquals(existingLayoutSetPrototype.getLayoutSetPrototypeId(),
			newLayoutSetPrototype.getLayoutSetPrototypeId());
		Assert.assertEquals(existingLayoutSetPrototype.getCompanyId(),
			newLayoutSetPrototype.getCompanyId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLayoutSetPrototype.getCreateDate()),
			Time.getShortTimestamp(newLayoutSetPrototype.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingLayoutSetPrototype.getModifiedDate()),
			Time.getShortTimestamp(newLayoutSetPrototype.getModifiedDate()));
		Assert.assertEquals(existingLayoutSetPrototype.getName(),
			newLayoutSetPrototype.getName());
		Assert.assertEquals(existingLayoutSetPrototype.getDescription(),
			newLayoutSetPrototype.getDescription());
		Assert.assertEquals(existingLayoutSetPrototype.getSettings(),
			newLayoutSetPrototype.getSettings());
		Assert.assertEquals(existingLayoutSetPrototype.getActive(),
			newLayoutSetPrototype.getActive());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LayoutSetPrototype newLayoutSetPrototype = addLayoutSetPrototype();

		LayoutSetPrototype existingLayoutSetPrototype = _persistence.findByPrimaryKey(newLayoutSetPrototype.getPrimaryKey());

		Assert.assertEquals(existingLayoutSetPrototype, newLayoutSetPrototype);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail(
				"Missing entity did not throw NoSuchLayoutSetPrototypeException");
		}
		catch (NoSuchLayoutSetPrototypeException nsee) {
		}
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LayoutSetPrototype newLayoutSetPrototype = addLayoutSetPrototype();

		LayoutSetPrototype existingLayoutSetPrototype = _persistence.fetchByPrimaryKey(newLayoutSetPrototype.getPrimaryKey());

		Assert.assertEquals(existingLayoutSetPrototype, newLayoutSetPrototype);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		LayoutSetPrototype missingLayoutSetPrototype = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLayoutSetPrototype);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LayoutSetPrototype newLayoutSetPrototype = addLayoutSetPrototype();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutSetPrototype.class,
				LayoutSetPrototype.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("layoutSetPrototypeId",
				newLayoutSetPrototype.getLayoutSetPrototypeId()));

		List<LayoutSetPrototype> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LayoutSetPrototype existingLayoutSetPrototype = result.get(0);

		Assert.assertEquals(existingLayoutSetPrototype, newLayoutSetPrototype);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutSetPrototype.class,
				LayoutSetPrototype.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("layoutSetPrototypeId",
				ServiceTestUtil.nextLong()));

		List<LayoutSetPrototype> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LayoutSetPrototype newLayoutSetPrototype = addLayoutSetPrototype();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutSetPrototype.class,
				LayoutSetPrototype.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"layoutSetPrototypeId"));

		Object newLayoutSetPrototypeId = newLayoutSetPrototype.getLayoutSetPrototypeId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("layoutSetPrototypeId",
				new Object[] { newLayoutSetPrototypeId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLayoutSetPrototypeId = result.get(0);

		Assert.assertEquals(existingLayoutSetPrototypeId,
			newLayoutSetPrototypeId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutSetPrototype.class,
				LayoutSetPrototype.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"layoutSetPrototypeId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("layoutSetPrototypeId",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected LayoutSetPrototype addLayoutSetPrototype()
		throws Exception {
		long pk = ServiceTestUtil.nextLong();

		LayoutSetPrototype layoutSetPrototype = _persistence.create(pk);

		layoutSetPrototype.setUuid(ServiceTestUtil.randomString());

		layoutSetPrototype.setCompanyId(ServiceTestUtil.nextLong());

		layoutSetPrototype.setCreateDate(ServiceTestUtil.nextDate());

		layoutSetPrototype.setModifiedDate(ServiceTestUtil.nextDate());

		layoutSetPrototype.setName(ServiceTestUtil.randomString());

		layoutSetPrototype.setDescription(ServiceTestUtil.randomString());

		layoutSetPrototype.setSettings(ServiceTestUtil.randomString());

		layoutSetPrototype.setActive(ServiceTestUtil.randomBoolean());

		_persistence.update(layoutSetPrototype, false);

		return layoutSetPrototype;
	}

	private static Log _log = LogFactoryUtil.getLog(LayoutSetPrototypePersistenceTest.class);
	private LayoutSetPrototypePersistence _persistence = (LayoutSetPrototypePersistence)PortalBeanLocatorUtil.locate(LayoutSetPrototypePersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}