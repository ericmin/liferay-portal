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

package com.liferay.portlet.shopping.search;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portlet.shopping.model.ShoppingOrderConstants;

import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class OrderSearchTerms extends OrderDisplayTerms {

	public OrderSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		emailAddress = DAOParamUtil.getLike(portletRequest, EMAIL_ADDRESS);
		firstName = DAOParamUtil.getLike(portletRequest, FIRST_NAME);
		lastName = DAOParamUtil.getLike(portletRequest, LAST_NAME);
		number = DAOParamUtil.getLike(portletRequest, NUMBER);
		status = DAOParamUtil.getString(portletRequest, STATUS);
	}

	@Override
	public String getStatus() {
		if (status == null) {
			return null;
		}
		else if (status.equals(ShoppingOrderConstants.STATUSES[0])) {
			return ShoppingOrderConstants.STATUS_CHECKOUT;
		}
		else if (status.equals(ShoppingOrderConstants.STATUSES[1])) {
			return ShoppingOrderConstants.STATUS_COMPLETED;
		}
		else if (status.equals(ShoppingOrderConstants.STATUSES[2])) {
			return ShoppingOrderConstants.STATUS_DENIED;
		}
		else if (status.equals(ShoppingOrderConstants.STATUSES[3])) {
			return ShoppingOrderConstants.STATUS_PENDING;
		}
		else if (status.equals(ShoppingOrderConstants.STATUSES[4])) {
			return ShoppingOrderConstants.STATUS_REFUNDED;
		}
		else {
			return null;
		}
	}

}