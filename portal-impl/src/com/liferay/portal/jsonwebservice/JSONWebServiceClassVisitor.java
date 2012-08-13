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

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringUtil;

import jodd.asm.EmptyClassVisitor;

/**
 * @author Igor Spasic
 */
public class JSONWebServiceClassVisitor extends EmptyClassVisitor {

	public String getClassName() {
		return _className;
	}

	@Override
	public void visit(
		int version, int access, String name, String signature,
		String superName, String[] interfaces) {

		_className = StringUtil.replace(name, CharPool.SLASH, CharPool.PERIOD);
	}

	private String _className;

}