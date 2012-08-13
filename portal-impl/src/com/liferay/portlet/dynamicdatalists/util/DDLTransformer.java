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

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.templateparser.BaseTransformer;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.util.PropsUtil;

/**
 * @author Marcellus Tavares
 */
public class DDLTransformer extends BaseTransformer {

	@Override
	protected String getTemplateParserClassName(String langType) {
		return PropsUtil.get(
			PropsKeys.DYNAMIC_DATA_LISTS_TEMPLATE_LANGUAGE_PARSER,
			new Filter(langType));
	}

	@Override
	protected String[] getTransformerListenersClassNames() {
		return PropsUtil.getArray(
			PropsKeys.DYNAMIC_DATA_LISTS_TRANSFORMER_LISTENER);
	}

}