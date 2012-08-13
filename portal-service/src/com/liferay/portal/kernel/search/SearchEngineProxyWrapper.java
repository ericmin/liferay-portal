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

import com.liferay.portal.kernel.cluster.Priority;

/**
 * @author Michael C. Han
 */
public class SearchEngineProxyWrapper implements SearchEngine {

	public SearchEngineProxyWrapper(
		SearchEngine searchEngine, IndexSearcher indexSearcher,
		IndexWriter indexWriter) {

		_indexSearcher = indexSearcher;
		_indexWriter = indexWriter;
		_searchEngine = searchEngine;
	}

	public BooleanClauseFactory getBooleanClauseFactory() {
		return _searchEngine.getBooleanClauseFactory();
	}

	public BooleanQueryFactory getBooleanQueryFactory() {
		return _searchEngine.getBooleanQueryFactory();
	}

	public Priority getClusteredWritePriority() {
		return _searchEngine.getClusteredWritePriority();
	}

	public IndexSearcher getIndexSearcher() {
		return _indexSearcher;
	}

	public IndexWriter getIndexWriter() {
		return _indexWriter;
	}

	public SearchEngine getSearchEngine() {
		return _searchEngine;
	}

	public TermQueryFactory getTermQueryFactory() {
		return _searchEngine.getTermQueryFactory();
	}

	public TermRangeQueryFactory getTermRangeQueryFactory() {
		return _searchEngine.getTermRangeQueryFactory();
	}

	public String getVendor() {
		return _searchEngine.getVendor();
	}

	public boolean isClusteredWrite() {
		return _searchEngine.isClusteredWrite();
	}

	public boolean isLuceneBased() {
		return _searchEngine.isLuceneBased();
	}

	private IndexSearcher _indexSearcher;
	private IndexWriter _indexWriter;
	private SearchEngine _searchEngine;

}