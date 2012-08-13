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

package com.liferay.portal.kernel.jmx;

import java.io.ObjectInputStream;

import java.util.Set;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.InvalidAttributeValueException;
import javax.management.ListenerNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.NotCompliantMBeanException;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.OperationsException;
import javax.management.QueryExp;
import javax.management.ReflectionException;
import javax.management.loading.ClassLoaderRepository;

/**
 * @author Michael C. Han
 */
public class RegistryAwareMBeanServer implements MBeanServer {

	public void addNotificationListener(
			ObjectName objectName, NotificationListener notificationListener,
			NotificationFilter notificationFilter, Object handback)
		throws InstanceNotFoundException {

		ObjectName platformObjectName = getPlatformObjectName(objectName);

		_mBeanServer.addNotificationListener(
			platformObjectName, notificationListener, notificationFilter,
			handback);
	}

	public void addNotificationListener(
			ObjectName objectName, ObjectName listenerObjectName,
			NotificationFilter notificationFilter, Object handback)
		throws InstanceNotFoundException {

		ObjectName platformObjectName = getPlatformObjectName(objectName);
		ObjectName platformListenerObjectName = getPlatformObjectName(
			listenerObjectName);

		_mBeanServer.addNotificationListener(
			platformObjectName, platformListenerObjectName, notificationFilter,
			handback);

	}

	public ObjectInstance createMBean(String className, ObjectName objectName)
		throws InstanceAlreadyExistsException, MBeanException,
			   MBeanRegistrationException, NotCompliantMBeanException,
			   ReflectionException {

		return _mBeanServer.createMBean(className, objectName);
	}

	public ObjectInstance createMBean(
			String className, ObjectName objectName, Object[] params,
			String[] signature)
		throws InstanceAlreadyExistsException, MBeanException,
			   MBeanRegistrationException, NotCompliantMBeanException,
			   ReflectionException {

		return _mBeanServer.createMBean(
			className, objectName, params, signature);
	}

	public ObjectInstance createMBean(
			String className, ObjectName objectName, ObjectName loaderName)
		throws InstanceAlreadyExistsException, InstanceNotFoundException,
			   MBeanException, MBeanRegistrationException,
			   NotCompliantMBeanException, ReflectionException {

		return _mBeanServer.createMBean(className, objectName, loaderName);
	}

	public ObjectInstance createMBean(
			String className, ObjectName objectName,
			ObjectName loaderObjectName, Object[] params, String[] signature)
		throws InstanceAlreadyExistsException, InstanceNotFoundException,
			   MBeanException, MBeanRegistrationException,
			   NotCompliantMBeanException, ReflectionException {

		return _mBeanServer.createMBean(
			className, objectName, loaderObjectName, params, signature);
	}

	/**
	 * @deprecated
	 */
	public ObjectInputStream deserialize(ObjectName objectName, byte[] data)
		throws InstanceNotFoundException, OperationsException {

		ObjectName platformObjectName = getPlatformObjectName(objectName);

		return _mBeanServer.deserialize(platformObjectName, data);
	}

	/**
	 * @deprecated
	 */
	public ObjectInputStream deserialize(String className, byte[] data)
		throws OperationsException, ReflectionException {

		return _mBeanServer.deserialize(className, data);
	}

	/**
	 * @deprecated
	 */
	public ObjectInputStream deserialize(
			String className, ObjectName loaderObjectName, byte[] data)
		throws InstanceNotFoundException, OperationsException,
			   ReflectionException {

		return _mBeanServer.deserialize(className, loaderObjectName, data);
	}

	public Object getAttribute(ObjectName objectName, String attribute)
		throws AttributeNotFoundException, InstanceNotFoundException,
			   MBeanException, ReflectionException {

		ObjectName platformObjectName = getPlatformObjectName(objectName);

		return _mBeanServer.getAttribute(platformObjectName, attribute);
	}

	public AttributeList getAttributes(
			ObjectName objectName, String[] attributes)
		throws InstanceNotFoundException, ReflectionException {

		ObjectName platformObjectName = getPlatformObjectName(objectName);

		return _mBeanServer.getAttributes(platformObjectName, attributes);
	}

	public ClassLoader getClassLoader(ObjectName loaderObjectName)
		throws InstanceNotFoundException {

		return _mBeanServer.getClassLoader(loaderObjectName);
	}

	public ClassLoader getClassLoaderFor(ObjectName objectName)
		throws InstanceNotFoundException {

		ObjectName platformObjectName = getPlatformObjectName(objectName);

		return _mBeanServer.getClassLoaderFor(platformObjectName);
	}

	public ClassLoaderRepository getClassLoaderRepository() {
		return _mBeanServer.getClassLoaderRepository();
	}

	public String getDefaultDomain() {
		return _mBeanServer.getDefaultDomain();
	}

	public String[] getDomains() {
		return _mBeanServer.getDomains();
	}

	public Integer getMBeanCount() {
		return _mBeanServer.getMBeanCount();
	}

	public MBeanInfo getMBeanInfo(ObjectName objectName)
		throws InstanceNotFoundException, IntrospectionException,
			   ReflectionException {

		ObjectName platformObjectName = getPlatformObjectName(objectName);

		return _mBeanServer.getMBeanInfo(platformObjectName);
	}

	public ObjectInstance getObjectInstance(ObjectName objectName)
		throws InstanceNotFoundException {

		ObjectName platformObjectName = getPlatformObjectName(objectName);

		return _mBeanServer.getObjectInstance(platformObjectName);
	}

	public Object instantiate(String className)
		throws MBeanException, ReflectionException {

		return _mBeanServer.instantiate(className);
	}

	public Object instantiate(
			String className, Object[] params, String[] signature)
		throws MBeanException, ReflectionException {

		return _mBeanServer.instantiate(className, params, signature);
	}

	public Object instantiate(String className, ObjectName loaderObjectName)
		throws InstanceNotFoundException, MBeanException, ReflectionException {

		return _mBeanServer.instantiate(className, loaderObjectName);
	}

	public Object instantiate(
			String className, ObjectName loaderName, Object[] params,
			String[] signature)
		throws InstanceNotFoundException, MBeanException, ReflectionException {

		return _mBeanServer.instantiate(
			className, loaderName, params, signature);
	}

	public Object invoke(
			ObjectName objectName, String operationName, Object[] params,
			String[] signature)
		throws InstanceNotFoundException, MBeanException, ReflectionException {

		ObjectName platformObjectName = getPlatformObjectName(objectName);

		return _mBeanServer.invoke(
			platformObjectName, operationName, params, signature);
	}

	public boolean isInstanceOf(ObjectName objectName, String className)
		throws InstanceNotFoundException {

		ObjectName platformObjectName = getPlatformObjectName(objectName);

		return _mBeanServer.isInstanceOf(platformObjectName, className);
	}

	public boolean isRegistered(ObjectName objectName) {
		ObjectName platformObjectName = getPlatformObjectName(objectName);

		return _mBeanServer.isRegistered(platformObjectName);
	}

	public Set<ObjectInstance> queryMBeans(
		ObjectName objectName, QueryExp queryExp) {

		return _mBeanServer.queryMBeans(objectName, queryExp);
	}

	public Set<ObjectName> queryNames(
		ObjectName objectName, QueryExp queryExp) {

		return _mBeanServer.queryNames(objectName, queryExp);
	}

	public ObjectInstance registerMBean(Object object, ObjectName objectName)
		throws InstanceAlreadyExistsException, MBeanRegistrationException,
			   NotCompliantMBeanException {

		return _mBeanRegistry.register(
			objectName.getCanonicalName(), object, objectName);
	}

	public void removeNotificationListener(
			ObjectName name, NotificationListener notificationListener)
		throws InstanceNotFoundException, ListenerNotFoundException {

		ObjectName platformObjectName = getPlatformObjectName(name);

		_mBeanServer.removeNotificationListener(
			platformObjectName, notificationListener);
	}

	public void removeNotificationListener(
			ObjectName objectName, NotificationListener notificationListener,
			NotificationFilter notificationFilter, Object handback)
		throws InstanceNotFoundException, ListenerNotFoundException {

		ObjectName platformObjectName = getPlatformObjectName(objectName);

		_mBeanServer.removeNotificationListener(
			platformObjectName, notificationListener, notificationFilter,
			handback);
	}

	public void removeNotificationListener(
			ObjectName objectName, ObjectName listenerObjectName)
		throws InstanceNotFoundException, ListenerNotFoundException {

		ObjectName platformObjectName = getPlatformObjectName(objectName);
		ObjectName platformListenerObjectName = getPlatformObjectName(
			listenerObjectName);

		_mBeanServer.removeNotificationListener(
			platformObjectName, platformListenerObjectName);
	}

	public void removeNotificationListener(
			ObjectName objectName, ObjectName listenerObjectName,
			NotificationFilter notificationFilter, Object handback)
		throws InstanceNotFoundException, ListenerNotFoundException {

		ObjectName platformObjectName = getPlatformObjectName(objectName);
		ObjectName platformListenerObjectName = getPlatformObjectName(
			listenerObjectName);

		_mBeanServer.removeNotificationListener(
			platformObjectName, platformListenerObjectName, notificationFilter,
			handback);

	}

	public void setAttribute(ObjectName objectName, Attribute attribute)
		throws AttributeNotFoundException, InstanceNotFoundException,
			   InvalidAttributeValueException, MBeanException,
			   ReflectionException {

		ObjectName platformObjectName = getPlatformObjectName(objectName);

		_mBeanServer.setAttribute(platformObjectName, attribute);
	}

	public AttributeList setAttributes(
			ObjectName objectName, AttributeList attributeList)
		throws InstanceNotFoundException, ReflectionException {

		ObjectName platformObjectName = getPlatformObjectName(objectName);

		return _mBeanServer.setAttributes(platformObjectName, attributeList);
	}

	public void setMBeanRegistry(MBeanRegistry mBeanRegistry) {
		_mBeanRegistry = mBeanRegistry;
	}

	public void setMBeanServer(MBeanServer mBeanServer) {
		_mBeanServer = mBeanServer;
	}

	public void unregisterMBean(ObjectName objectName)
		throws InstanceNotFoundException, MBeanRegistrationException {

		_mBeanRegistry.unregister(objectName.getCanonicalName(), objectName);
	}

	protected ObjectName getPlatformObjectName(ObjectName objectName) {
		ObjectName platformObjectName = _mBeanRegistry.getObjectName(
			objectName.getCanonicalName());

		if (platformObjectName == null) {
			platformObjectName = objectName;
		}

		return platformObjectName;
	}

	private MBeanRegistry _mBeanRegistry;
	private MBeanServer _mBeanServer;

}