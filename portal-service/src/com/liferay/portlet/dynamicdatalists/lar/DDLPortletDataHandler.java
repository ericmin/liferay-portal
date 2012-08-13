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

package com.liferay.portlet.dynamicdatalists.lar;

import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;

/**
 * @author Michael C. Han
 */
public interface DDLPortletDataHandler {

	public void exportRecordSet(
			PortletDataContext portletDataContext, Element recordSetsElement,
			DDLRecordSet recordSet)
		throws Exception;

	public void importRecordSet(
			PortletDataContext portletDataContext, Element recordSetElement)
		throws Exception;

}