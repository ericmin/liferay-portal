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

package com.liferay.portal.servlet.filters.doubleclick;

import com.liferay.portal.kernel.servlet.ByteBufferServletResponse;
import com.liferay.util.servlet.filters.CacheResponseData;
import com.liferay.util.servlet.filters.CacheResponseUtil;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Olaf Fricke
 * @author Brian Wing Shun Chan
 */
public class DoubleClickController implements Serializable {

	public void control(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws IOException, ServletException {

		boolean firstRequest = false;

		ByteBufferServletResponse byteBufferResponse = null;

		synchronized (this) {
			if (_byteBufferResponse == null) {
				firstRequest = true;

				_byteBufferResponse = new ByteBufferServletResponse(response);
				_throwable = null;
			}

			byteBufferResponse = _byteBufferResponse;
		}

		if (firstRequest) {
			try {
				filterChain.doFilter(request, _byteBufferResponse);
			}
			catch (Throwable t) {
				_throwable = t;
			}
			finally {
				synchronized (this) {
					_byteBufferResponse = null;

					notifyAll();
				}
			}
		}
		else {
			synchronized (this) {
				while (_byteBufferResponse != null) {
					try {
						wait();
					}
					catch (InterruptedException ie) {
						Thread currentThread = Thread.currentThread();

						currentThread.interrupt();
					}
				}
			}
		}

		if (_throwable != null) {
			try {
				throw _throwable;
			}
			catch (IOException ioe) {
				throw ioe;
			}
			catch (ServletException se) {
				throw se;
			}
			catch (RuntimeException re) {
				throw re;
			}
			catch (Error e) {
				throw e;
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		CacheResponseData cacheResponseData = new CacheResponseData(
			byteBufferResponse);

		CacheResponseUtil.write(response, cacheResponseData);
	}

	private ByteBufferServletResponse _byteBufferResponse;
	private Throwable _throwable;

}