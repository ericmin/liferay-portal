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
import java.util.Map;

/**
 * @author Raymond Augé
 */
public interface RestrictionsFactory {

	public Criterion allEq(Map<String, Criterion> propertyNameValues);

	public Criterion and(Criterion lhs, Criterion rhs);

	public Criterion between(String propertyName, Object lo, Object hi);

	public Conjunction conjunction();

	public Disjunction disjunction();

	public Criterion eq(String propertyName, Object value);

	public Criterion eqProperty(String propertyName, String otherPropertyName);

	public Criterion ge(String propertyName, Object value);

	public Criterion geProperty(String propertyName, String otherPropertyName);

	public Criterion gt(String propertyName, Object value);

	public Criterion gtProperty(String propertyName, String otherPropertyName);

	public Criterion ilike(String propertyName, Object value);

	public Criterion in(String propertyName, Collection<Object> values);

	public Criterion in(String propertyName, Object[] values);

	public Criterion isEmpty(String propertyName);

	public Criterion isNotEmpty(String propertyName);

	public Criterion isNotNull(String propertyName);

	public Criterion isNull(String propertyName);

	public Criterion le(String propertyName, Object value);

	public Criterion leProperty(String propertyName, String otherPropertyName);

	public Criterion like(String propertyName, Object value);

	public Criterion lt(String propertyName, Object value);

	public Criterion ltProperty(String propertyName, String otherPropertyName);

	public Criterion ne(String propertyName, Object value);

	public Criterion neProperty(String propertyName, String otherPropertyName);

	public Criterion not(Criterion expression);

	public Criterion or(Criterion lhs, Criterion rhs);

	public Criterion sizeEq(String propertyName, int size);

	public Criterion sizeGe(String propertyName, int size);

	public Criterion sizeGt(String propertyName, int size);

	public Criterion sizeLe(String propertyName, int size);

	public Criterion sizeLt(String propertyName, int size);

	public Criterion sizeNe(String propertyName, int size);

}