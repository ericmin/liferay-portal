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

package com.liferay.portal.cluster;

import org.jgroups.Address;

/**
 * @author Shuyang Zhou
 */
public class AddressImpl implements com.liferay.portal.kernel.cluster.Address {

	public AddressImpl(Address address) {
		_address = address;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		AddressImpl addressImpl = null;

		try {
			addressImpl = (AddressImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		if (_address.equals(addressImpl._address)) {
			return true;
		}
		else {
			return false;
		}
	}

	public String getDescription() {
		return _address.toString();
	}

	public Address getRealAddress() {
		return _address;
	}

	@Override
	public int hashCode() {
		return _address.hashCode();
	}

	@Override
	public String toString() {
		return _address.toString();
	}

	private static final long serialVersionUID = 7969878022424426497L;

	private Address _address;

}