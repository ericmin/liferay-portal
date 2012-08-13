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

package com.liferay.portal.tools.comparator;

import com.thoughtworks.qdox.model.JavaMethod;

import java.util.Comparator;

/**
 * @author Brian Wing Shun Chan
 */
public class JavaMethodComparator implements Comparator<JavaMethod> {

	public int compare(JavaMethod method1, JavaMethod method2) {
		return method1.getName().compareTo(method2.getName());
	}

}