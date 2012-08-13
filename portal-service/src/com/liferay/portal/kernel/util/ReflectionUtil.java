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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Brian Wing Shun Chan
 * @author Miguel Pastor
 */
public class ReflectionUtil {

	public static Class<?> getAnnotationDeclaringClass(
		Class<? extends Annotation> annotationClass, Class<?> clazz) {

		if ((clazz == null) || clazz.equals(Object.class)) {
			return null;
		}

		if (isAnnotationDeclaredInClass(annotationClass, clazz)) {
			return clazz;
		}
		else {
			return getAnnotationDeclaringClass(
				annotationClass, clazz.getSuperclass());
		}
	}

	public static Field getDeclaredField(Class<?> clazz, String name)
		throws Exception {

		Field field = clazz.getDeclaredField(name);

		if (!field.isAccessible()) {
			field.setAccessible(true);
		}

		return field;
	}

	public static Method getDeclaredMethod(
			Class<?> clazz, String name, Class<?> ... parameterTypes)
		throws Exception {

		Method method = clazz.getDeclaredMethod(name, parameterTypes);

		if (!method.isAccessible()) {
			method.setAccessible(true);
		}

		return method;
	}

	public static Class<?>[] getParameterTypes(Object[] arguments) {
		if (arguments == null) {
			return null;
		}

		Class<?>[] parameterTypes = new Class<?>[arguments.length];

		for (int i = 0; i < arguments.length; i++) {
			if (arguments[i] == null) {
				parameterTypes[i] = null;
			}
			else if (arguments[i] instanceof Boolean) {
				parameterTypes[i] = Boolean.TYPE;
			}
			else if (arguments[i] instanceof Byte) {
				parameterTypes[i] = Byte.TYPE;
			}
			else if (arguments[i] instanceof Character) {
				parameterTypes[i] = Character.TYPE;
			}
			else if (arguments[i] instanceof Double) {
				parameterTypes[i] = Double.TYPE;
			}
			else if (arguments[i] instanceof Float) {
				parameterTypes[i] = Float.TYPE;
			}
			else if (arguments[i] instanceof Integer) {
				parameterTypes[i] = Integer.TYPE;
			}
			else if (arguments[i] instanceof Long) {
				parameterTypes[i] = Long.TYPE;
			}
			else if (arguments[i] instanceof Short) {
				parameterTypes[i] = Short.TYPE;
			}
			else {
				parameterTypes[i] = arguments[i].getClass();
			}
		}

		return parameterTypes;
	}

	public static boolean isAnnotationDeclaredInClass(
		Class<? extends Annotation> annotationClass, Class<?> clazz) {

		if ((annotationClass == null) || (clazz == null)) {
			throw new IllegalArgumentException();
		}

		Annotation[] annotations = clazz.getAnnotations();

		for (Annotation annotation : annotations) {
			if (annotationClass.equals(annotation.annotationType())) {
				return true;
			}
		}

		return false;
	}

}