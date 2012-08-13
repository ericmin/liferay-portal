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

package com.liferay.portal.spring.bean;

import com.liferay.portal.cluster.ClusterableAdvice;
import com.liferay.portal.kernel.bean.BeanLocatorException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.lang.PortalSecurityManagerThreadLocal;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

/**
 * @author Michael Young
 * @author Shuyang Zhou
 */
public class BeanReferenceAnnotationBeanPostProcessor
	implements BeanFactoryAware, BeanPostProcessor {

	public BeanReferenceAnnotationBeanPostProcessor() {
		if (_log.isDebugEnabled()) {
			_log.debug("Creating instance " + this.hashCode());
		}
	}

	public void destroy() {
		_beans.clear();
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
		throws BeansException {

		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName)
		throws BeansException {

		if (bean instanceof IdentifiableBean) {
			IdentifiableBean identifiableBean = (IdentifiableBean)bean;

			identifiableBean.setBeanIdentifier(beanName);
		}
		else if (beanName.endsWith("Service")) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					beanName + " should implement " +
						IdentifiableBean.class.getName() +
							" for " + ClusterableAdvice.class.getName());
			}
		}

		boolean enabled = PortalSecurityManagerThreadLocal.isEnabled();

		try {
			PortalSecurityManagerThreadLocal.setEnabled(false);

			_autoInject(bean, beanName, bean.getClass());
		}
		finally {
			PortalSecurityManagerThreadLocal.setEnabled(enabled);
		}

		return bean;
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		_beanFactory = beanFactory;
	}

	private void _autoInject(
		Object targetBean, String targetBeanName, Class<?> beanClass) {

		if ((beanClass == null) || beanClass.isInterface()) {
			return;
		}

		String className = beanClass.getName();

		if (className.equals(_JAVA_LANG_OBJECT) ||
			className.startsWith(_ORG_SPRINGFRAMEWORK)) {

			return;
		}

		Field[] fields = beanClass.getDeclaredFields();

		for (Field field : fields) {
			BeanReference beanReference = field.getAnnotation(
				BeanReference.class);

			String referencedBeanName = null;
			Class<?> referencedBeanType = null;

			if (beanReference != null) {
				referencedBeanName = beanReference.name();
				referencedBeanType = beanReference.type();
			}
			else {
				continue;
			}

			if (!Object.class.equals(referencedBeanType)) {
				referencedBeanName = referencedBeanType.getName();
			}

			Object referencedBean = _beans.get(referencedBeanName);

			if (referencedBean == null) {
				try {
					referencedBean = _beanFactory.getBean(referencedBeanName);
				}
				catch (NoSuchBeanDefinitionException nsbde) {
					try {
						referencedBean = PortalBeanLocatorUtil.locate(
							referencedBeanName);
					}
					catch (BeanLocatorException ble) {
						StringWriter stringWriter = new StringWriter();

						PrintWriter printWriter = new PrintWriter(stringWriter);

						printWriter.print("BeanFactory could not find bean: ");

						nsbde.printStackTrace(printWriter);

						printWriter.print(
							" and PortalBeanLocator failed with: ");
						printWriter.append(ble.getMessage());

						printWriter.close();

						throw new BeanLocatorException(
							stringWriter.toString(), ble);
					}
				}

				_beans.put(referencedBeanName, referencedBean);
			}

			ReflectionUtils.makeAccessible(field);

			try {
				field.set(targetBean, referencedBean);
			}
			catch (Throwable t) {
				throw new BeanCreationException(
					targetBeanName, "Could not inject BeanReference fields", t);
			}
		}

		_autoInject(targetBean, targetBeanName, beanClass.getSuperclass());
	}

	private static final String _JAVA_LANG_OBJECT = "java.lang.Object";

	private static final String _ORG_SPRINGFRAMEWORK = "org.springframework";

	private static Log _log = LogFactoryUtil.getLog(
		BeanReferenceAnnotationBeanPostProcessor.class);

	private BeanFactory _beanFactory;
	private Map<String, Object> _beans = new HashMap<String, Object>();

}