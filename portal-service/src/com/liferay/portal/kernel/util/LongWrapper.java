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

/**
 * @author Brian Wing Shun Chan
 */
public class LongWrapper
	extends PrimitiveWrapper implements Comparable<LongWrapper> {

	public static final Class<?> TYPE = Long.TYPE;

	public LongWrapper() {
		this(0L);
	}

	public LongWrapper(long value) {
		_value = value;
	}

	public int compareTo(LongWrapper longWrapper) {
		if (longWrapper == null) {
			return 1;
		}

		if (getValue() > longWrapper.getValue()) {
			return 1;
		}
		else if (getValue() < longWrapper.getValue()) {
			return -1;
		}
		else {
			return 0;
		}
	}

	public long decrement() {
		return --_value;
	}

	public long getValue() {
		return _value;
	}

	public long increment() {
		return ++_value;
	}

	public void setValue(long value) {
		_value = value;
	}

	private long _value;

}