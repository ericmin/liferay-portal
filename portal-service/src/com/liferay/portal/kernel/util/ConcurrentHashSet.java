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

import java.util.Set;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated {@link com.liferay.portal.kernel.concurrent.ConcurrentHashSet}
 */
public class ConcurrentHashSet<E>
	extends com.liferay.portal.kernel.concurrent.ConcurrentHashSet<E> {

	public ConcurrentHashSet() {
		super();
	}

	public ConcurrentHashSet(int capacity) {
		super(capacity);
	}

	public ConcurrentHashSet(Set<E> set) {
		super(set);
	}

}