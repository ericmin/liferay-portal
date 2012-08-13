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

package com.liferay.portal.messaging.proxy;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.proxy.BaseMultiDestinationProxyBean;
import com.liferay.portal.kernel.messaging.proxy.MessageValuesThreadLocal;
import com.liferay.portal.kernel.messaging.proxy.ProxyRequest;
import com.liferay.portal.kernel.messaging.proxy.ProxyResponse;
import com.liferay.portal.kernel.messaging.sender.MessageSender;
import com.liferay.portal.kernel.messaging.sender.SynchronousMessageSender;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author Michael C. Han
 */
public class MultiDestinationMessagingProxyAdvice {

	public Object invoke(ProceedingJoinPoint proceedingJoinPoint)
		throws Throwable {

		Message message = new Message();

		ProxyRequest proxyRequest = createProxyRequest(proceedingJoinPoint);

		message.setPayload(proxyRequest);

		Map<String, Object> messageValues =
			MessageValuesThreadLocal.getValues();

		if (!messageValues.isEmpty()) {
			for (String key : messageValues.keySet()) {
				message.put(key, messageValues.get(key));
			}
		}

		BaseMultiDestinationProxyBean baseMultiDestinationProxyBean =
			(BaseMultiDestinationProxyBean)proceedingJoinPoint.getTarget();

		String destinationName =
			baseMultiDestinationProxyBean.getDestinationName(proxyRequest);

		if (proxyRequest.isSynchronous() ||
			ProxyModeThreadLocal.isForceSync()) {

			return doInvokeSynchronous(
				message, baseMultiDestinationProxyBean, destinationName);
		}
		else {
			doInvokeAsynchronous(
				message, baseMultiDestinationProxyBean, destinationName);

			return null;
		}
	}

	protected ProxyRequest createProxyRequest(
			ProceedingJoinPoint proceedingJoinPoint)
		throws Exception {

		return new ProxyRequest(
			com.liferay.util.aspectj.AspectJUtil.getMethod(proceedingJoinPoint),
			proceedingJoinPoint.getArgs());
	}

	protected void doInvokeAsynchronous(
		Message message,
		BaseMultiDestinationProxyBean baseMultiDestinationProxyBean,
		String destinationName) {

		MessageSender messageSender =
			baseMultiDestinationProxyBean.getMessageSender();

		if (messageSender == null) {
			throw new IllegalStateException(
				"Asynchronous message sender was not configured properly for " +
					baseMultiDestinationProxyBean.getClass().getName());
		}

		messageSender.send(destinationName, message);
	}

	protected Object doInvokeSynchronous(
			Message message,
			BaseMultiDestinationProxyBean baseMultiDestinationProxyBean,
			String destinationName)
		throws Exception {

		SynchronousMessageSender synchronousMessageSender =
			baseMultiDestinationProxyBean.getSynchronousMessageSender();

		if (synchronousMessageSender == null) {
			throw new IllegalStateException(
				"Synchronous message sender was not configured properly for " +
					baseMultiDestinationProxyBean.getClass().getName());
		}

		ProxyResponse proxyResponse =
			(ProxyResponse)synchronousMessageSender.send(
				destinationName, message);

		if (proxyResponse == null) {
			return null;
		}
		else if (proxyResponse.hasError()) {
			throw proxyResponse.getException();
		}
		else {
			return proxyResponse.getResult();
		}
	}

}