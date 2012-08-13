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

package com.liferay.portal.dao.shard;

import java.util.Map;

/**
 * @author Alexander Chow
 */
public class ManualShardSelector implements ShardSelector {

	public String getShardName(
		String scope, String shardName, Map<String, String> params) {

		return shardName;
	}

}