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

package com.liferay.portal.kernel.lock;

/**
 * @author Alexander Chow
 */
public interface LockListener {

	public String getClassName();

	public void onAfterExpire(String key);

	public void onAfterLock(String key);

	public void onAfterRefresh(String key);

	public void onAfterUnlock(String key);

	public void onBeforeExpire(String key);

	public void onBeforeLock(String key);

	public void onBeforeRefresh(String key);

	public void onBeforeUnlock(String key);

}