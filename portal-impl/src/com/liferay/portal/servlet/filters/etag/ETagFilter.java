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

package com.liferay.portal.servlet.filters.etag;

import com.liferay.portal.kernel.servlet.ByteBufferServletResponse;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.util.servlet.filters.CacheResponseUtil;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eduardo Lundgren
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Shuyang Zhou
 */
public class ETagFilter extends BasePortalFilter {

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		if (ParamUtil.getBoolean(request, _ETAG, true)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		ByteBufferServletResponse byteBufferResponse =
			new ByteBufferServletResponse(response);

		processFilter(
			ETagFilter.class, request, byteBufferResponse, filterChain);

		if (!ETagUtil.processETag(request, response, byteBufferResponse)) {
			CacheResponseUtil.setHeaders(
				response, byteBufferResponse.getHeaders());

			ServletResponseUtil.write(
				response, byteBufferResponse.getByteBuffer());
		}
	}

	private static final String _ETAG = "etag";

}