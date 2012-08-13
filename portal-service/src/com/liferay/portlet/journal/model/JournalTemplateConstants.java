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

package com.liferay.portlet.journal.model;

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author Alexander Chow
 */
public class JournalTemplateConstants {

	public static final String LANG_TYPE_CSS = "css";

	public static final String LANG_TYPE_FTL = "ftl";

	public static final String LANG_TYPE_VM = "vm";

	public static final String LANG_TYPE_XSL = "xsl";

	public static final String[] LANG_TYPES = PropsUtil.getArray(
		PropsKeys.JOURNAL_TEMPLATE_LANGUAGE_TYPES);

}