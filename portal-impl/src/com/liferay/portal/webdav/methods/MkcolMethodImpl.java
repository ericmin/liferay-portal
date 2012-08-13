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

import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.webdav.Status;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.kernel.webdav.WebDAVStorage;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class MkcolMethodImpl implements Method {

	public int process(WebDAVRequest webDavRequest) throws WebDAVException {
		WebDAVStorage storage = webDavRequest.getWebDAVStorage();
		HttpServletRequest request = webDavRequest.getHttpServletRequest();
		HttpServletResponse response = webDavRequest.getHttpServletResponse();
		long groupId = webDavRequest.getGroupId();

		if (groupId != 0) {
			Status status = storage.makeCollection(webDavRequest);

			if (Validator.isNotNull(status.getObject())) {
				response.setHeader(
					HttpHeaders.LOCATION,
					PortalUtil.getPortalURL(request) +
						webDavRequest.getRootPath() + StringPool.SLASH +
							status.getObject());
			}

			return status.getCode();
		}

		return HttpServletResponse.SC_FORBIDDEN;
	}

}