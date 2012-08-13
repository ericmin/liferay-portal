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

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.HashCode;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Brian Wing Shun Chan
 */
public class HashCodeImpl implements HashCode {

	public HashCodeImpl() {
		_hashCodeBuilder = new HashCodeBuilder();
	}

	public HashCodeImpl(
		int initialNonZeroOddNumber, int multiplierNonZeroOddNumber) {

		_hashCodeBuilder = new HashCodeBuilder(
			initialNonZeroOddNumber, multiplierNonZeroOddNumber);
	}

	public HashCode append(boolean value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(boolean[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(byte value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(byte[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(char value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(char[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(double value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(double[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(float value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(float[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(int value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(int[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(long value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(long[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(Object value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(Object[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(short value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode append(short[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	public HashCode appendSuper(int superHashCode) {
		_hashCodeBuilder.appendSuper(superHashCode);

		return this;
	}

	public int toHashCode() {
		return _hashCodeBuilder.toHashCode();
	}

	private HashCodeBuilder _hashCodeBuilder;

}