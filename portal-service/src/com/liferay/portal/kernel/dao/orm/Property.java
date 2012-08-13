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

import java.util.Collection;

/**
 * @author Brian Wing Shun Chan
 */
public interface Property extends Projection {

	public Order asc();

	public Projection avg();

	public Criterion between(Object min, Object max);

	public Projection count();

	public Order desc();

	public Criterion eq(DynamicQuery subselect);

	public Criterion eq(Object value);

	public Criterion eqAll(DynamicQuery subselect);

	public Criterion eqProperty(Property other);

	public Criterion eqProperty(String other);

	public Criterion ge(DynamicQuery subselect);

	public Criterion ge(Object value);

	public Criterion geAll(DynamicQuery subselect);

	public Criterion geProperty(Property other);

	public Criterion geProperty(String other);

	public Criterion geSome(DynamicQuery subselect);

	public Property getProperty(String propertyName);

	public Projection group();

	public Criterion gt(DynamicQuery subselect);

	public Criterion gt(Object value);

	public Criterion gtAll(DynamicQuery subselect);

	public Criterion gtProperty(Property other);

	public Criterion gtProperty(String other);

	public Criterion gtSome(DynamicQuery subselect);

	public Criterion in(Collection<Object> values);

	public Criterion in(DynamicQuery subselect);

	public Criterion in(Object[] values);

	public Criterion isEmpty();

	public Criterion isNotEmpty();

	public Criterion isNotNull();

	public Criterion isNull();

	public Criterion le(DynamicQuery subselect);

	public Criterion le(Object value);

	public Criterion leAll(DynamicQuery subselect);

	public Criterion leProperty(Property other);

	public Criterion leProperty(String other);

	public Criterion leSome(DynamicQuery subselect);

	public Criterion like(Object value);

	public Criterion lt(DynamicQuery subselect);

	public Criterion lt(Object value);

	public Criterion ltAll(DynamicQuery subselect);

	public Criterion ltProperty(Property other);

	public Criterion ltProperty(String other);

	public Criterion ltSome(DynamicQuery subselect);

	public Projection max();

	public Projection min();

	public Criterion ne(DynamicQuery subselect);

	public Criterion ne(Object value);

	public Criterion neProperty(Property other);

	public Criterion neProperty(String other);

	public Criterion notIn(DynamicQuery subselect);

}