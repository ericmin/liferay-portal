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

package com.liferay.portal.webdav;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.webdav.BaseResourceImpl;
import com.liferay.portal.kernel.webdav.BaseWebDAVStorageImpl;
import com.liferay.portal.kernel.webdav.Resource;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.kernel.webdav.WebDAVUtil;
import com.liferay.portal.model.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Chow
 */
public class GroupWebDAVStorageImpl extends BaseWebDAVStorageImpl {

	public Resource getResource(WebDAVRequest webDavRequest)
		throws WebDAVException {

		verifyGroup(webDavRequest);

		String path = getRootPath() + webDavRequest.getPath();

		return new BaseResourceImpl(path, StringPool.BLANK, StringPool.BLANK);
	}

	public List<Resource> getResources(WebDAVRequest webDavRequest)
		throws WebDAVException {

		verifyGroup(webDavRequest);

		List<Resource> resources = new ArrayList<Resource>();

		String path = getRootPath() + webDavRequest.getPath();

		for (String token : WebDAVUtil.getStorageTokens()) {
			resources.add(new BaseResourceImpl(path, token, token));
		}

		return resources;
	}

	protected void verifyGroup(WebDAVRequest webDavRequest)
		throws WebDAVException {

		String path = webDavRequest.getPath();

		try {
			long userId = webDavRequest.getUserId();

			List<Group> groups = WebDAVUtil.getGroups(userId);

			for (Group group : groups) {
				if (path.equals(group.getFriendlyURL())) {
					return;
				}
			}
		}
		catch (Exception e) {
		}

		throw new WebDAVException(
			"Invalid group for given credentials " +
				webDavRequest.getRootPath() + path);
	}

}