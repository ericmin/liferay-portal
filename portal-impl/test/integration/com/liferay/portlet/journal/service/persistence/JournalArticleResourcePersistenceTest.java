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

package com.liferay.portlet.journal.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.service.persistence.PersistenceExecutionTestListener;
import com.liferay.portal.test.ExecutionTestListeners;
import com.liferay.portal.test.LiferayPersistenceIntegrationJUnitTestRunner;
import com.liferay.portal.test.persistence.TransactionalPersistenceAdvice;
import com.liferay.portal.util.PropsValues;

import com.liferay.portlet.journal.NoSuchArticleResourceException;
import com.liferay.portlet.journal.model.JournalArticleResource;
import com.liferay.portlet.journal.model.impl.JournalArticleResourceModelImpl;

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
public class JournalArticleResourcePersistenceTest {
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

		JournalArticleResource journalArticleResource = _persistence.create(pk);

		Assert.assertNotNull(journalArticleResource);

		Assert.assertEquals(journalArticleResource.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		JournalArticleResource newJournalArticleResource = addJournalArticleResource();

		_persistence.remove(newJournalArticleResource);

		JournalArticleResource existingJournalArticleResource = _persistence.fetchByPrimaryKey(newJournalArticleResource.getPrimaryKey());

		Assert.assertNull(existingJournalArticleResource);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addJournalArticleResource();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		JournalArticleResource newJournalArticleResource = _persistence.create(pk);

		newJournalArticleResource.setUuid(ServiceTestUtil.randomString());

		newJournalArticleResource.setGroupId(ServiceTestUtil.nextLong());

		newJournalArticleResource.setArticleId(ServiceTestUtil.randomString());

		_persistence.update(newJournalArticleResource, false);

		JournalArticleResource existingJournalArticleResource = _persistence.findByPrimaryKey(newJournalArticleResource.getPrimaryKey());

		Assert.assertEquals(existingJournalArticleResource.getUuid(),
			newJournalArticleResource.getUuid());
		Assert.assertEquals(existingJournalArticleResource.getResourcePrimKey(),
			newJournalArticleResource.getResourcePrimKey());
		Assert.assertEquals(existingJournalArticleResource.getGroupId(),
			newJournalArticleResource.getGroupId());
		Assert.assertEquals(existingJournalArticleResource.getArticleId(),
			newJournalArticleResource.getArticleId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		JournalArticleResource newJournalArticleResource = addJournalArticleResource();

		JournalArticleResource existingJournalArticleResource = _persistence.findByPrimaryKey(newJournalArticleResource.getPrimaryKey());

		Assert.assertEquals(existingJournalArticleResource,
			newJournalArticleResource);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail(
				"Missing entity did not throw NoSuchArticleResourceException");
		}
		catch (NoSuchArticleResourceException nsee) {
		}
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		JournalArticleResource newJournalArticleResource = addJournalArticleResource();

		JournalArticleResource existingJournalArticleResource = _persistence.fetchByPrimaryKey(newJournalArticleResource.getPrimaryKey());

		Assert.assertEquals(existingJournalArticleResource,
			newJournalArticleResource);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		JournalArticleResource missingJournalArticleResource = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingJournalArticleResource);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		JournalArticleResource newJournalArticleResource = addJournalArticleResource();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(JournalArticleResource.class,
				JournalArticleResource.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("resourcePrimKey",
				newJournalArticleResource.getResourcePrimKey()));

		List<JournalArticleResource> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		JournalArticleResource existingJournalArticleResource = result.get(0);

		Assert.assertEquals(existingJournalArticleResource,
			newJournalArticleResource);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(JournalArticleResource.class,
				JournalArticleResource.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("resourcePrimKey",
				ServiceTestUtil.nextLong()));

		List<JournalArticleResource> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		JournalArticleResource newJournalArticleResource = addJournalArticleResource();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(JournalArticleResource.class,
				JournalArticleResource.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"resourcePrimKey"));

		Object newResourcePrimKey = newJournalArticleResource.getResourcePrimKey();

		dynamicQuery.add(RestrictionsFactoryUtil.in("resourcePrimKey",
				new Object[] { newResourcePrimKey }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingResourcePrimKey = result.get(0);

		Assert.assertEquals(existingResourcePrimKey, newResourcePrimKey);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(JournalArticleResource.class,
				JournalArticleResource.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"resourcePrimKey"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("resourcePrimKey",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		JournalArticleResource newJournalArticleResource = addJournalArticleResource();

		_persistence.clearCache();

		JournalArticleResourceModelImpl existingJournalArticleResourceModelImpl = (JournalArticleResourceModelImpl)_persistence.findByPrimaryKey(newJournalArticleResource.getPrimaryKey());

		Assert.assertTrue(Validator.equals(
				existingJournalArticleResourceModelImpl.getUuid(),
				existingJournalArticleResourceModelImpl.getOriginalUuid()));
		Assert.assertEquals(existingJournalArticleResourceModelImpl.getGroupId(),
			existingJournalArticleResourceModelImpl.getOriginalGroupId());

		Assert.assertEquals(existingJournalArticleResourceModelImpl.getGroupId(),
			existingJournalArticleResourceModelImpl.getOriginalGroupId());
		Assert.assertTrue(Validator.equals(
				existingJournalArticleResourceModelImpl.getArticleId(),
				existingJournalArticleResourceModelImpl.getOriginalArticleId()));
	}

	protected JournalArticleResource addJournalArticleResource()
		throws Exception {
		long pk = ServiceTestUtil.nextLong();

		JournalArticleResource journalArticleResource = _persistence.create(pk);

		journalArticleResource.setUuid(ServiceTestUtil.randomString());

		journalArticleResource.setGroupId(ServiceTestUtil.nextLong());

		journalArticleResource.setArticleId(ServiceTestUtil.randomString());

		_persistence.update(journalArticleResource, false);

		return journalArticleResource;
	}

	private static Log _log = LogFactoryUtil.getLog(JournalArticleResourcePersistenceTest.class);
	private JournalArticleResourcePersistence _persistence = (JournalArticleResourcePersistence)PortalBeanLocatorUtil.locate(JournalArticleResourcePersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}