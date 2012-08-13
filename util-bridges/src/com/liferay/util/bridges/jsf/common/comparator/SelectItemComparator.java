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

package com.liferay.util.bridges.jsf.common.comparator;

import java.util.Comparator;

import javax.faces.model.SelectItem;

/**
 * @author Neil Griffin
 */
public class SelectItemComparator implements Comparator<SelectItem> {

	public SelectItemComparator() {
		this(true);
	}

	public SelectItemComparator(boolean ascending) {
		_ascending = ascending;
	}

	public int compare(SelectItem selectItem1, SelectItem selectItem2) {
		int value = selectItem1.getLabel().compareTo(selectItem2.getLabel());

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	private boolean _ascending;

}