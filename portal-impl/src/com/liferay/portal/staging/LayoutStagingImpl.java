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

package com.liferay.portal.staging;

import com.liferay.portal.kernel.staging.LayoutStaging;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutRevision;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetBranch;
import com.liferay.portal.model.LayoutSetStagingHandler;
import com.liferay.portal.model.LayoutStagingHandler;

import java.lang.reflect.InvocationHandler;

/**
 * @author Raymond Augé
 */
public class LayoutStagingImpl implements LayoutStaging {

	public LayoutRevision getLayoutRevision(Layout layout) {
		LayoutStagingHandler layoutStagingHandler = getLayoutStagingHandler(
			layout);

		if (layoutStagingHandler == null) {
			return null;
		}

		return layoutStagingHandler.getLayoutRevision();
	}

	public LayoutSetBranch getLayoutSetBranch(LayoutSet layoutSet) {
		LayoutSetStagingHandler layoutSetStagingHandler =
			getLayoutSetStagingHandler(layoutSet);

		if (layoutSetStagingHandler == null) {
			return null;
		}

		return layoutSetStagingHandler.getLayoutSetBranch();
	}

	public LayoutSetStagingHandler getLayoutSetStagingHandler(
		LayoutSet layoutSet) {

		if (!ProxyUtil.isProxyClass(layoutSet.getClass())) {
			return null;
		}

		InvocationHandler invocationHandler = ProxyUtil.getInvocationHandler(
			layoutSet);

		if (!(invocationHandler instanceof LayoutSetStagingHandler)) {
			return null;
		}

		return (LayoutSetStagingHandler)invocationHandler;
	}

	public LayoutStagingHandler getLayoutStagingHandler(Layout layout) {
		if (!ProxyUtil.isProxyClass(layout.getClass())) {
			return null;
		}

		InvocationHandler invocationHandler = ProxyUtil.getInvocationHandler(
			layout);

		if (!(invocationHandler instanceof LayoutStagingHandler)) {
			return null;
		}

		return (LayoutStagingHandler)invocationHandler;
	}

	public boolean isBranchingLayout(Layout layout) {
		try {
			return isBranchingLayoutSet(
				layout.getGroup(), layout.isPrivateLayout());
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public boolean isBranchingLayoutSet(Group group, boolean privateLayout) {
		boolean isStagingGroup = false;

		if (group.isStagingGroup()) {
			isStagingGroup = true;

			group = group.getLiveGroup();
		}

		UnicodeProperties typeSettingsProperties =
			group.getTypeSettingsProperties();

		boolean branchingEnabled = false;

		if (privateLayout) {
			branchingEnabled = GetterUtil.getBoolean(
				typeSettingsProperties.getProperty("branchingPrivate"));
		}
		else {
			branchingEnabled = GetterUtil.getBoolean(
				typeSettingsProperties.getProperty("branchingPublic"));
		}

		if (group.isStaged() && branchingEnabled) {
			if (!group.isStagedRemotely() && !isStagingGroup) {
				return false;
			}

			return true;
		}

		return false;
	}

}