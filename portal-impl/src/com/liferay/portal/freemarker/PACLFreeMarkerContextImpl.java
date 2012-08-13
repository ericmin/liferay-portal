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

import com.liferay.portal.security.pacl.PACLPolicy;

import java.util.Map;

/**
 * @author Raymond Augé
 */
public class PACLFreeMarkerContextImpl extends FreeMarkerContextImpl {

	public PACLFreeMarkerContextImpl(
		Map<String, Object> context, PACLPolicy paclPolicy) {

		super(context);

		_paclPolicy = paclPolicy;
	}

	public PACLPolicy getPaclPolicy() {
		return _paclPolicy;
	}

	private PACLPolicy _paclPolicy;

}