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

package com.liferay.util;

import com.liferay.portal.kernel.util.HashCode;
import com.liferay.portal.kernel.util.HashCodeFactoryUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class State {

	public State(String id, String name) {
		_id = id;
		_name = name;
	}

	public int compareTo(Object obj) {
		State state = (State)obj;

		if ((getId() != null) && (state.getId() != null)) {
			return getId().toLowerCase().compareTo(state.getId().toLowerCase());
		}
		else if ((getName() != null) && (state.getName() != null)) {
			return getName().toLowerCase().compareTo(
				state.getName().toLowerCase());
		}
		else {
			return -1;
		}
	}

	@Override
	public boolean equals(Object obj) {
		State state = (State)obj;

		if ((getId() != null) && (state.getId() != null)) {
			return getId().equalsIgnoreCase(state.getId());
		}
		else if ((getName() != null) && (state.getName() != null)) {
			return getName().equalsIgnoreCase(state.getName());
		}
		else {
			return false;
		}
	}

	public String getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	@Override
	public int hashCode() {
		HashCode hashCode = HashCodeFactoryUtil.getHashCode();

		hashCode.append(_id);
		hashCode.append(_name);

		return hashCode.toHashCode();
	}

	private String _id;
	private String _name;

}