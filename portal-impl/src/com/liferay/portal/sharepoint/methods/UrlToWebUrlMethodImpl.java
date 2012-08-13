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

package com.liferay.portal.sharepoint.methods;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.sharepoint.Property;
import com.liferay.portal.sharepoint.ResponseElement;
import com.liferay.portal.sharepoint.SharepointRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Farache
 */
public class UrlToWebUrlMethodImpl extends BaseMethodImpl {

	public String getMethodName() {
		return _METHOD_NAME;
	}

	@Override
	protected List<ResponseElement> getElements(
		SharepointRequest sharepointRequest) {

		List<ResponseElement> elements = new ArrayList<ResponseElement>();

		String url = sharepointRequest.getParameterValue("url");

		if (_log.isInfoEnabled()) {
			_log.info("URL is " + url);
		}

		if (Validator.isNotNull(url)) {
			elements.add(new Property("webUrl", "/sharepoint"));

			url = url.substring(1);

			elements.add(new Property("fileUrl", StringPool.BLANK));
		}

		return elements;
	}

	private static final String _METHOD_NAME = "url to web url";

	private static Log _log = LogFactoryUtil.getLog(
		UrlToWebUrlMethodImpl.class);

}