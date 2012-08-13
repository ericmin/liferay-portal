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

package com.liferay.portal.kernel.process;

import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class PortalClassLoaderObjectInputStream extends ObjectInputStream {

	public PortalClassLoaderObjectInputStream(InputStream inputStream)
		throws IOException {

		super(inputStream);

		_portalClassLoader = PortalClassLoaderUtil.getClassLoader();
	}

	@Override
	protected Class<?> resolveClass(ObjectStreamClass objectStreamClass)
		throws ClassNotFoundException {

		String name = objectStreamClass.getName();

		try {
			return Class.forName(name, false, _portalClassLoader);
		}
		catch (ClassNotFoundException cnfe) {
			Class<?> clazz = _primaryClasses.get(name);

			if (clazz != null) {
				return clazz;
			}
			else {
				throw cnfe;
			}
		}
	}

	private static final Map<String, Class<?>> _primaryClasses =
		new HashMap<String, Class<?>>(8, 1.0F);

	private final ClassLoader _portalClassLoader;

	static {
		_primaryClasses.put("boolean", boolean.class);
		_primaryClasses.put("byte", byte.class);
		_primaryClasses.put("char", char.class);
		_primaryClasses.put("short", short.class);
		_primaryClasses.put("int", int.class);
		_primaryClasses.put("long", long.class);
		_primaryClasses.put("float", float.class);
		_primaryClasses.put("double", double.class);
		_primaryClasses.put("void", void.class);
	}

}