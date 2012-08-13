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

import com.liferay.portal.kernel.freemarker.FreeMarkerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mika Koivisto
 */
public class FreeMarkerContextImpl implements FreeMarkerContext {

	public FreeMarkerContextImpl() {
		_context = new ConcurrentHashMap<String, Object>();
	}

	public FreeMarkerContextImpl(Map<String, Object> context) {
		_context = new ConcurrentHashMap<String, Object>();

		_context.putAll(context);
	}

	public Object get(String key) {
		return _context.get(key);
	}

	public Map<String, Object> getWrappedContext() {
		return _context;
	}

	public void put(String key, Object value) {
		_context.put(key, value);
	}

	private Map<String, Object> _context;

}