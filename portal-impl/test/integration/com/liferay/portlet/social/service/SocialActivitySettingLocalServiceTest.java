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

package com.liferay.portlet.social.service;

import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.ExecutionTestListeners;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.model.SocialActivityDefinition;
import com.liferay.portlet.social.util.SocialConfigurationUtil;

import java.io.InputStream;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Zsolt Berentey
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class SocialActivitySettingLocalServiceTest {

	@BeforeClass
	public static void setUp() throws Exception {
		Class<?> clazz = SocialActivitySettingLocalServiceTest.class;

		InputStream inputStream = clazz.getResourceAsStream(
			"dependencies/liferay-social.xml");

		String xml = new String(FileUtil.getBytes(inputStream));

		SocialConfigurationUtil.read(
			clazz.getClassLoader(), new String[] {xml});

		_activityDefinition = SocialConfigurationUtil.getActivityDefinition(
			TEST_MODEL, 1);

		_group = GroupLocalServiceUtil.getGroup(
			PortalUtil.getDefaultCompanyId(), GroupConstants.GUEST);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		long classNameId = PortalUtil.getClassNameId(TEST_MODEL);

		ClassNameLocalServiceUtil.deleteClassName(classNameId);
	}

	@Test
	public void testGetActivityDefinition() throws Exception {
		SocialActivityDefinition activityDefinition =
			SocialActivitySettingLocalServiceUtil.getActivityDefinition(
				_group.getGroupId(), TEST_MODEL, 1);

		Assert.assertEquals(_activityDefinition, activityDefinition);

		List<SocialActivityDefinition> activityDefinitions =
			SocialActivitySettingLocalServiceUtil.getActivityDefinitions(
				_group.getGroupId(), TEST_MODEL);

		Assert.assertNotNull(activityDefinitions);
		Assert.assertEquals(1, activityDefinitions.size());
		Assert.assertEquals(_activityDefinition, activityDefinitions.get(0));
	}

	@Test
	@Transactional
	public void testUpdateActivitySettings() throws Exception {
		long classNameId = PortalUtil.getClassNameId(TEST_MODEL);

		Assert.assertFalse(
			SocialActivitySettingLocalServiceUtil.isEnabled(
				_group.getGroupId(), classNameId));

		SocialActivitySettingLocalServiceUtil.updateActivitySetting(
			_group.getGroupId(), TEST_MODEL, true);

		Assert.assertTrue(
			SocialActivitySettingLocalServiceUtil.isEnabled(
			_group.getGroupId(), classNameId));
	}

	private static final String TEST_MODEL = "TEST_MODEL";

	private static SocialActivityDefinition _activityDefinition;
	private static Group _group;

}