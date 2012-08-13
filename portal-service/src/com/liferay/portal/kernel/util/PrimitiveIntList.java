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
 * @author Michael C. Han
 */
public class PrimitiveIntList {

	public PrimitiveIntList() {
		_elements = new int[10];
	}

	public PrimitiveIntList(int capacity) {
		_elements = new int[capacity];
	}

	public void add(int value) {
		_checkCapacity(_elementsSize + 1);

		_elements[_elementsSize++] = value;
	}

	public void addAll(int[] values) {
		_checkCapacity(_elementsSize + values.length);

		System.arraycopy(values, 0, _elements, _elementsSize, values.length);

		_elementsSize += values.length;
	}

	public int[] getArray() {
		_trim();

		return _elements;
	}

	public int size() {
		return _elementsSize;
	}

	private void _checkCapacity(int minSize) {
		int oldSize = _elements.length;

		if (minSize > oldSize) {
			int[] previousElements = _elements;

			int newCapacity = (oldSize * 3) / 2 + 1;

			if (newCapacity < minSize) {
				newCapacity = minSize;
			}

			_elements = new int[newCapacity];

			System.arraycopy(previousElements, 0, _elements, 0, _elementsSize);
		}
	}

	private void _trim() {
		int oldSize = _elements.length;

		if (_elementsSize < oldSize) {
			int[] previousElements = _elements;

			_elements = new int[_elementsSize];

			System.arraycopy(previousElements, 0, _elements, 0, _elementsSize);
		}
	}

	private int[] _elements;
	private int _elementsSize;

}