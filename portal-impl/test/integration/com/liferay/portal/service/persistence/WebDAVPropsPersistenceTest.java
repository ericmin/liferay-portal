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

import com.liferay.portal.NoSuchWebDAVPropsException;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.model.WebDAVProps;
import com.liferay.portal.model.impl.WebDAVPropsModelImpl;
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
public class WebDAVPropsPersistenceTest {
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

		WebDAVProps webDAVProps = _persistence.create(pk);

		Assert.assertNotNull(webDAVProps);

		Assert.assertEquals(webDAVProps.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		WebDAVProps newWebDAVProps = addWebDAVProps();

		_persistence.remove(newWebDAVProps);

		WebDAVProps existingWebDAVProps = _persistence.fetchByPrimaryKey(newWebDAVProps.getPrimaryKey());

		Assert.assertNull(existingWebDAVProps);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addWebDAVProps();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		WebDAVProps newWebDAVProps = _persistence.create(pk);

		newWebDAVProps.setCompanyId(ServiceTestUtil.nextLong());

		newWebDAVProps.setCreateDate(ServiceTestUtil.nextDate());

		newWebDAVProps.setModifiedDate(ServiceTestUtil.nextDate());

		newWebDAVProps.setClassNameId(ServiceTestUtil.nextLong());

		newWebDAVProps.setClassPK(ServiceTestUtil.nextLong());

		newWebDAVProps.setProps(ServiceTestUtil.randomString());

		_persistence.update(newWebDAVProps, false);

		WebDAVProps existingWebDAVProps = _persistence.findByPrimaryKey(newWebDAVProps.getPrimaryKey());

		Assert.assertEquals(existingWebDAVProps.getWebDavPropsId(),
			newWebDAVProps.getWebDavPropsId());
		Assert.assertEquals(existingWebDAVProps.getCompanyId(),
			newWebDAVProps.getCompanyId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingWebDAVProps.getCreateDate()),
			Time.getShortTimestamp(newWebDAVProps.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingWebDAVProps.getModifiedDate()),
			Time.getShortTimestamp(newWebDAVProps.getModifiedDate()));
		Assert.assertEquals(existingWebDAVProps.getClassNameId(),
			newWebDAVProps.getClassNameId());
		Assert.assertEquals(existingWebDAVProps.getClassPK(),
			newWebDAVProps.getClassPK());
		Assert.assertEquals(existingWebDAVProps.getProps(),
			newWebDAVProps.getProps());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		WebDAVProps newWebDAVProps = addWebDAVProps();

		WebDAVProps existingWebDAVProps = _persistence.findByPrimaryKey(newWebDAVProps.getPrimaryKey());

		Assert.assertEquals(existingWebDAVProps, newWebDAVProps);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail(
				"Missing entity did not throw NoSuchWebDAVPropsException");
		}
		catch (NoSuchWebDAVPropsException nsee) {
		}
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		WebDAVProps newWebDAVProps = addWebDAVProps();

		WebDAVProps existingWebDAVProps = _persistence.fetchByPrimaryKey(newWebDAVProps.getPrimaryKey());

		Assert.assertEquals(existingWebDAVProps, newWebDAVProps);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		WebDAVProps missingWebDAVProps = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingWebDAVProps);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		WebDAVProps newWebDAVProps = addWebDAVProps();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WebDAVProps.class,
				WebDAVProps.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("webDavPropsId",
				newWebDAVProps.getWebDavPropsId()));

		List<WebDAVProps> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		WebDAVProps existingWebDAVProps = result.get(0);

		Assert.assertEquals(existingWebDAVProps, newWebDAVProps);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WebDAVProps.class,
				WebDAVProps.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("webDavPropsId",
				ServiceTestUtil.nextLong()));

		List<WebDAVProps> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		WebDAVProps newWebDAVProps = addWebDAVProps();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WebDAVProps.class,
				WebDAVProps.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"webDavPropsId"));

		Object newWebDavPropsId = newWebDAVProps.getWebDavPropsId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("webDavPropsId",
				new Object[] { newWebDavPropsId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingWebDavPropsId = result.get(0);

		Assert.assertEquals(existingWebDavPropsId, newWebDavPropsId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WebDAVProps.class,
				WebDAVProps.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"webDavPropsId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("webDavPropsId",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		WebDAVProps newWebDAVProps = addWebDAVProps();

		_persistence.clearCache();

		WebDAVPropsModelImpl existingWebDAVPropsModelImpl = (WebDAVPropsModelImpl)_persistence.findByPrimaryKey(newWebDAVProps.getPrimaryKey());

		Assert.assertEquals(existingWebDAVPropsModelImpl.getClassNameId(),
			existingWebDAVPropsModelImpl.getOriginalClassNameId());
		Assert.assertEquals(existingWebDAVPropsModelImpl.getClassPK(),
			existingWebDAVPropsModelImpl.getOriginalClassPK());
	}

	protected WebDAVProps addWebDAVProps() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		WebDAVProps webDAVProps = _persistence.create(pk);

		webDAVProps.setCompanyId(ServiceTestUtil.nextLong());

		webDAVProps.setCreateDate(ServiceTestUtil.nextDate());

		webDAVProps.setModifiedDate(ServiceTestUtil.nextDate());

		webDAVProps.setClassNameId(ServiceTestUtil.nextLong());

		webDAVProps.setClassPK(ServiceTestUtil.nextLong());

		webDAVProps.setProps(ServiceTestUtil.randomString());

		_persistence.update(webDAVProps, false);

		return webDAVProps;
	}

	private static Log _log = LogFactoryUtil.getLog(WebDAVPropsPersistenceTest.class);
	private WebDAVPropsPersistence _persistence = (WebDAVPropsPersistence)PortalBeanLocatorUtil.locate(WebDAVPropsPersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}