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

import com.liferay.portal.model.Lock;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public interface WebDAVStorage {

	public int copyCollectionResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite, long depth)
		throws WebDAVException;

	public int copySimpleResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException;

	public int deleteResource(WebDAVRequest webDavRequest)
		throws WebDAVException;

	public Resource getResource(WebDAVRequest webDavRequest)
		throws WebDAVException;

	public List<Resource> getResources(WebDAVRequest webDavRequest)
		throws WebDAVException;

	public String getRootPath();

	public String getToken();

	public boolean isAvailable(WebDAVRequest webDavRequest)
		throws WebDAVException;

	public boolean isSupportsClassTwo();

	public Status lockResource(
			WebDAVRequest webDavRequest, String owner, long timeout)
		throws WebDAVException;

	public Status makeCollection(WebDAVRequest webDavRequest)
		throws WebDAVException;

	public int moveCollectionResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException;

	public int moveSimpleResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException;

	public int putResource(WebDAVRequest webDavRequest) throws WebDAVException;

	public Lock refreshResourceLock(
			WebDAVRequest webDavRequest, String uuid, long timeout)
		throws WebDAVException;

	public void setRootPath(String rootPath);

	public void setToken(String token);

	public boolean unlockResource(WebDAVRequest webDavRequest, String token)
		throws WebDAVException;

}