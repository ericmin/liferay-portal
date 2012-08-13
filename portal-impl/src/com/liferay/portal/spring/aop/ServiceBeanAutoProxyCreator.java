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

import org.aopalliance.intercept.MethodInterceptor;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopConfigException;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.framework.AopProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;

/**
 * @author Shuyang Zhou
 */
public class ServiceBeanAutoProxyCreator
	extends AbstractAdvisorAutoProxyCreator {

	public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
		_methodInterceptor = methodInterceptor;
	}

	@Override
	protected void customizeProxyFactory(ProxyFactory proxyFactory) {
		proxyFactory.setAopProxyFactory(
			new AopProxyFactory() {

				public AopProxy createAopProxy(AdvisedSupport advisedSupport)
					throws AopConfigException {

					return new ServiceBeanAopProxy(
						advisedSupport, _methodInterceptor);
				}

			}
		);
	}

	@Override
	@SuppressWarnings("rawtypes")
	protected Object[] getAdvicesAndAdvisorsForBean(
		Class beanClass, String beanName, TargetSource targetSource) {

		Object[] advices = DO_NOT_PROXY;

		if (beanName.endsWith(_SERVICE_SUFFIX)) {
			advices = super.getAdvicesAndAdvisorsForBean(
				beanClass, beanName, targetSource);

			if (advices == DO_NOT_PROXY) {
				advices = PROXY_WITHOUT_ADDITIONAL_INTERCEPTORS;
			}
		}

		return advices;
	}

	private static final String _SERVICE_SUFFIX = "Service";

	private MethodInterceptor _methodInterceptor;

}