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

package com.liferay.portal.velocity;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.InputStream;

import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * @author Brian Wing Shun Chan
 */
public class ClassLoaderVelocityResourceListener
	extends VelocityResourceListener {

	@Override
	public InputStream getResourceStream(String source)
		throws ResourceNotFoundException {

		try {
			return doGetResourceStream(source);
		}
		catch (Exception e) {
			throw new ResourceNotFoundException(source);
		}
	}

	protected InputStream doGetResourceStream(String source) throws Exception {
		if (source.contains(JOURNAL_SEPARATOR) ||
			source.contains(SERVLET_SEPARATOR) ||
			source.contains(THEME_LOADER_SEPARATOR)) {

			return null;
		}

		ClassLoader classLoader = getClass().getClassLoader();

		if (_log.isDebugEnabled()) {
			_log.debug("Loading " + source);
		}

		return classLoader.getResourceAsStream(source);
	}

	private static Log _log = LogFactoryUtil.getLog(
		ClassLoaderVelocityResourceListener.class);

}