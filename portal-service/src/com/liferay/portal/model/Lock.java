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

/**
 * The extended model interface for the Lock service. Represents a row in the &quot;Lock_&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see LockModel
 * @see com.liferay.portal.model.impl.LockImpl
 * @see com.liferay.portal.model.impl.LockModelImpl
 * @generated
 */
public interface Lock extends LockModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.model.impl.LockImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public long getExpirationTime();

	public boolean isExpired();

	public boolean isNeverExpires();
}