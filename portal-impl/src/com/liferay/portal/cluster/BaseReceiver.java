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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.jgroups.Address;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.View;

/**
 * @author Tina Tian
 */
public class BaseReceiver implements Receiver {

	public void block() {
	}

	public byte[] getState() {
		return null;
	}

	public View getView() {
		return view;
	}

	public void receive(Message message) {
	}

	public void setState(byte[] state) {
	}

	public void suspect(Address address) {
	}

	public void viewAccepted(View view) {
		if (_log.isInfoEnabled()) {
			_log.info("Accepted view " + view);
		}

		this.view = view;
	}

	protected volatile View view;

	private static Log _log = LogFactoryUtil.getLog(BaseReceiver.class);

}