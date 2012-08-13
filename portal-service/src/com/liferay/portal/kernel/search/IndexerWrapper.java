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

import com.liferay.portal.security.permission.PermissionChecker;

import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 */
public class IndexerWrapper implements Indexer {

	public IndexerWrapper(Indexer indexer) {
		_indexer = indexer;
	}

	public void delete(long companyId, String uid) throws SearchException {
		_indexer.delete(companyId, uid);
	}

	public void delete(Object obj) throws SearchException {
		_indexer.delete(obj);
	}

	public String[] getClassNames() {
		return _indexer.getClassNames();
	}

	public Document getDocument(Object obj) throws SearchException {
		return _indexer.getDocument(obj);
	}

	public BooleanQuery getFacetQuery(
			String className, SearchContext searchContext)
		throws Exception {

		return _indexer.getFacetQuery(className, searchContext);
	}

	public BooleanQuery getFullQuery(SearchContext searchContext)
		throws SearchException {

		return _indexer.getFullQuery(searchContext);
	}

	public IndexerPostProcessor[] getIndexerPostProcessors() {
		return _indexer.getIndexerPostProcessors();
	}

	public String getPortletId() {
		return _indexer.getPortletId();
	}

	public String getSearchEngineId() {
		return _indexer.getSearchEngineId();
	}

	public String getSortField(String orderByCol) {
		return _indexer.getSortField(orderByCol);
	}

	public Summary getSummary(
			Document document, Locale locale, String snippet,
			PortletURL portletURL)
		throws SearchException {

		return _indexer.getSummary(document, locale, snippet, portletURL);
	}

	public boolean hasPermission(
			PermissionChecker permissionChecker, long entryClassPK,
			String actionId)
		throws Exception {

		return _indexer.hasPermission(
			permissionChecker, entryClassPK, actionId);
	}

	public boolean isFilterSearch() {
		return _indexer.isFilterSearch();
	}

	public boolean isPermissionAware() {
		return _indexer.isPermissionAware();
	}

	public boolean isStagingAware() {
		return _indexer.isStagingAware();
	}

	public void postProcessContextQuery(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {

		_indexer.postProcessContextQuery(contextQuery, searchContext);
	}

	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		_indexer.postProcessSearchQuery(searchQuery, searchContext);
	}

	public void registerIndexerPostProcessor(
		IndexerPostProcessor indexerPostProcessor) {

		_indexer.registerIndexerPostProcessor(indexerPostProcessor);
	}

	public void reindex(Object obj) throws SearchException {
		_indexer.reindex(obj);
	}

	public void reindex(String className, long classPK) throws SearchException {
		_indexer.reindex(className, classPK);
	}

	public void reindex(String[] ids) throws SearchException {
		_indexer.reindex(ids);
	}

	public Hits search(SearchContext searchContext) throws SearchException {
		return _indexer.search(searchContext);
	}

	public void unregisterIndexerPostProcessor(
		IndexerPostProcessor indexerPostProcessor) {

		_indexer.unregisterIndexerPostProcessor(indexerPostProcessor);
	}

	private Indexer _indexer;

}