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

package com.liferay.portal.increment;

import com.liferay.portal.kernel.cache.key.CacheKeyGenerator;
import com.liferay.portal.kernel.cache.key.CacheKeyGeneratorUtil;
import com.liferay.portal.kernel.concurrent.BatchablePipe;
import com.liferay.portal.kernel.increment.BufferedIncrement;
import com.liferay.portal.kernel.increment.Increment;
import com.liferay.portal.kernel.increment.IncrementFactory;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.aop.AnnotationChainableMethodAdvice;

import java.io.Serializable;

import java.lang.annotation.Annotation;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Zsolt Berentey
 * @author Shuyang Zhou
 */
public class BufferedIncrementAdvice
	extends AnnotationChainableMethodAdvice<BufferedIncrement> {

	@Override
	@SuppressWarnings("rawtypes")
	public Object before(MethodInvocation methodInvocation) throws Throwable {
		BufferedIncrement bufferedIncrement = findAnnotation(methodInvocation);

		if (bufferedIncrement == _nullBufferedIncrement) {
			return null;
		}

		Object[] arguments = methodInvocation.getArguments();

		Object value = arguments[arguments.length - 1];

		CacheKeyGenerator cacheKeyGenerator =
			CacheKeyGeneratorUtil.getCacheKeyGenerator(
				BufferedIncrementAdvice.class.getName());

		cacheKeyGenerator.append(methodInvocation.toString());

		for (int i = 0; i < arguments.length - 1; i++) {
			cacheKeyGenerator.append(StringUtil.toHexString(arguments[i]));
		}

		Serializable batchKey = cacheKeyGenerator.finish();

		Increment<?> increment = IncrementFactory.createIncrement(
			bufferedIncrement.incrementClass(), value);

		BufferedIncreasableEntry bufferedIncreasableEntry =
			new BufferedIncreasableEntry(
				nextMethodInterceptor, methodInvocation, batchKey, increment);

		if (_batchablePipe.put(bufferedIncreasableEntry)) {
			if (bufferedIncrement.parallel()) {
				MessageBusUtil.sendMessage(
					DestinationNames.BUFFERED_INCREMENT_PARALLEL,
					_batchablePipe);
			}
			else {
				MessageBusUtil.sendMessage(
					DestinationNames.BUFFERED_INCREMENT_SERIAL, _batchablePipe);
			}
		}

		return nullResult;
	}

	@Override
	public BufferedIncrement getNullAnnotation() {
		return _nullBufferedIncrement;
	}

	@SuppressWarnings("rawtypes")
	private static BatchablePipe<String, BufferedIncreasableEntry>
		_batchablePipe = new BatchablePipe<String, BufferedIncreasableEntry>();

	private static BufferedIncrement _nullBufferedIncrement =
		new BufferedIncrement() {

			public Class<? extends Annotation> annotationType() {
				return BufferedIncrement.class;
			}

			public Class<? extends Increment<?>> incrementClass() {
				return null;
			}

			public boolean parallel() {
				return true;
			}

		};

}