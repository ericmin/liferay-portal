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

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.WebDAVRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public interface Method {

	public static final String COPY = "COPY";

	public static final String DELETE = "DELETE";

	public static final String GET = "GET";

	public static final String HEAD = "HEAD";

	public static final String LOCK = "LOCK";

	public static final String MKCOL = "MKCOL";

	public static final String MOVE = "MOVE";

	public static final String OPTIONS = "OPTIONS";

	public static final String PROPFIND = "PROPFIND";

	public static final String PROPPATCH = "PROPPATCH";

	public static final String PUT = "PUT";

	public static final String[] SUPPORTED_METHODS_ARRAY = {
		COPY, DELETE, GET, HEAD, LOCK, MKCOL, MOVE, OPTIONS, PROPFIND,
		PROPPATCH, PUT, Method.UNLOCK
	};

	public static final String SUPPORTED_METHODS = StringUtil.merge(
		SUPPORTED_METHODS_ARRAY);

	public static final String UNLOCK = "UNLOCK";

	/**
	 * Returns -1 or a supported HTTP status code. If it is -1, then the status
	 * code has already been set. Otherwise, the status code needs to be set by
	 * the caller.
	 *
	 * @return -1 or a supported HTTP status code. If it is -1, then the status
	 *         code has already been set. Otherwise, the status code needs to be
	 *         set by the caller.
	 */
	public int process(WebDAVRequest webDavRequest) throws WebDAVException;

}