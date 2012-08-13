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

package com.liferay.portlet.dynamicdatalists.util;

import com.liferay.portal.kernel.templateparser.TemplateContext;
import com.liferay.portal.kernel.velocity.VelocityEngineUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.util.ContentUtil;

/**
 * @author Marcellus Tavares
 */
public class VelocityTemplateParser extends
	com.liferay.portlet.journal.util.VelocityTemplateParser {

	@Override
	protected String getErrorTemplateContent() {
		return ContentUtil.get(
			PropsValues.DYNAMIC_DATA_LISTS_ERROR_TEMPLATE_VELOCITY);
	}

	@Override
	protected String getErrorTemplateId() {
		return PropsValues.DYNAMIC_DATA_LISTS_ERROR_TEMPLATE_VELOCITY;
	}

	@Override
	protected TemplateContext getTemplateContext() throws Exception {
		return VelocityEngineUtil.getWrappedStandardToolsContext();
	}

}