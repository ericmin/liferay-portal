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

package com.liferay.portal.service;

/**
 * <p>
 * This class is a wrapper for {@link ClassNameService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ClassNameService
 * @generated
 */
public class ClassNameServiceWrapper implements ClassNameService,
	ServiceWrapper<ClassNameService> {
	public ClassNameServiceWrapper(ClassNameService classNameService) {
		_classNameService = classNameService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _classNameService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_classNameService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.ClassName fetchClassName(
		java.lang.String value)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _classNameService.fetchClassName(value);
	}

	public long fetchClassNameId(java.lang.Class<?> clazz) {
		return _classNameService.fetchClassNameId(clazz);
	}

	public long fetchClassNameId(java.lang.String value) {
		return _classNameService.fetchClassNameId(value);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ClassNameService getWrappedClassNameService() {
		return _classNameService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedClassNameService(ClassNameService classNameService) {
		_classNameService = classNameService;
	}

	public ClassNameService getWrappedService() {
		return _classNameService;
	}

	public void setWrappedService(ClassNameService classNameService) {
		_classNameService = classNameService;
	}

	private ClassNameService _classNameService;
}