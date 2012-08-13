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

package com.liferay.portlet.wiki.engines.mediawiki;

import java.util.List;

import org.jamwiki.SearchEngine;
import org.jamwiki.model.SearchResultEntry;
import org.jamwiki.model.Topic;

/**
 * @author Jonathan Potter
 */
public class LiferaySearchEngine implements SearchEngine {

	public void addToIndex(Topic arg0) {
	}

	public void addToIndex(Topic topic, List<String> links) {
	}

	public void commit(String arg0) {
	}

	public void deleteFromIndex(Topic topic) {
	}

	public List<SearchResultEntry> findLinkedTo(
		String virtualWiki, String topicName) {

		return null;
	}

	public List<SearchResultEntry> findResults(
		String virtualWiki, String text) {

		return null;
	}

	public void refreshIndex() {
	}

	public void setAutoCommit(boolean autoCommit) {
	}

	public void shutdown() {
	}

	public void updateInIndex(Topic topic) {
	}

	public void updateInIndex(Topic topic, List<String> links) {
	}

}