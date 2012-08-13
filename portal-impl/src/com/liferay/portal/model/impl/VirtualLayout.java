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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutWrapper;
import com.liferay.portal.model.VirtualLayoutConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Raymond Augé
 */
public class VirtualLayout extends LayoutWrapper {

	public VirtualLayout(Layout sourceLayout, Group targetGroup) {
		super(sourceLayout);

		_sourceLayout = sourceLayout;
		_targetGroup = targetGroup;
	}

	@Override
	public Object clone() {
		return new VirtualLayout((Layout)_sourceLayout.clone(), _targetGroup);
	}

	@Override
	public String getFriendlyURL() {
		StringBundler sb = new StringBundler(4);

		sb.append(VirtualLayoutConstants.CANONICAL_URL_SEPARATOR);

		try {
			Group group = _sourceLayout.getGroup();

			sb.append(group.getFriendlyURL());
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		sb.append(_sourceLayout.getFriendlyURL());

		return sb.toString();
	}

	@Override
	public Group getGroup() {
		return getHostGroup();
	}

	@Override
	public long getGroupId() {
		return getVirtualGroupId();
	}

	public Group getHostGroup() {
		return _targetGroup;
	}

	@Override
	public LayoutSet getLayoutSet() {
		if (isPrivateLayout()) {
			return _targetGroup.getPrivateLayoutSet();
		}

		return _targetGroup.getPublicLayoutSet();
	}

	@Override
	public String getRegularURL(HttpServletRequest request)
		throws PortalException, SystemException {

		String layoutURL = _sourceLayout.getRegularURL(request);

		return injectVirtualGroupURL(layoutURL);
	}

	@Override
	public String getResetLayoutURL(HttpServletRequest request)
		throws PortalException, SystemException {

		String layoutURL = _sourceLayout.getResetLayoutURL(request);

		return injectVirtualGroupURL(layoutURL);
	}

	@Override
	public String getResetMaxStateURL(HttpServletRequest request)
		throws PortalException, SystemException {

		String layoutURL = _sourceLayout.getResetMaxStateURL(request);

		return injectVirtualGroupURL(layoutURL);
	}

	public long getSourceGroupId() {
		return _sourceLayout.getGroupId();
	}

	public Layout getSourceLayout() {
		return _sourceLayout;
	}

	public long getVirtualGroupId() {
		return _targetGroup.getGroupId();
	}

	protected String injectVirtualGroupURL(String layoutURL) {
		try {
			Group group = _sourceLayout.getGroup();

			StringBundler sb = new StringBundler(4);

			int pos = layoutURL.indexOf(group.getFriendlyURL());

			sb.append(layoutURL.substring(0, pos));
			sb.append(_targetGroup.getFriendlyURL());
			sb.append(getFriendlyURL());

			pos = layoutURL.indexOf(StringPool.QUESTION);

			if (pos > 0) {
				sb.append(layoutURL.substring(pos));
			}

			return sb.toString();
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(VirtualLayout.class);

	private Layout _sourceLayout;
	private Group _targetGroup;

}