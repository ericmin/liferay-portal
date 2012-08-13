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

package com.liferay.portal.kernel.notifications;

import java.util.Comparator;

/**
 * @author Edward Han
 */
public class NotificationEventComparator
	implements Comparator<NotificationEvent> {

	public NotificationEventComparator() {
		this(true);
	}

	public NotificationEventComparator(boolean ascending) {
		_ascending = ascending;
	}

	public int compare(
		NotificationEvent notificationEvent1,
		NotificationEvent notificationEvent2) {

		long deliverBy1 = notificationEvent1.getDeliverBy();
		long deliverBy2 = notificationEvent2.getDeliverBy();

		int value = 0;

		if (deliverBy1 < deliverBy2) {
			value = -1;
		}
		else if (deliverBy1 > deliverBy2) {
			value = 1;
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	private boolean _ascending;

}