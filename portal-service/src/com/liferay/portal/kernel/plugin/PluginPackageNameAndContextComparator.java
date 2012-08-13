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

package com.liferay.portal.kernel.plugin;

import java.util.Comparator;

/**
 * @author Jorge Ferrer
 */
public class PluginPackageNameAndContextComparator
	implements Comparator<PluginPackage> {

	public int compare(PluginPackage package1, PluginPackage package2) {
		int result = package1.getName().compareTo(package2.getName());

		if (result == 0) {
			result = package1.getContext().compareTo(package2.getContext());
		}

		return result;
	}

}