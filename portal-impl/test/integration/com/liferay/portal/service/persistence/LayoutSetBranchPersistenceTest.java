/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.service.persistence;

import com.liferay.portal.NoSuchLayoutSetBranchException;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.LayoutSetBranch;
import com.liferay.portal.model.impl.LayoutSetBranchModelImpl;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.service.persistence.PersistenceExecutionTestListener;
import com.liferay.portal.test.ExecutionTestListeners;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.PropsValues;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@ExecutionTestListeners(listeners =  {
	PersistenceExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class LayoutSetBranchPersistenceTest {
	@Before
	public void setUp() throws Exception {
		_persistence = (LayoutSetBranchPersistence)PortalBeanLocatorUtil.locate(LayoutSetBranchPersistence.class.getName());
	}

	@Test
	public void testCreate() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		LayoutSetBranch layoutSetBranch = _persistence.create(pk);

		Assert.assertNotNull(layoutSetBranch);

		Assert.assertEquals(layoutSetBranch.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LayoutSetBranch newLayoutSetBranch = addLayoutSetBranch();

		_persistence.remove(newLayoutSetBranch);

		LayoutSetBranch existingLayoutSetBranch = _persistence.fetchByPrimaryKey(newLayoutSetBranch.getPrimaryKey());

		Assert.assertNull(existingLayoutSetBranch);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLayoutSetBranch();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		LayoutSetBranch newLayoutSetBranch = _persistence.create(pk);

		newLayoutSetBranch.setGroupId(ServiceTestUtil.nextLong());

		newLayoutSetBranch.setCompanyId(ServiceTestUtil.nextLong());

		newLayoutSetBranch.setUserId(ServiceTestUtil.nextLong());

		newLayoutSetBranch.setUserName(ServiceTestUtil.randomString());

		newLayoutSetBranch.setCreateDate(ServiceTestUtil.nextDate());

		newLayoutSetBranch.setModifiedDate(ServiceTestUtil.nextDate());

		newLayoutSetBranch.setPrivateLayout(ServiceTestUtil.randomBoolean());

		newLayoutSetBranch.setName(ServiceTestUtil.randomString());

		newLayoutSetBranch.setDescription(ServiceTestUtil.randomString());

		newLayoutSetBranch.setMaster(ServiceTestUtil.randomBoolean());

		newLayoutSetBranch.setLogo(ServiceTestUtil.randomBoolean());

		newLayoutSetBranch.setLogoId(ServiceTestUtil.nextLong());

		newLayoutSetBranch.setThemeId(ServiceTestUtil.randomString());

		newLayoutSetBranch.setColorSchemeId(ServiceTestUtil.randomString());

		newLayoutSetBranch.setWapThemeId(ServiceTestUtil.randomString());

		newLayoutSetBranch.setWapColorSchemeId(ServiceTestUtil.randomString());

		newLayoutSetBranch.setCss(ServiceTestUtil.randomString());

		newLayoutSetBranch.setSettings(ServiceTestUtil.randomString());

		newLayoutSetBranch.setLayoutSetPrototypeUuid(ServiceTestUtil.randomString());

		newLayoutSetBranch.setLayoutSetPrototypeLinkEnabled(ServiceTestUtil.randomBoolean());

		_persistence.update(newLayoutSetBranch, false);

		LayoutSetBranch existingLayoutSetBranch = _persistence.findByPrimaryKey(newLayoutSetBranch.getPrimaryKey());

		Assert.assertEquals(existingLayoutSetBranch.getLayoutSetBranchId(),
			newLayoutSetBranch.getLayoutSetBranchId());
		Assert.assertEquals(existingLayoutSetBranch.getGroupId(),
			newLayoutSetBranch.getGroupId());
		Assert.assertEquals(existingLayoutSetBranch.getCompanyId(),
			newLayoutSetBranch.getCompanyId());
		Assert.assertEquals(existingLayoutSetBranch.getUserId(),
			newLayoutSetBranch.getUserId());
		Assert.assertEquals(existingLayoutSetBranch.getUserName(),
			newLayoutSetBranch.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLayoutSetBranch.getCreateDate()),
			Time.getShortTimestamp(newLayoutSetBranch.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingLayoutSetBranch.getModifiedDate()),
			Time.getShortTimestamp(newLayoutSetBranch.getModifiedDate()));
		Assert.assertEquals(existingLayoutSetBranch.getPrivateLayout(),
			newLayoutSetBranch.getPrivateLayout());
		Assert.assertEquals(existingLayoutSetBranch.getName(),
			newLayoutSetBranch.getName());
		Assert.assertEquals(existingLayoutSetBranch.getDescription(),
			newLayoutSetBranch.getDescription());
		Assert.assertEquals(existingLayoutSetBranch.getMaster(),
			newLayoutSetBranch.getMaster());
		Assert.assertEquals(existingLayoutSetBranch.getLogo(),
			newLayoutSetBranch.getLogo());
		Assert.assertEquals(existingLayoutSetBranch.getLogoId(),
			newLayoutSetBranch.getLogoId());
		Assert.assertEquals(existingLayoutSetBranch.getThemeId(),
			newLayoutSetBranch.getThemeId());
		Assert.assertEquals(existingLayoutSetBranch.getColorSchemeId(),
			newLayoutSetBranch.getColorSchemeId());
		Assert.assertEquals(existingLayoutSetBranch.getWapThemeId(),
			newLayoutSetBranch.getWapThemeId());
		Assert.assertEquals(existingLayoutSetBranch.getWapColorSchemeId(),
			newLayoutSetBranch.getWapColorSchemeId());
		Assert.assertEquals(existingLayoutSetBranch.getCss(),
			newLayoutSetBranch.getCss());
		Assert.assertEquals(existingLayoutSetBranch.getSettings(),
			newLayoutSetBranch.getSettings());
		Assert.assertEquals(existingLayoutSetBranch.getLayoutSetPrototypeUuid(),
			newLayoutSetBranch.getLayoutSetPrototypeUuid());
		Assert.assertEquals(existingLayoutSetBranch.getLayoutSetPrototypeLinkEnabled(),
			newLayoutSetBranch.getLayoutSetPrototypeLinkEnabled());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LayoutSetBranch newLayoutSetBranch = addLayoutSetBranch();

		LayoutSetBranch existingLayoutSetBranch = _persistence.findByPrimaryKey(newLayoutSetBranch.getPrimaryKey());

		Assert.assertEquals(existingLayoutSetBranch, newLayoutSetBranch);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail(
				"Missing entity did not throw NoSuchLayoutSetBranchException");
		}
		catch (NoSuchLayoutSetBranchException nsee) {
		}
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LayoutSetBranch newLayoutSetBranch = addLayoutSetBranch();

		LayoutSetBranch existingLayoutSetBranch = _persistence.fetchByPrimaryKey(newLayoutSetBranch.getPrimaryKey());

		Assert.assertEquals(existingLayoutSetBranch, newLayoutSetBranch);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		LayoutSetBranch missingLayoutSetBranch = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLayoutSetBranch);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LayoutSetBranch newLayoutSetBranch = addLayoutSetBranch();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutSetBranch.class,
				LayoutSetBranch.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("layoutSetBranchId",
				newLayoutSetBranch.getLayoutSetBranchId()));

		List<LayoutSetBranch> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LayoutSetBranch existingLayoutSetBranch = result.get(0);

		Assert.assertEquals(existingLayoutSetBranch, newLayoutSetBranch);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutSetBranch.class,
				LayoutSetBranch.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("layoutSetBranchId",
				ServiceTestUtil.nextLong()));

		List<LayoutSetBranch> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LayoutSetBranch newLayoutSetBranch = addLayoutSetBranch();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutSetBranch.class,
				LayoutSetBranch.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"layoutSetBranchId"));

		Object newLayoutSetBranchId = newLayoutSetBranch.getLayoutSetBranchId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("layoutSetBranchId",
				new Object[] { newLayoutSetBranchId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLayoutSetBranchId = result.get(0);

		Assert.assertEquals(existingLayoutSetBranchId, newLayoutSetBranchId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutSetBranch.class,
				LayoutSetBranch.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"layoutSetBranchId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("layoutSetBranchId",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		LayoutSetBranch newLayoutSetBranch = addLayoutSetBranch();

		_persistence.clearCache();

		LayoutSetBranchModelImpl existingLayoutSetBranchModelImpl = (LayoutSetBranchModelImpl)_persistence.findByPrimaryKey(newLayoutSetBranch.getPrimaryKey());

		Assert.assertEquals(existingLayoutSetBranchModelImpl.getGroupId(),
			existingLayoutSetBranchModelImpl.getOriginalGroupId());
		Assert.assertEquals(existingLayoutSetBranchModelImpl.getPrivateLayout(),
			existingLayoutSetBranchModelImpl.getOriginalPrivateLayout());
		Assert.assertTrue(Validator.equals(
				existingLayoutSetBranchModelImpl.getName(),
				existingLayoutSetBranchModelImpl.getOriginalName()));
	}

	protected LayoutSetBranch addLayoutSetBranch() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		LayoutSetBranch layoutSetBranch = _persistence.create(pk);

		layoutSetBranch.setGroupId(ServiceTestUtil.nextLong());

		layoutSetBranch.setCompanyId(ServiceTestUtil.nextLong());

		layoutSetBranch.setUserId(ServiceTestUtil.nextLong());

		layoutSetBranch.setUserName(ServiceTestUtil.randomString());

		layoutSetBranch.setCreateDate(ServiceTestUtil.nextDate());

		layoutSetBranch.setModifiedDate(ServiceTestUtil.nextDate());

		layoutSetBranch.setPrivateLayout(ServiceTestUtil.randomBoolean());

		layoutSetBranch.setName(ServiceTestUtil.randomString());

		layoutSetBranch.setDescription(ServiceTestUtil.randomString());

		layoutSetBranch.setMaster(ServiceTestUtil.randomBoolean());

		layoutSetBranch.setLogo(ServiceTestUtil.randomBoolean());

		layoutSetBranch.setLogoId(ServiceTestUtil.nextLong());

		layoutSetBranch.setThemeId(ServiceTestUtil.randomString());

		layoutSetBranch.setColorSchemeId(ServiceTestUtil.randomString());

		layoutSetBranch.setWapThemeId(ServiceTestUtil.randomString());

		layoutSetBranch.setWapColorSchemeId(ServiceTestUtil.randomString());

		layoutSetBranch.setCss(ServiceTestUtil.randomString());

		layoutSetBranch.setSettings(ServiceTestUtil.randomString());

		layoutSetBranch.setLayoutSetPrototypeUuid(ServiceTestUtil.randomString());

		layoutSetBranch.setLayoutSetPrototypeLinkEnabled(ServiceTestUtil.randomBoolean());

		_persistence.update(layoutSetBranch, false);

		return layoutSetBranch;
	}

	private LayoutSetBranchPersistence _persistence;
}