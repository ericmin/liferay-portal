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

package com.liferay.portal.kernel.dao.orm;

/**
 * @author Brian Wing Shun Chan
 */
public interface ScrollableResults {

	public boolean first() throws ORMException;

	public Object[] get() throws ORMException;

	public Object get(int i) throws ORMException;

	public boolean last() throws ORMException;

	public boolean next() throws ORMException;

	public boolean previous() throws ORMException;

	public boolean scroll(int i) throws ORMException;

}