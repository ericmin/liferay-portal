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

import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

/**
 * @author Brian Wing Shun Chan
 */
public class DynamicQueryImpl implements DynamicQuery {

	public DynamicQueryImpl(DetachedCriteria detachedCriteria) {
		_detachedCriteria = detachedCriteria;
	}

	public DynamicQuery add(Criterion criterion) {
		CriterionImpl criterionImpl = (CriterionImpl)criterion;

		_detachedCriteria.add(criterionImpl.getWrappedCriterion());

		return this;
	}

	public DynamicQuery addOrder(Order order) {
		OrderImpl orderImpl = (OrderImpl)order;

		_detachedCriteria.addOrder(orderImpl.getWrappedOrder());

		return this;
	}

	public void compile(Session session) {
		org.hibernate.Session hibernateSession =
			(org.hibernate.Session)session.getWrappedSession();

		_criteria = _detachedCriteria.getExecutableCriteria(hibernateSession);

		if ((_start == null) || (_end == null)) {
			return;
		}

		int start = _start.intValue();
		int end = _end.intValue();

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
			return;
		}

		_criteria = _criteria.setFirstResult(start);
		_criteria = _criteria.setMaxResults(end - start);
	}

	public DetachedCriteria getDetachedCriteria() {
		return _detachedCriteria;
	}

	@SuppressWarnings("rawtypes")
	public List list() {
		return list(true);
	}

	@SuppressWarnings("rawtypes")
	public List list(boolean unmodifiable) {
		List list = _criteria.list();

		if (unmodifiable) {
			return new UnmodifiableList(list);
		}
		else {
			return ListUtil.copy(list);
		}
	}

	public void setLimit(int start, int end) {
		_start = Integer.valueOf(start);
		_end = Integer.valueOf(end);
	}

	public DynamicQuery setProjection(Projection projection) {
		ProjectionImpl projectionImpl = (ProjectionImpl)projection;

		_detachedCriteria.setProjection(projectionImpl.getWrappedProjection());

		return this;
	}

	private Criteria _criteria;
	private DetachedCriteria _detachedCriteria;
	private Integer _end;
	private Integer _start;

}