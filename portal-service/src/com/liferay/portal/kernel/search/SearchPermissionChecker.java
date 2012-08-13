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

package com.liferay.portal.kernel.search;

/**
 * @author Allen Chiang
 * @author Bruno Farache
 * @author Raymond Augé
 */
public interface SearchPermissionChecker {

	public void addPermissionFields(long companyId, Document doc);

	public Query getPermissionQuery(
		long companyId, long[] groupIds, long userId, String className,
		Query query, SearchContext searchContext);

	public void updatePermissionFields(long resourceId);

	public void updatePermissionFields(String name, String primKey);

}