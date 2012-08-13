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

package com.liferay.portlet.wiki.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.wiki.model.WikiPageResource;
import com.liferay.portlet.wiki.service.base.WikiPageResourceLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class WikiPageResourceLocalServiceImpl
	extends WikiPageResourceLocalServiceBaseImpl {

	public WikiPageResource addPageResource(long nodeId, String title)
		throws SystemException {

		long pageResourcePrimKey = counterLocalService.increment();

		WikiPageResource pageResource = wikiPageResourcePersistence.create(
			pageResourcePrimKey);

		pageResource.setNodeId(nodeId);
		pageResource.setTitle(title);

		wikiPageResourcePersistence.update(pageResource, false);

		return pageResource;
	}

	public void deletePageResource(long nodeId, String title)
		throws PortalException, SystemException {

		wikiPageResourcePersistence.removeByN_T(nodeId, title);
	}

	public WikiPageResource getPageResource(long pageResourcePrimKey)
		throws PortalException, SystemException {

		return wikiPageResourcePersistence.findByPrimaryKey(
			pageResourcePrimKey);
	}

	public WikiPageResource getPageResource(long nodeId, String title)
		throws PortalException, SystemException {

		return wikiPageResourcePersistence.findByN_T(nodeId, title);
	}

	public long getPageResourcePrimKey(long nodeId, String title)
		throws SystemException {

		WikiPageResource pageResource = wikiPageResourcePersistence.fetchByN_T(
			nodeId, title);

		if (pageResource == null) {
			long pageResourcePrimKey = counterLocalService.increment();

			pageResource = wikiPageResourcePersistence.create(
				pageResourcePrimKey);

			pageResource.setNodeId(nodeId);
			pageResource.setTitle(title);

			wikiPageResourcePersistence.update(pageResource, false);
		}

		return pageResource.getResourcePrimKey();
	}

}