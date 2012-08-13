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

package com.liferay.portal.dao.orm.hibernate;

import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactory;
import com.liferay.portal.kernel.dao.orm.ProjectionList;

import org.hibernate.criterion.Projections;

/**
 * @author Brian Wing Shun Chan
 */
public class ProjectionFactoryImpl implements ProjectionFactory {

	public Projection alias(Projection projection, String alias) {
		ProjectionImpl projectionImpl = (ProjectionImpl)projection;

		return new ProjectionImpl(
			Projections.alias(projectionImpl.getWrappedProjection(), alias));
	}

	public Projection avg(String propertyName) {
		return new ProjectionImpl(Projections.avg(propertyName));
	}

	public Projection count(String propertyName) {
		return new ProjectionImpl(Projections.count(propertyName));
	}

	public Projection countDistinct(String propertyName) {
		return new ProjectionImpl(Projections.countDistinct(propertyName));
	}

	public Projection distinct(Projection projection) {
		ProjectionImpl projectionImpl = (ProjectionImpl)projection;

		return new ProjectionImpl(
			Projections.distinct(projectionImpl.getWrappedProjection()));
	}

	public Projection groupProperty(String propertyName) {
		return new ProjectionImpl(Projections.groupProperty(propertyName));
	}

	public Projection max(String propertyName) {
		return new ProjectionImpl(Projections.max(propertyName));
	}

	public Projection min(String propertyName) {
		return new ProjectionImpl(Projections.min(propertyName));
	}

	public ProjectionList projectionList() {
		return new ProjectionListImpl(Projections.projectionList());
	}

	public Projection property(String propertyName) {
		return new ProjectionImpl(Projections.property(propertyName));
	}

	public Projection rowCount() {
		return new ProjectionImpl(Projections.rowCount());
	}

	public Projection sum(String propertyName) {
		return new ProjectionImpl(Projections.sum(propertyName));
	}

}