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
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class BooleanQueryFactoryUtil {

	public static BooleanQuery create(SearchContext searchContext) {
		return getBooleanQueryFactory(searchContext).create();
	}

	public static BooleanQueryFactory getBooleanQueryFactory(
		SearchContext searchContext) {

		String searchEngineId = searchContext.getSearchEngineId();

		SearchEngine searchEngine = SearchEngineUtil.getSearchEngine(
			searchEngineId);

		return searchEngine.getBooleanQueryFactory();
	}

}