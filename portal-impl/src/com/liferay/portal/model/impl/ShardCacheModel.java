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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.Shard;

import java.io.Serializable;

/**
 * The cache model class for representing Shard in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Shard
 * @generated
 */
public class ShardCacheModel implements CacheModel<Shard>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{shardId=");
		sb.append(shardId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	public Shard toEntityModel() {
		ShardImpl shardImpl = new ShardImpl();

		shardImpl.setShardId(shardId);
		shardImpl.setClassNameId(classNameId);
		shardImpl.setClassPK(classPK);

		if (name == null) {
			shardImpl.setName(StringPool.BLANK);
		}
		else {
			shardImpl.setName(name);
		}

		shardImpl.resetOriginalValues();

		return shardImpl;
	}

	public long shardId;
	public long classNameId;
	public long classPK;
	public String name;
}