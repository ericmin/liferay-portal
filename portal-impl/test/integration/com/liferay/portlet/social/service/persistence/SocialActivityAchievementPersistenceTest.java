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

package com.liferay.portlet.social.service.persistence;

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

import com.liferay.portlet.social.NoSuchActivityAchievementException;
import com.liferay.portlet.social.model.SocialActivityAchievement;
import com.liferay.portlet.social.model.impl.SocialActivityAchievementModelImpl;

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
public class SocialActivityAchievementPersistenceTest {
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

		SocialActivityAchievement socialActivityAchievement = _persistence.create(pk);

		Assert.assertNotNull(socialActivityAchievement);

		Assert.assertEquals(socialActivityAchievement.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		SocialActivityAchievement newSocialActivityAchievement = addSocialActivityAchievement();

		_persistence.remove(newSocialActivityAchievement);

		SocialActivityAchievement existingSocialActivityAchievement = _persistence.fetchByPrimaryKey(newSocialActivityAchievement.getPrimaryKey());

		Assert.assertNull(existingSocialActivityAchievement);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addSocialActivityAchievement();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		SocialActivityAchievement newSocialActivityAchievement = _persistence.create(pk);

		newSocialActivityAchievement.setGroupId(ServiceTestUtil.nextLong());

		newSocialActivityAchievement.setCompanyId(ServiceTestUtil.nextLong());

		newSocialActivityAchievement.setUserId(ServiceTestUtil.nextLong());

		newSocialActivityAchievement.setCreateDate(ServiceTestUtil.nextLong());

		newSocialActivityAchievement.setName(ServiceTestUtil.randomString());

		newSocialActivityAchievement.setFirstInGroup(ServiceTestUtil.randomBoolean());

		_persistence.update(newSocialActivityAchievement, false);

		SocialActivityAchievement existingSocialActivityAchievement = _persistence.findByPrimaryKey(newSocialActivityAchievement.getPrimaryKey());

		Assert.assertEquals(existingSocialActivityAchievement.getActivityAchievementId(),
			newSocialActivityAchievement.getActivityAchievementId());
		Assert.assertEquals(existingSocialActivityAchievement.getGroupId(),
			newSocialActivityAchievement.getGroupId());
		Assert.assertEquals(existingSocialActivityAchievement.getCompanyId(),
			newSocialActivityAchievement.getCompanyId());
		Assert.assertEquals(existingSocialActivityAchievement.getUserId(),
			newSocialActivityAchievement.getUserId());
		Assert.assertEquals(existingSocialActivityAchievement.getCreateDate(),
			newSocialActivityAchievement.getCreateDate());
		Assert.assertEquals(existingSocialActivityAchievement.getName(),
			newSocialActivityAchievement.getName());
		Assert.assertEquals(existingSocialActivityAchievement.getFirstInGroup(),
			newSocialActivityAchievement.getFirstInGroup());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		SocialActivityAchievement newSocialActivityAchievement = addSocialActivityAchievement();

		SocialActivityAchievement existingSocialActivityAchievement = _persistence.findByPrimaryKey(newSocialActivityAchievement.getPrimaryKey());

		Assert.assertEquals(existingSocialActivityAchievement,
			newSocialActivityAchievement);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail(
				"Missing entity did not throw NoSuchActivityAchievementException");
		}
		catch (NoSuchActivityAchievementException nsee) {
		}
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		SocialActivityAchievement newSocialActivityAchievement = addSocialActivityAchievement();

		SocialActivityAchievement existingSocialActivityAchievement = _persistence.fetchByPrimaryKey(newSocialActivityAchievement.getPrimaryKey());

		Assert.assertEquals(existingSocialActivityAchievement,
			newSocialActivityAchievement);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		SocialActivityAchievement missingSocialActivityAchievement = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingSocialActivityAchievement);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		SocialActivityAchievement newSocialActivityAchievement = addSocialActivityAchievement();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SocialActivityAchievement.class,
				SocialActivityAchievement.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("activityAchievementId",
				newSocialActivityAchievement.getActivityAchievementId()));

		List<SocialActivityAchievement> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		SocialActivityAchievement existingSocialActivityAchievement = result.get(0);

		Assert.assertEquals(existingSocialActivityAchievement,
			newSocialActivityAchievement);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SocialActivityAchievement.class,
				SocialActivityAchievement.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("activityAchievementId",
				ServiceTestUtil.nextLong()));

		List<SocialActivityAchievement> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		SocialActivityAchievement newSocialActivityAchievement = addSocialActivityAchievement();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SocialActivityAchievement.class,
				SocialActivityAchievement.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"activityAchievementId"));

		Object newActivityAchievementId = newSocialActivityAchievement.getActivityAchievementId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("activityAchievementId",
				new Object[] { newActivityAchievementId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingActivityAchievementId = result.get(0);

		Assert.assertEquals(existingActivityAchievementId,
			newActivityAchievementId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SocialActivityAchievement.class,
				SocialActivityAchievement.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"activityAchievementId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("activityAchievementId",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		SocialActivityAchievement newSocialActivityAchievement = addSocialActivityAchievement();

		_persistence.clearCache();

		SocialActivityAchievementModelImpl existingSocialActivityAchievementModelImpl =
			(SocialActivityAchievementModelImpl)_persistence.findByPrimaryKey(newSocialActivityAchievement.getPrimaryKey());

		Assert.assertEquals(existingSocialActivityAchievementModelImpl.getGroupId(),
			existingSocialActivityAchievementModelImpl.getOriginalGroupId());
		Assert.assertEquals(existingSocialActivityAchievementModelImpl.getUserId(),
			existingSocialActivityAchievementModelImpl.getOriginalUserId());
		Assert.assertTrue(Validator.equals(
				existingSocialActivityAchievementModelImpl.getName(),
				existingSocialActivityAchievementModelImpl.getOriginalName()));
	}

	protected SocialActivityAchievement addSocialActivityAchievement()
		throws Exception {
		long pk = ServiceTestUtil.nextLong();

		SocialActivityAchievement socialActivityAchievement = _persistence.create(pk);

		socialActivityAchievement.setGroupId(ServiceTestUtil.nextLong());

		socialActivityAchievement.setCompanyId(ServiceTestUtil.nextLong());

		socialActivityAchievement.setUserId(ServiceTestUtil.nextLong());

		socialActivityAchievement.setCreateDate(ServiceTestUtil.nextLong());

		socialActivityAchievement.setName(ServiceTestUtil.randomString());

		socialActivityAchievement.setFirstInGroup(ServiceTestUtil.randomBoolean());

		_persistence.update(socialActivityAchievement, false);

		return socialActivityAchievement;
	}

	private static Log _log = LogFactoryUtil.getLog(SocialActivityAchievementPersistenceTest.class);
	private SocialActivityAchievementPersistence _persistence = (SocialActivityAchievementPersistence)PortalBeanLocatorUtil.locate(SocialActivityAchievementPersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}