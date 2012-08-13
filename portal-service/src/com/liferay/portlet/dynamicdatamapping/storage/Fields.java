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

package com.liferay.portlet.dynamicdatamapping.storage;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class Fields implements Serializable {

	public boolean contains(String name) {
		return _fieldsMap.containsKey(name);
	}

	public Field get(String name) {
		return _fieldsMap.get(name);
	}

	public Set<String> getNames() {
		return _fieldsMap.keySet();
	}

	public Iterator<Field> iterator() {
		return iterator(null);
	}

	public Iterator<Field> iterator(Comparator<Field> comparator) {
		Collection<Field> fieldsCollection = _fieldsMap.values();

		List<Field> fieldsList = new ArrayList<Field>(fieldsCollection);

		if (comparator != null) {
			Collections.sort(fieldsList, comparator);
		}

		return fieldsList.iterator();
	}

	public void put(Field field) {
		_fieldsMap.put(field.getName(), field);
	}

	public Field remove(String name) {
		return _fieldsMap.remove(name);
	}

	private Map<String, Field> _fieldsMap = new HashMap<String, Field>();

}