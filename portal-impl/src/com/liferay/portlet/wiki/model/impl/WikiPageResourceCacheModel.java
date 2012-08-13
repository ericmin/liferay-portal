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

package com.liferay.portlet.wiki.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.wiki.model.WikiPageResource;

import java.io.Serializable;

/**
 * The cache model class for representing WikiPageResource in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see WikiPageResource
 * @generated
 */
public class WikiPageResourceCacheModel implements CacheModel<WikiPageResource>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", resourcePrimKey=");
		sb.append(resourcePrimKey);
		sb.append(", nodeId=");
		sb.append(nodeId);
		sb.append(", title=");
		sb.append(title);
		sb.append("}");

		return sb.toString();
	}

	public WikiPageResource toEntityModel() {
		WikiPageResourceImpl wikiPageResourceImpl = new WikiPageResourceImpl();

		if (uuid == null) {
			wikiPageResourceImpl.setUuid(StringPool.BLANK);
		}
		else {
			wikiPageResourceImpl.setUuid(uuid);
		}

		wikiPageResourceImpl.setResourcePrimKey(resourcePrimKey);
		wikiPageResourceImpl.setNodeId(nodeId);

		if (title == null) {
			wikiPageResourceImpl.setTitle(StringPool.BLANK);
		}
		else {
			wikiPageResourceImpl.setTitle(title);
		}

		wikiPageResourceImpl.resetOriginalValues();

		return wikiPageResourceImpl;
	}

	public String uuid;
	public long resourcePrimKey;
	public long nodeId;
	public String title;
}