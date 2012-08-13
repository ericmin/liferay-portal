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

package com.liferay.portal.kernel.velocity;

import java.io.Writer;

/**
 * @author Raymond Augé
 */
public interface VelocityEngine {

	public void clearClassLoader(ClassLoader classLoader);

	public void flushTemplate(String velocityTemplateId);

	public VelocityContext getEmptyContext();

	public VelocityContext getRestrictedToolsContext();

	public VelocityContext getStandardToolsContext();

	public VelocityContext getWrappedClassLoaderToolsContext();

	public VelocityContext getWrappedRestrictedToolsContext();

	public VelocityContext getWrappedStandardToolsContext();

	public void init() throws Exception;

	public boolean mergeTemplate(
			String velocityTemplateId, String velocityTemplateContent,
			VelocityContext velocityContext, Writer writer)
		throws Exception;

	public boolean mergeTemplate(
			String velocityTemplateId, VelocityContext velocityContext,
			Writer writer)
		throws Exception;

	public boolean resourceExists(String resource);

}