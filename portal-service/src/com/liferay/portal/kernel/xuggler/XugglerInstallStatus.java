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

package com.liferay.portal.kernel.xuggler;

import com.liferay.portal.kernel.progress.InstallStatus;

/**
 * @author Alexander Chow
 */
public class XugglerInstallStatus implements InstallStatus {

	public static final int COMPLETED = 2;

	public static final int COPYING = 1;

	public static final int DOWNLOADING = 0;

	public int getStatus() {
		return _status;
	}

	public String getStatusLabel() {
		int status = getStatus();

		if (status == 0) {
			return "downloading-xuggler";
		}
		else if (status == 1) {
			return "copying-xuggler";
		}
		else if (status == 2) {
			return "completed";
		}

		return "unknown";
	}

	public void setStatus(int status) {
		_status = status;
	}

	private int _status = UNKNOWN;

}