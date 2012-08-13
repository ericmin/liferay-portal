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

package com.liferay.portal.kernel.freemarker;

import java.io.Writer;

/**
 * @author Mika Koivisto
 */
public interface FreeMarkerEngine {

	public void clearClassLoader(ClassLoader classLoader);

	public void flushTemplate(String freeMarkerTemplateId);

	public FreeMarkerContext getWrappedClassLoaderToolsContext();

	public FreeMarkerContext getWrappedRestrictedToolsContext();

	public FreeMarkerContext getWrappedStandardToolsContext();

	public void init() throws Exception;

	public boolean mergeTemplate(
			String freeMarkerTemplateId, FreeMarkerContext freeMarkerContext,
			Writer writer)
		throws Exception;

	public boolean mergeTemplate(
			String freeMarkerTemplateId, String freemarkerTemplateContent,
			FreeMarkerContext freeMarkerContext, Writer writer)
		throws Exception;

	public boolean resourceExists(String resource);

}