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

package com.liferay.util.servlet.filters;

import com.liferay.portal.kernel.servlet.Header;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Alexander Chow
 */
public class CacheResponseUtil {

	public static void setHeaders(
		HttpServletResponse response, Map<String, List<Header>> headers) {

		for (Map.Entry<String, List<Header>> entry : headers.entrySet()) {
			String headerKey = entry.getKey();
			List<Header> headerValues = ListUtil.copy(entry.getValue());

			for (Header header : headerValues) {
				int type = header.getType();

				if (type == Header.COOKIE_TYPE) {
					response.addCookie(header.getCookieValue());
				}
				else if (type == Header.DATE_TYPE) {
					response.setDateHeader(headerKey, header.getDateValue());
				}
				else if (type == Header.INTEGER_TYPE) {
					response.setIntHeader(headerKey, header.getIntValue());
				}
				else if (type == Header.STRING_TYPE) {
					response.setHeader(headerKey, header.getStringValue());
				}
			}
		}
	}

	public static void write(
			HttpServletResponse response, CacheResponseData cacheResponseData)
		throws IOException {

		setHeaders(response, cacheResponseData.getHeaders());

		response.setContentType(cacheResponseData.getContentType());

		ServletResponseUtil.write(response, cacheResponseData.getByteBuffer());
	}

}