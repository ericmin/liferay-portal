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

package com.liferay.portal.kernel.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**
 * @author Brian Wing Shun Chan
 */
public class ClassLoaderObjectInputStream extends ObjectInputStream {

	public ClassLoaderObjectInputStream(InputStream is, ClassLoader classLoader)
		throws IOException {

		super(is);

		_classLoader = classLoader;
	}

	@Override
	protected Class<?> resolveClass(ObjectStreamClass osc)
		throws ClassNotFoundException {

		return Class.forName(osc.getName(), true, _classLoader);
	}

	private ClassLoader _classLoader;

}