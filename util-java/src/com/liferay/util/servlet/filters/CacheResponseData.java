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

package com.liferay.util.servlet.filters;

import com.liferay.portal.kernel.servlet.ByteBufferServletResponse;
import com.liferay.portal.kernel.servlet.Header;

import java.io.Serializable;

import java.nio.ByteBuffer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael Young
 * @author Shuyang Zhou
 */
public class CacheResponseData implements Serializable {

	public CacheResponseData(ByteBufferServletResponse byteBufferResponse) {
		_byteBuffer = byteBufferResponse.getByteBuffer();
		_content = _byteBuffer.array();
		_contentType = byteBufferResponse.getContentType();
		_headers = byteBufferResponse.getHeaders();
	}

	public Object getAttribute(String name) {
		return _attributes.get(name);
	}

	public ByteBuffer getByteBuffer() {
		if (_byteBuffer == null) {
			_byteBuffer = ByteBuffer.wrap(_content);
		}

		return _byteBuffer;
	}

	public String getContentType() {
		return _contentType;
	}

	public Map<String, List<Header>> getHeaders() {
		return _headers;
	}

	public void setAttribute(String name, Object value) {
		_attributes.put(name, value);
	}

	private Map<String, Object> _attributes = new HashMap<String, Object>();
	private transient ByteBuffer _byteBuffer;
	private byte[] _content;
	private String _contentType;
	private Map<String, List<Header>> _headers;

}