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

package com.liferay.portal.spring.aop;

import java.lang.annotation.Annotation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Shuyang Zhou
 */
public class ServiceMethodAnnotationCache {

	public static <T> T get(
		MethodInvocation methodInvocation,
		Class<? extends Annotation> annotationType, T defaultValue) {

		Annotation[] annotations = _annotations.get(methodInvocation);

		if (annotations == _nullAnnotations) {
			return defaultValue;
		}

		if (annotations == null) {
			return null;
		}

		for (Annotation annotation : annotations) {
			if (annotation.annotationType() == annotationType) {
				return (T)annotation;
			}
		}

		return defaultValue;
	}

	public static void put(
		MethodInvocation methodInvocation, Annotation[] annotations) {

		if ((annotations == null) || (annotations.length == 0)) {
			annotations = _nullAnnotations;
		}

		if (methodInvocation instanceof ServiceBeanMethodInvocation) {
			ServiceBeanMethodInvocation serviceBeanMethodInvocation =
				(ServiceBeanMethodInvocation)methodInvocation;

			methodInvocation = serviceBeanMethodInvocation.toCacheKeyModel();
		}

		_annotations.put(methodInvocation, annotations);
	}

	private static Map<MethodInvocation, Annotation[]> _annotations =
		new ConcurrentHashMap<MethodInvocation, Annotation[]>();
	private static Annotation[] _nullAnnotations = new Annotation[0];

}