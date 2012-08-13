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

package com.liferay.portal.util.comparator;

import com.liferay.portal.model.Portlet;

import java.io.Serializable;

import java.util.Comparator;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletRenderWeightComparator
	implements Comparator<Portlet>, Serializable {

	public int compare(Portlet portlet1, Portlet portlet2) {
		int renderWeight1 = portlet1.getRenderWeight();
		int renderWeight2 = portlet2.getRenderWeight();

		if (renderWeight1 < renderWeight2) {
			return 1;
		}
		else if (renderWeight1 > renderWeight2) {
			return -1;
		}
		else {
			return portlet1.getPortletId().compareTo(portlet2.getPortletId());
		}
	}

}