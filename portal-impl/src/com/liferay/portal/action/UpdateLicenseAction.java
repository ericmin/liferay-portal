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

package com.liferay.portal.action;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.license.util.LicenseManagerUtil;
import com.liferay.portal.license.util.LicenseUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.admin.util.OmniadminUtil;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Amos Fong
 */
public class UpdateLicenseAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		if (ParamUtil.getString(request, Constants.CMD).equals("validateState")) {
			com.liferay.portal.license.LicenseManager.writeKey(request, response);

			return null;
		}

		if (_isValidRequest (request)) {
			String cmd = ParamUtil.getString(request, Constants.CMD);

			String clusterNodeId = ParamUtil.getString(
				request, "clusterNodeId");

			if (cmd.equals("licenseProperties")) {
				String licenseProperties = _getLicenseProperties(clusterNodeId);

				response.setContentType(ContentTypes.TEXT_JAVASCRIPT);

				ServletResponseUtil.write(response, licenseProperties);

				return null;
			}
			else if (cmd.equals("serverInfo")) {
				String serverInfo = _getServerInfo(clusterNodeId);

				response.setContentType(ContentTypes.TEXT_JAVASCRIPT);

				ServletResponseUtil.write(response, serverInfo);

				return null;
			}

			return mapping.findForward("portal.license");
		}
		else {
			response.sendRedirect(
				PortalUtil.getPathContext() + "/c/portal/layout");

			return null;
		}
	}

	private String _getLicenseProperties(String clusterNodeId) {
		List<Map<String, String>> licenseProperties =
			LicenseManagerUtil.getClusterLicenseProperties(clusterNodeId);

		if (licenseProperties == null) {
			return StringPool.BLANK;
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Map<String, String> propertiesMap : licenseProperties) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			for (Map.Entry<String, String> entry : propertiesMap.entrySet()) {
				jsonObject.put(entry.getKey(), entry.getValue());
			}

			jsonArray.put(jsonObject);
		}

		return jsonArray.toString();
	}

	private String _getServerInfo(String clusterNodeId) throws Exception {
		Map<String, String> serverInfo = LicenseUtil.getClusterServerInfo(
			clusterNodeId);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (serverInfo != null) {
			for (Map.Entry<String, String> entry : serverInfo.entrySet()) {
				jsonObject.put(entry.getKey(), entry.getValue());
			}
		}

		return jsonObject.toString();
	}

	private boolean _isOmniAdmin(HttpServletRequest request) {
		User user = null;

		try {
			user = PortalUtil.getUser(request);
		}
		catch (Exception e) {
		}

		if ((user != null) && OmniadminUtil.isOmniadmin(user.getUserId())) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean _isValidRequest(HttpServletRequest request) {
		int licenseState = com.liferay.portal.license.LicenseManager.getLicenseState(request);

		if (licenseState != com.liferay.portal.license.LicenseManager.STATE_GOOD) {
			LicenseUtil.registerOrder(request);

			licenseState = com.liferay.portal.license.LicenseManager.getLicenseState(request);

			if (licenseState != com.liferay.portal.license.LicenseManager.STATE_GOOD) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (_isOmniAdmin (request)) {
			LicenseUtil.registerOrder(request);

			return true;
		}
		else {
			return false;
		}
	}

}