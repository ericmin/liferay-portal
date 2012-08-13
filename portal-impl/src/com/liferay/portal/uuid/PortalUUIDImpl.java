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

package com.liferay.portal.uuid;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.uuid.PortalUUID;

import java.util.UUID;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalUUIDImpl implements PortalUUID {

	public String fromJsSafeUuid(String jsSafeUuid) {
		return StringUtil.replace(
			jsSafeUuid, StringPool.DOUBLE_UNDERLINE, StringPool.DASH);
	}

	public String generate() {
		return UUID.randomUUID().toString();
	}

	public String generate(byte[] bytes) {
		return UUID.nameUUIDFromBytes(bytes).toString();
	}

	public String toJsSafeUuid(String uuid) {
		return StringUtil.replace(
			uuid, StringPool.DASH, StringPool.DOUBLE_UNDERLINE);
	}

}