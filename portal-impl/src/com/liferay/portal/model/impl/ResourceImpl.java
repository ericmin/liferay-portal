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
import com.liferay.portal.model.ResourceCode;
import com.liferay.portal.service.ResourceCodeLocalServiceUtil;

/**
 * Represents a permissionable resource in permissions versions &lt; 6.
 *
 * @author Brian Wing Shun Chan
 */
public class ResourceImpl extends ResourceBaseImpl {

	public ResourceImpl() {
	}

	public long getCompanyId() throws PortalException, SystemException {
		if (_companyId != 0) {
			return _companyId;
		}
		else {
			ResourceCode resourceCode =
				ResourceCodeLocalServiceUtil.getResourceCode(getCodeId());

			return resourceCode.getCompanyId();
		}
	}

	public String getName() throws PortalException, SystemException {
		if (_name != null) {
			return _name;
		}
		else {
			ResourceCode resourceCode =
				ResourceCodeLocalServiceUtil.getResourceCode(getCodeId());

			return resourceCode.getName();
		}
	}

	public int getScope() throws PortalException, SystemException {
		if (_scope != 0) {
			return _scope;
		}
		else {
			ResourceCode resourceCode =
				ResourceCodeLocalServiceUtil.getResourceCode(getCodeId());

			return resourceCode.getScope();
		}
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setScope(int scope) {
		_scope = scope;
	}

	private long _companyId;
	private String _name;
	private int _scope;

}