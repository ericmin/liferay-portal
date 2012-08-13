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

package com.liferay.portal.dao.jdbc.aop;

import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.transaction.TransactionInterceptor;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;

import org.springframework.transaction.interceptor.TransactionAttribute;

/**
 * @author Michael Young
 */
public class DynamicDataSourceTransactionInterceptor
	extends TransactionInterceptor {

	public void afterPropertiesSet() {
		if (_dynamicDataSourceTargetSource == null) {
			_dynamicDataSourceTargetSource =
				(DynamicDataSourceTargetSource)InfrastructureUtil.
					getDynamicDataSourceTargetSource();
		}
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		if (_dynamicDataSourceTargetSource == null) {
			return super.invoke(methodInvocation);
		}

		Class<?> targetClass = null;

		if (methodInvocation.getThis() != null) {
			Object thisObject = methodInvocation.getThis();

			targetClass = thisObject.getClass();
		}

		Method targetMethod = methodInvocation.getMethod();

		TransactionAttribute transactionAttribute =
			transactionAttributeSource.getTransactionAttribute(
				targetMethod, targetClass);

		if ((transactionAttribute != null) &&
			transactionAttribute.isReadOnly()) {

			_dynamicDataSourceTargetSource.setOperation(Operation.READ);
		}
		else {
			_dynamicDataSourceTargetSource.setOperation(Operation.WRITE);
		}

		_dynamicDataSourceTargetSource.pushMethod(
			targetClass.getName().concat(StringPool.PERIOD).concat(
				targetMethod.getName()));

		Object returnValue = null;

		try {
			returnValue = super.invoke(methodInvocation);
		}
		finally {
			_dynamicDataSourceTargetSource.popMethod();
		}

		return returnValue;
	}

	public void setDynamicDataSourceTargetSource(
		DynamicDataSourceTargetSource dynamicDataSourceTargetSource) {

		_dynamicDataSourceTargetSource = dynamicDataSourceTargetSource;
	}

	private DynamicDataSourceTargetSource _dynamicDataSourceTargetSource;

}