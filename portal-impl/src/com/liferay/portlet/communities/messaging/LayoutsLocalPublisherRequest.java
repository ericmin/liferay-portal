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

package com.liferay.portlet.communities.messaging;

import java.util.Date;
import java.util.Map;

/**
 * @author     Bruno Farache
 * @deprecated {@link com.liferay.portal.messaging.LayoutsLocalPublisherRequest}
 */
public class LayoutsLocalPublisherRequest
	extends com.liferay.portal.messaging.LayoutsLocalPublisherRequest {

	public LayoutsLocalPublisherRequest() {
	}

	public LayoutsLocalPublisherRequest(
		String command, long userId, long sourceGroupId, long targetGroupId,
		boolean privateLayout, Map<Long, Boolean> layoutIdMap,
		Map<String, String[]> parameterMap, Date startDate, Date endDate) {

		super(
			command, userId, sourceGroupId, targetGroupId, privateLayout,
			layoutIdMap, parameterMap, startDate, endDate);
	}

}