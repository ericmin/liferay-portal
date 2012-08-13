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

package com.liferay.portal.kernel.webdav;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Lock;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseWebDAVStorageImpl implements WebDAVStorage {

	@SuppressWarnings("unused")
	public int copyCollectionResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite, long depth)
		throws WebDAVException {

		return HttpServletResponse.SC_FORBIDDEN;
	}

	@SuppressWarnings("unused")
	public int copySimpleResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException {

		return HttpServletResponse.SC_FORBIDDEN;
	}

	@SuppressWarnings("unused")
	public int deleteResource(WebDAVRequest webDavRequest)
		throws WebDAVException {

		return HttpServletResponse.SC_FORBIDDEN;
	}

	public String getRootPath() {
		return _rootPath;
	}

	public String getToken() {
		return _token;
	}

	public boolean isAvailable(WebDAVRequest webDavRequest)
		throws WebDAVException {

		if (getResource(webDavRequest) == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean isSupportsClassTwo() {
		return false;
	}

	@SuppressWarnings("unused")
	public Status lockResource(
			WebDAVRequest webDavRequest, String owner, long timeout)
		throws WebDAVException {

		return null;
	}

	@SuppressWarnings("unused")
	public Status makeCollection(WebDAVRequest webDavRequest)
		throws WebDAVException {

		return new Status(HttpServletResponse.SC_FORBIDDEN);
	}

	@SuppressWarnings("unused")
	public int moveCollectionResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException {

		return HttpServletResponse.SC_FORBIDDEN;
	}

	@SuppressWarnings("unused")
	public int moveSimpleResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException {

		return HttpServletResponse.SC_FORBIDDEN;
	}

	@SuppressWarnings("unused")
	public int putResource(WebDAVRequest webDavRequest) throws WebDAVException {
		return HttpServletResponse.SC_FORBIDDEN;
	}

	@SuppressWarnings("unused")
	public Lock refreshResourceLock(
			WebDAVRequest webDavRequest, String uuid, long timeout)
		throws WebDAVException {

		return null;
	}

	public void setRootPath(String rootPath) {
		_rootPath = rootPath;
	}

	public void setToken(String token) {
		_token = token;
	}

	@SuppressWarnings("unused")
	public boolean unlockResource(WebDAVRequest webDavRequest, String token)
		throws WebDAVException {

		return false;
	}

	protected long getPlid(long groupId) throws SystemException {
		return LayoutLocalServiceUtil.getDefaultPlid(groupId);
	}

	protected boolean isAddGroupPermissions(long groupId) throws Exception {
		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (!group.isUser()) {
			return true;
		}
		else {
			return false;
		}
	}

	private String _rootPath;
	private String _token;

}