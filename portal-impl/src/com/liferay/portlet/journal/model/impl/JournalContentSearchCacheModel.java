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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.journal.model.JournalContentSearch;

import java.io.Serializable;

/**
 * The cache model class for representing JournalContentSearch in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JournalContentSearch
 * @generated
 */
public class JournalContentSearchCacheModel implements CacheModel<JournalContentSearch>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{contentSearchId=");
		sb.append(contentSearchId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", privateLayout=");
		sb.append(privateLayout);
		sb.append(", layoutId=");
		sb.append(layoutId);
		sb.append(", portletId=");
		sb.append(portletId);
		sb.append(", articleId=");
		sb.append(articleId);
		sb.append("}");

		return sb.toString();
	}

	public JournalContentSearch toEntityModel() {
		JournalContentSearchImpl journalContentSearchImpl = new JournalContentSearchImpl();

		journalContentSearchImpl.setContentSearchId(contentSearchId);
		journalContentSearchImpl.setGroupId(groupId);
		journalContentSearchImpl.setCompanyId(companyId);
		journalContentSearchImpl.setPrivateLayout(privateLayout);
		journalContentSearchImpl.setLayoutId(layoutId);

		if (portletId == null) {
			journalContentSearchImpl.setPortletId(StringPool.BLANK);
		}
		else {
			journalContentSearchImpl.setPortletId(portletId);
		}

		if (articleId == null) {
			journalContentSearchImpl.setArticleId(StringPool.BLANK);
		}
		else {
			journalContentSearchImpl.setArticleId(articleId);
		}

		journalContentSearchImpl.resetOriginalValues();

		return journalContentSearchImpl;
	}

	public long contentSearchId;
	public long groupId;
	public long companyId;
	public boolean privateLayout;
	public long layoutId;
	public String portletId;
	public String articleId;
}