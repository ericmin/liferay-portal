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

package com.liferay.portlet.ratings.service.persistence;

/**
 * @author Brian Wing Shun Chan
 */
public interface RatingsEntryFinder {
	public java.util.List<com.liferay.portlet.ratings.model.RatingsEntry> findByU_C_C(
		long userId, long classNameId, java.util.List<java.lang.Long> classPKs)
		throws com.liferay.portal.kernel.exception.SystemException;
}