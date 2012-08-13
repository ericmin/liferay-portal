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

package com.liferay.portlet.admin.poller;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.poller.BasePollerProcessor;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;
import com.liferay.portal.kernel.xuggler.XugglerInstallStatus;
import com.liferay.portal.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Sergio González
 */
public class AdminPollerProcessor extends BasePollerProcessor {

	@Override
	protected void doReceive(
		PollerRequest pollerRequest, PollerResponse pollerResponse)
		throws Exception {

		pollerResponse.setParameter(
			PollerResponse.POLLER_HINT_HIGH_CONNECTIVITY,
			Boolean.TRUE.toString());

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		String statusLabel = "unknown";

		XugglerInstallStatus xugglerInstallStatus = getXugglerInstallStatus(
			pollerRequest);

		if (xugglerInstallStatus != null) {
			statusLabel = xugglerInstallStatus.getStatusLabel();
		}

		jsonObject.put("status", statusLabel);

		boolean success = false;

		if (!statusLabel.equals("unknown")) {
			success = true;
		}

		jsonObject.put("success", success);

		pollerResponse.setParameter("status", jsonObject);
	}

	@Override
	protected void doSend(PollerRequest pollerRequest) throws Exception {
		return;
	}

	protected XugglerInstallStatus getXugglerInstallStatus(
		PollerRequest pollerRequest) {

		HttpServletRequest request = pollerRequest.getRequest();

		HttpSession session = request.getSession();

		return (XugglerInstallStatus)session.getAttribute(
			WebKeys.XUGGLER_INSTALL_STATUS);
	}

}