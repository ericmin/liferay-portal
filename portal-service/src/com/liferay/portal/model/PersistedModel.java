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

package com.liferay.portal.model;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Connor McKay
 */
public interface PersistedModel {

	/**
	 * Updates this model instance in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void persist() throws SystemException;

}