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

import java.util.Collection;

/**
 * @author Bruno Farache
 * @author Tina Tian
 */
public class IndexWriterProxyBean extends BaseMultiDestinationProxyBean
	implements IndexWriter {

	public void addDocument(SearchContext searchContext, Document document) {
		throw new UnsupportedOperationException();
	}

	public void addDocuments(
		SearchContext searchContext, Collection<Document> documents) {

		throw new UnsupportedOperationException();
	}

	public void deleteDocument(SearchContext searchContext, String uid) {
		throw new UnsupportedOperationException();
	}

	public void deleteDocuments(
		SearchContext searchContext, Collection<String> uids) {

		throw new UnsupportedOperationException();
	}

	public void deletePortletDocuments(
		SearchContext searchContext, String portletId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public String getDestinationName(ProxyRequest proxyRequest) {
		Object[] arguments = proxyRequest.getArguments();

		SearchContext searchContext = (SearchContext)arguments[0];

		String searchEngineId = searchContext.getSearchEngineId();

		return SearchEngineUtil.getSearchWriterDestinationName(searchEngineId);
	}

	public void updateDocument(SearchContext searchContext, Document document) {
		throw new UnsupportedOperationException();
	}

	public void updateDocuments(
		SearchContext searchContext, Collection<Document> documents) {

		throw new UnsupportedOperationException();
	}

}