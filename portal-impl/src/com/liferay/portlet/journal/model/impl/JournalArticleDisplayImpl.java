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

package com.liferay.portlet.journal.model.impl;

import com.liferay.portlet.journal.model.JournalArticleDisplay;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class JournalArticleDisplayImpl implements JournalArticleDisplay {

	public JournalArticleDisplayImpl(
		long companyId, long id, long resourcePrimKey, long groupId,
		long userId, String articleId, double version, String title,
		String urlTitle, String description, String[] availableLocales,
		String content, String type, String structureId, String templateId,
		boolean smallImage, long smallImageId, String smallImageURL,
		int numberOfPages, int currentPage, boolean paginate,
		boolean cacheable) {

		_companyId = companyId;
		_id = id;
		_resourcePrimKey = resourcePrimKey;
		_groupId = groupId;
		_userId = userId;
		_articleId = articleId;
		_version = version;
		_title = title;
		_urlTitle = urlTitle;
		_description = description;
		_availableLocales = availableLocales;
		_content = content;
		_type = type;
		_structureId = structureId;
		_templateId = templateId;
		_smallImage = smallImage;
		_smallImageId = smallImageId;
		_smallImageURL = smallImageURL;
		_numberOfPages = numberOfPages;
		_currentPage = currentPage;
		_paginate = paginate;
		_cacheable = cacheable;
	}

	public String getArticleId() {
		return _articleId;
	}

	public String[] getAvailableLocales() {
		return _availableLocales;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public String getContent() {
		return _content;
	}

	public int getCurrentPage() {
		return _currentPage;
	}

	public String getDescription() {
		return _description;
	}

	public long getGroupId() {
		return _groupId;
	}

	public long getId() {
		return _id;
	}

	public int getNumberOfPages() {
		return _numberOfPages;
	}

	public long getResourcePrimKey() {
		return _resourcePrimKey;
	}

	public long getSmallImageId() {
		return _smallImageId;
	}

	public String getSmallImageURL() {
		return _smallImageURL;
	}

	public String getStructureId() {
		return _structureId;
	}

	public String getTemplateId() {
		return _templateId;
	}

	public String getTitle() {
		return _title;
	}

	public String getType() {
		return _type;
	}

	public String getUrlTitle() {
		return _urlTitle;
	}

	public long getUserId() {
		return _userId;
	}

	public double getVersion() {
		return _version;
	}

	public boolean isCacheable() {
		return _cacheable;
	}

	public boolean isPaginate() {
		return _paginate;
	}

	public boolean isSmallImage() {
		return _smallImage;
	}

	public void setCacheable(boolean cacheable) {
		_cacheable = cacheable;
	}

	public void setContent(String content) {
		_content = content;
	}

	public void setCurrentPage(int currentPage) {
		_currentPage = currentPage;
	}

	public void setNumberOfPages(int numberOfPages) {
		_numberOfPages = numberOfPages;
	}

	public void setPaginate(boolean paginate) {
		_paginate = paginate;
	}

	public void setSmallImage(boolean smallImage) {
		_smallImage = smallImage;
	}

	public void setSmallImageId(long smallImageId) {
		_smallImageId = smallImageId;
	}

	public void setSmallImageURL(String smallImageURL) {
		_smallImageURL = smallImageURL;
	}

	public void setStructureId(String structureId) {
		_structureId = structureId;
	}

	public void setTemplateId(String templateId) {
		_templateId = templateId;
	}

	private String _articleId;
	private String[] _availableLocales;
	private boolean _cacheable;
	private long _companyId;
	private String _content;
	private int _currentPage;
	private String _description;
	private long _groupId;
	private long _id;
	private int _numberOfPages;
	private boolean _paginate;
	private long _resourcePrimKey;
	private boolean _smallImage;
	private long _smallImageId;
	private String _smallImageURL;
	private String _structureId;
	private String _templateId;
	private String _title;
	private String _type;
	private String _urlTitle;
	private long _userId;
	private double _version;

}