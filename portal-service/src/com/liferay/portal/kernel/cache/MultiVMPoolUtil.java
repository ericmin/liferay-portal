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

package com.liferay.portal.kernel.cache;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Young
 */
public class MultiVMPoolUtil {

	public static void clear() {
		getMultiVMPool().clear();
	}

	public static void clear(String name) {
		getMultiVMPool().clear(name);
	}

	/**
	 * @deprecated
	 */
	public static Object get(PortalCache portalCache, String key) {
		return getMultiVMPool().get(portalCache, key);
	}

	public static Object get(String name, String key) {
		return getMultiVMPool().get(name, key);
	}

	public static PortalCache getCache(String name) {
		return getMultiVMPool().getCache(name);
	}

	public static PortalCache getCache(String name, boolean blocking) {
		return getMultiVMPool().getCache(name, blocking);
	}

	public static MultiVMPool getMultiVMPool() {
		PortalRuntimePermission.checkGetBeanProperty(MultiVMPoolUtil.class);

		return _multiVMPool;
	}

	/**
	 * @deprecated
	 */
	public static void put(PortalCache portalCache, String key, Object value) {
		getMultiVMPool().put(portalCache, key, value);
	}

	/**
	 * @deprecated
	 */
	public static void put(
		PortalCache portalCache, String key, Serializable value) {

		getMultiVMPool().put(portalCache, key, value);
	}

	public static void put(String name, String key, Object value) {
		getMultiVMPool().put(name, key, value);
	}

	public static void put(String name, String key, Serializable value) {
		getMultiVMPool().put(name, key, value);
	}

	/**
	 * @deprecated
	 */
	public static void remove(PortalCache portalCache, String key) {
		getMultiVMPool().remove(portalCache, key);
	}

	public static void remove(String name, String key) {
		getMultiVMPool().remove(name, key);
	}

	public static void removeCache(String name) {
		getMultiVMPool().removeCache(name);
	}

	public void setMultiVMPool(MultiVMPool multiVMPool) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_multiVMPool = multiVMPool;
	}

	private static MultiVMPool _multiVMPool;

}