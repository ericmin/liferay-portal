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

package com.liferay.portal.dao.orm.jpa;

import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactory;
import com.liferay.portal.kernel.dao.orm.ProjectionList;

/**
 * @author Prashant Dighe
 * @author Brian Wing Shun Chan
 */
public class ProjectionFactoryImpl implements ProjectionFactory {

	public Projection alias(Projection projection, String alias) {
		throw new UnsupportedOperationException();
	}

	public Projection avg(String propertyName) {
		throw new UnsupportedOperationException();
	}

	public Projection count(String propertyName) {
		throw new UnsupportedOperationException();
	}

	public Projection countDistinct(String propertyName) {
		throw new UnsupportedOperationException();
	}

	public Projection distinct(Projection projection) {
		throw new UnsupportedOperationException();
	}

	public Projection groupProperty(String propertyName) {
		throw new UnsupportedOperationException();
	}

	public Projection max(String propertyName) {
		throw new UnsupportedOperationException();
	}

	public Projection min(String propertyName) {
		throw new UnsupportedOperationException();
	}

	public ProjectionList projectionList() {
		throw new UnsupportedOperationException();
	}

	public Projection property(String propertyName) {
		throw new UnsupportedOperationException();
	}

	public Projection rowCount() {
		throw new UnsupportedOperationException();
	}

	public Projection sum(String propertyName) {
		throw new UnsupportedOperationException();
	}

}