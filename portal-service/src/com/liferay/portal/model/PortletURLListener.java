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

package com.liferay.portal.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public interface PortletURLListener extends Serializable {

	public String getListenerClass();

	public PortletApp getPortletApp();

	public void setListenerClass(String listenerClass);

	public void setPortletApp(PortletApp portletApp);

}