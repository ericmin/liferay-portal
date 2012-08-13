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

package com.liferay.portal.kernel.util;

import java.io.IOException;

/**
 * @author Shuyang Zhou
 */
public class HeapUtil {

	public static void heapDump(boolean live, boolean binary, String file) {
		int processId = ProcessUtil.getProcessId();

		StringBundler sb = new StringBundler(7);

		sb.append("jmap -dump:");

		if (live) {
			sb.append("live,");
		}

		if (binary) {
			sb.append("format=b,");
		}

		sb.append("file=");
		sb.append(file);
		sb.append(StringPool.SPACE);
		sb.append(processId);

		try {
			Runtime runtime = Runtime.getRuntime();

			runtime.exec(sb.toString());
		}
		catch (IOException ioe) {
			throw new RuntimeException("Unable to perform heap dump", ioe);
		}
	}

}