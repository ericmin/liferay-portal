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

import java.io.Serializable;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public interface Hits extends Serializable {

	public Document doc(int n);

	public Document[] getDocs();

	public int getLength();

	public Query getQuery();

	public String[] getQueryTerms();

	public float[] getScores();

	public float getSearchTime();

	public String[] getSnippets();

	public long getStart();

	public float score(int n);

	public void setDocs(Document[] docs);

	public void setLength(int length);

	public void setQuery(Query query);

	public void setQueryTerms(String[] queryTerms);

	public void setScores(float[] scores);

	public void setScores(Float[] scores);

	public void setSearchTime(float time);

	public void setSnippets(String[] snippets);

	public void setStart(long start);

	public String snippet(int n);

	public List<Document> toList();

}