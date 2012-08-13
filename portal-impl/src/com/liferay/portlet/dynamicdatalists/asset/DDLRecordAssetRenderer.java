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

package com.liferay.portlet.dynamicdatalists.asset;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.asset.model.BaseAssetRenderer;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordVersion;
import com.liferay.portlet.dynamicdatalists.service.permission.DDLRecordSetPermission;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Marcellus Tavares
 * @author Sergio González
 */
public class DDLRecordAssetRenderer extends BaseAssetRenderer {

	public DDLRecordAssetRenderer(
		DDLRecord record, DDLRecordVersion recordVersion) {

		_record = record;
		_recordVersion = recordVersion;

		try {
			_recordSet = record.getRecordSet();
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
	}

	public long getClassPK() {
		return _record.getRecordId();
	}

	public long getGroupId() {
		return _record.getGroupId();
	}

	public String getSummary(Locale locale) {
		return StringPool.BLANK;
	}

	public String getTitle(Locale locale) {
		String name = _recordSet.getName(locale);

		return LanguageUtil.format(locale, "new-record-for-list-x", name);
	}

	@Override
	public PortletURL getURLEdit(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
			getControlPanelPlid(liferayPortletRequest),
			PortletKeys.DYNAMIC_DATA_LISTS, PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"struts_action", "/dynamic_data_lists/edit_record");
		portletURL.setParameter(
			"recordId", String.valueOf(_record.getRecordId()));

		return portletURL;
	}

	public long getUserId() {
		return _record.getUserId();
	}

	public String getUserName() {
		return _record.getUserName();
	}

	public String getUuid() {
		return _record.getUuid();
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {
		return DDLRecordSetPermission.contains(
			permissionChecker, _recordSet, ActionKeys.UPDATE);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) {
		return DDLRecordSetPermission.contains(
			permissionChecker, _recordSet, ActionKeys.VIEW);
	}

	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse,
			String template)
		throws Exception {

		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			renderRequest.setAttribute(
				WebKeys.DYNAMIC_DATA_LISTS_RECORD, _record);
			renderRequest.setAttribute(
				WebKeys.DYNAMIC_DATA_LISTS_RECORD_VERSION, _recordVersion);

			String path =
				"/html/portlet/dynamic_data_lists/asset/full_content.jsp";

			return path;
		}
		else {
			return null;
		}
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/history.png";
	}

	private static Log _log = LogFactoryUtil.getLog(
		DDLRecordAssetRenderer.class);

	private DDLRecord _record;
	private DDLRecordSet _recordSet;
	private DDLRecordVersion _recordVersion;

}