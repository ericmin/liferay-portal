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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Bruno Farache
 */
public class HitsImpl implements Hits {

	public HitsImpl() {
	}

	public Document doc(int n) {
		return _docs[n];
	}

	public Document[] getDocs() {
		return _docs;
	}

	public int getLength() {
		return _length;
	}

	public Query getQuery() {
		return _query;
	}

	public String[] getQueryTerms() {
		return _queryTerms;
	}

	public float[] getScores() {
		return _scores;
	}

	public float getSearchTime() {
		return _searchTime;
	}

	public String[] getSnippets() {
		return _snippets;
	}

	public long getStart() {
		return _start;
	}

	public float score(int n) {
		return _scores[n];
	}

	public void setDocs(Document[] docs) {
		_docs = docs;
	}

	public void setLength(int length) {
		_length = length;
	}

	public void setQuery(Query query) {
		_query = query;
	}

	public void setQueryTerms(String[] queryTerms) {
		_queryTerms = queryTerms;
	}

	public void setScores(float[] scores) {
		_scores = scores;
	}

	public void setScores(Float[] scores) {
		float[] primScores = new float[scores.length];

		for (int i = 0; i < scores.length; i++) {
			primScores[i] = scores[i].floatValue();
		}

		setScores(primScores);
	}

	public void setSearchTime(float time) {
		_searchTime = time;
	}

	public void setSnippets(String[] snippets) {
		_snippets = snippets;
	}

	public void setStart(long start) {
		_start = start;
	}

	public String snippet(int n) {
		return _snippets[n];
	}

	public List<Document> toList() {
		List<Document> subset = new ArrayList<Document>(_docs.length);

		for (Document _doc : _docs) {
			subset.add(_doc);
		}

		return subset;
	}

	private Document[] _docs;
	private int _length;
	private Query _query;
	private String[] _queryTerms;
	private float[] _scores = new float[0];
	private float _searchTime;
	private String[] _snippets = {};
	private long _start;

}