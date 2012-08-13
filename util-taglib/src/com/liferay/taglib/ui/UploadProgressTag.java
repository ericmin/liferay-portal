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

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Keith R. Davis
 */
public class UploadProgressTag extends IncludeTag {

	public void setId(String id) {
		_id = id;
	}

	public void setIframeSrc(String iframeSrc) {
		_iframeSrc = iframeSrc;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public void setRedirect(String redirect) {
		_redirect = redirect;
	}

	@Override
	protected void cleanUp() {
		_id = null;
		_iframeSrc = null;
		_message = null;
		_redirect = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:upload-progress:id", _id);
		request.setAttribute(
			"liferay-ui:upload-progress:iframe-src", _iframeSrc);
		request.setAttribute("liferay-ui:upload-progress:message", _message);
		request.setAttribute("liferay-ui:upload-progress:redirect", _redirect);
	}

	private static final String _PAGE =
		"/html/taglib/ui/upload_progress/page.jsp";

	private String _id;
	private String _iframeSrc;
	private String _message;
	private String _redirect;

}