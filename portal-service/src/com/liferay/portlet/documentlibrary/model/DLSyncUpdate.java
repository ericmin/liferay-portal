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

package com.liferay.portlet.documentlibrary.model;

import com.liferay.portal.kernel.json.JSON;

import java.util.Date;
import java.util.List;

/**
 * @author Michael Young
 */
@JSON
public class DLSyncUpdate {

	public DLSyncUpdate(List<DLSync> dlSyncs, Date lastAccessDate) {
		_dlSyncs = dlSyncs;
		_lastAccessDate = lastAccessDate;
	}

	@JSON
	public List<DLSync> getDLSyncs() {
		return _dlSyncs;
	}

	public Date getLastAccessDate() {
		return _lastAccessDate;
	}

	private List<DLSync> _dlSyncs;
	private Date _lastAccessDate = new Date();

}