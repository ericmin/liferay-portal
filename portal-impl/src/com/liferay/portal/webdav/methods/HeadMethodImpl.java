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

package com.liferay.portal.webdav.methods;

import com.liferay.portal.kernel.webdav.Resource;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.kernel.webdav.WebDAVStorage;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class HeadMethodImpl implements Method {

	public int process(WebDAVRequest webDavRequest) throws WebDAVException {
		try {
			WebDAVStorage storage = webDavRequest.getWebDAVStorage();
			HttpServletResponse response =
				webDavRequest.getHttpServletResponse();

			Resource resource = storage.getResource(webDavRequest);

			if (resource == null) {
				return HttpServletResponse.SC_NOT_FOUND;
			}

			if (!resource.isCollection()) {
				response.setContentLength((int)resource.getSize());
			}

			return HttpServletResponse.SC_OK;
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
	}

}