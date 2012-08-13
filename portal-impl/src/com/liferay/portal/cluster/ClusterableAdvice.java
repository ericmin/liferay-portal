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

package com.liferay.portal.cluster;

import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.Clusterable;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.spring.aop.Swallowable;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.spring.aop.AnnotationChainableMethodAdvice;
import com.liferay.portal.util.PropsValues;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Shuyang Zhou
 */
public class ClusterableAdvice
	extends AnnotationChainableMethodAdvice<Clusterable> {

	@Override
	public void afterPropertiesSet() {
		if (PropsValues.CLUSTER_LINK_ENABLED) {
			super.afterPropertiesSet();
		}
	}

	@Override
	public void afterReturning(MethodInvocation methodInvocation, Object result)
		throws Throwable {

		if (!ClusterInvokeThreadLocal.isEnabled()) {
			return;
		}

		Clusterable clusterable = findAnnotation(methodInvocation);

		if (clusterable == _nullClusterable) {
			return;
		}

		Object thisObject = methodInvocation.getThis();

		if (!(thisObject instanceof IdentifiableBean)) {
			_log.error(
				"Not clustering calls for " + thisObject.getClass().getName() +
					" because it does not implement " +
						IdentifiableBean.class.getName());

			return;
		}

		Method method = methodInvocation.getMethod();

		MethodHandler methodHandler = new MethodHandler(
			method, methodInvocation.getArguments());

		ClusterRequest clusterRequest = ClusterRequest.createMulticastRequest(
			methodHandler, true);

		IdentifiableBean identifiableBean = (IdentifiableBean)thisObject;

		clusterRequest.setBeanIdentifier(identifiableBean.getBeanIdentifier());

		clusterRequest.setServletContextName(_servletContextName);

		ClusterExecutorUtil.execute(clusterRequest);
	}

	@Override
	public boolean afterThrowing(
			MethodInvocation methodInvocation, Throwable throwable)
		throws Throwable {

		if (!(throwable instanceof Swallowable)) {
			return true;
		}

		Swallowable swallowable = (Swallowable)throwable;

		if (swallowable.isSwallowable()) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public Clusterable getNullAnnotation() {
		return _nullClusterable;
	}

	public void setServletContextName(String servletContextName) {
		_servletContextName = servletContextName;
	}

	private static Log _log = LogFactoryUtil.getLog(ClusterableAdvice.class);

	private static Clusterable _nullClusterable =
		new Clusterable() {

			public Class<? extends Annotation> annotationType() {
				return Clusterable.class;
			}

		};

	private String _servletContextName;

}