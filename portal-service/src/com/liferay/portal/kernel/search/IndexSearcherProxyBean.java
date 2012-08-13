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

import com.liferay.portal.kernel.messaging.proxy.BaseMultiDestinationProxyBean;
import com.liferay.portal.kernel.messaging.proxy.ProxyRequest;

/**
 * @author Bruno Farache
 * @author Tina Tian
 * @author Raymond Augé
 */
public class IndexSearcherProxyBean
	extends BaseMultiDestinationProxyBean implements IndexSearcher {

	@Override
	public String getDestinationName(ProxyRequest proxyRequest) {
		Object[] arguments = proxyRequest.getArguments();

		String searchEngineId = null;

		if (arguments[0] instanceof SearchContext) {
			SearchContext searchContext = (SearchContext)arguments[0];

			searchEngineId = searchContext.getSearchEngineId();
		}
		else {
			searchEngineId = (String)arguments[0];
		}

		return SearchEngineUtil.getSearchReaderDestinationName(searchEngineId);
	}

	public Hits search(SearchContext searchContext, Query query) {
		throw new UnsupportedOperationException();
	}

	public Hits search(
		String searchEngineId, long companyId, Query query, Sort[] sort,
		int start, int end) {

		throw new UnsupportedOperationException();
	}

}