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

package com.liferay.portlet.documentlibrary.antivirus;

import java.io.File;
import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 */
public class DummyAntivirusScannerImpl implements AntivirusScanner {

	public boolean isActive() {
		return _ACTIVE;
	}

	public void scan(byte[] bytes) {
	}

	public void scan(File file) {
	}

	public void scan(InputStream inputStream) {
	}

	private static final boolean _ACTIVE = false;

}