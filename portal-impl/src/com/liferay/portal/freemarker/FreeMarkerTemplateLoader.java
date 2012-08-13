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

package com.liferay.portal.freemarker;

import java.io.IOException;
import java.io.Reader;

/**
 * @author Mika Koivisto
 */
public abstract class FreeMarkerTemplateLoader {

	public static final String JOURNAL_SEPARATOR = "_JOURNAL_CONTEXT_";

	public static final String SERVLET_SEPARATOR = "_SERVLET_CONTEXT_";

	public static final String THEME_LOADER_SEPARATOR =
		"_THEME_LOADER_CONTEXT_";

	public void closeTemplateSource(Object templateSource) {
	}

	public abstract Object findTemplateSource(String name) throws IOException;

	public long getLastModified(Object templateSource) {
		return 0;
	}

	public abstract Reader getReader(Object templateSource, String encoding)
		throws IOException;

}