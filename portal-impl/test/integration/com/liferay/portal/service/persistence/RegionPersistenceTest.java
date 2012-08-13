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

import com.liferay.portal.NoSuchRegionException;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Region;
import com.liferay.portal.model.impl.RegionModelImpl;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.service.persistence.PersistenceExecutionTestListener;
import com.liferay.portal.test.ExecutionTestListeners;
import com.liferay.portal.test.LiferayPersistenceIntegrationJUnitTestRunner;
import com.liferay.portal.test.persistence.TransactionalPersistenceAdvice;
import com.liferay.portal.util.PropsValues;

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
public class RegionPersistenceTest {
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

		Region region = _persistence.create(pk);

		Assert.assertNotNull(region);

		Assert.assertEquals(region.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Region newRegion = addRegion();

		_persistence.remove(newRegion);

		Region existingRegion = _persistence.fetchByPrimaryKey(newRegion.getPrimaryKey());

		Assert.assertNull(existingRegion);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRegion();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		Region newRegion = _persistence.create(pk);

		newRegion.setCountryId(ServiceTestUtil.nextLong());

		newRegion.setRegionCode(ServiceTestUtil.randomString());

		newRegion.setName(ServiceTestUtil.randomString());

		newRegion.setActive(ServiceTestUtil.randomBoolean());

		_persistence.update(newRegion, false);

		Region existingRegion = _persistence.findByPrimaryKey(newRegion.getPrimaryKey());

		Assert.assertEquals(existingRegion.getRegionId(),
			newRegion.getRegionId());
		Assert.assertEquals(existingRegion.getCountryId(),
			newRegion.getCountryId());
		Assert.assertEquals(existingRegion.getRegionCode(),
			newRegion.getRegionCode());
		Assert.assertEquals(existingRegion.getName(), newRegion.getName());
		Assert.assertEquals(existingRegion.getActive(), newRegion.getActive());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Region newRegion = addRegion();

		Region existingRegion = _persistence.findByPrimaryKey(newRegion.getPrimaryKey());

		Assert.assertEquals(existingRegion, newRegion);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchRegionException");
		}
		catch (NoSuchRegionException nsee) {
		}
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Region newRegion = addRegion();

		Region existingRegion = _persistence.fetchByPrimaryKey(newRegion.getPrimaryKey());

		Assert.assertEquals(existingRegion, newRegion);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		Region missingRegion = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRegion);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Region newRegion = addRegion();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Region.class,
				Region.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("regionId",
				newRegion.getRegionId()));

		List<Region> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Region existingRegion = result.get(0);

		Assert.assertEquals(existingRegion, newRegion);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Region.class,
				Region.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("regionId",
				ServiceTestUtil.nextLong()));

		List<Region> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Region newRegion = addRegion();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Region.class,
				Region.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("regionId"));

		Object newRegionId = newRegion.getRegionId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("regionId",
				new Object[] { newRegionId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRegionId = result.get(0);

		Assert.assertEquals(existingRegionId, newRegionId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Region.class,
				Region.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("regionId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("regionId",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		Region newRegion = addRegion();

		_persistence.clearCache();

		RegionModelImpl existingRegionModelImpl = (RegionModelImpl)_persistence.findByPrimaryKey(newRegion.getPrimaryKey());

		Assert.assertEquals(existingRegionModelImpl.getCountryId(),
			existingRegionModelImpl.getOriginalCountryId());
		Assert.assertTrue(Validator.equals(
				existingRegionModelImpl.getRegionCode(),
				existingRegionModelImpl.getOriginalRegionCode()));
	}

	protected Region addRegion() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		Region region = _persistence.create(pk);

		region.setCountryId(ServiceTestUtil.nextLong());

		region.setRegionCode(ServiceTestUtil.randomString());

		region.setName(ServiceTestUtil.randomString());

		region.setActive(ServiceTestUtil.randomBoolean());

		_persistence.update(region, false);

		return region;
	}

	private static Log _log = LogFactoryUtil.getLog(RegionPersistenceTest.class);
	private RegionPersistence _persistence = (RegionPersistence)PortalBeanLocatorUtil.locate(RegionPersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}