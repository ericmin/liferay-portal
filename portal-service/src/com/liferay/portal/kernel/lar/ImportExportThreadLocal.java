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

package com.liferay.portal.kernel.lar;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

/**
 * @author Michael C. Han
 */
public class ImportExportThreadLocal {

	public static boolean isExportInProcess() {
		if (isLayoutExportInProcess() || isPortletExportInProcess()) {
			return true;
		}

		return false;
	}

	public static boolean isImportInProcess() {
		if (isPortletImportInProcess() || isLayoutImportInProcess()) {
			return true;
		}

		return false;
	}

	public static boolean isLayoutExportInProcess() {
		return _layoutExportInProcess.get();
	}

	public static boolean isLayoutImportInProcess() {
		return _layoutImportInProcess.get();
	}

	public static boolean isPortletExportInProcess() {
		return _portletExportInProcess.get();
	}

	public static boolean isPortletImportInProcess() {
		return _portletImportInProcess.get();
	}

	public static void setLayoutExportInProcess(boolean inProcess) {
		_layoutExportInProcess.set(inProcess);
	}

	public static void setLayoutImportInProcess(boolean inProcess) {
		_layoutImportInProcess.set(inProcess);
	}

	public static void setPortletExportInProcess(boolean inProcess) {
		_portletExportInProcess.set(inProcess);
	}

	public static void setPortletImportInProcess(boolean inProcess) {
		_portletImportInProcess.set(inProcess);
	}

	private static ThreadLocal<Boolean> _layoutExportInProcess =
		new AutoResetThreadLocal<Boolean>(
			ImportExportThreadLocal.class + "._layoutExportInProcess", false);
	private static ThreadLocal<Boolean> _layoutImportInProcess =
		new AutoResetThreadLocal<Boolean>(
			ImportExportThreadLocal.class + "._layoutImportInProcess", false);
	private static ThreadLocal<Boolean> _portletExportInProcess =
		new AutoResetThreadLocal<Boolean>(
			ImportExportThreadLocal.class + "._portletExportInProcess", false);
	private static ThreadLocal<Boolean> _portletImportInProcess =
		new AutoResetThreadLocal<Boolean>(
			ImportExportThreadLocal.class + "._portletImportInProcess", false);

}