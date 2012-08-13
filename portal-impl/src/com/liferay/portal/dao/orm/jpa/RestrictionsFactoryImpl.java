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

import com.liferay.portal.kernel.dao.orm.Conjunction;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactory;

import java.util.Collection;
import java.util.Map;

/**
 * @author Prashant Dighe
 * @author Brian Wing Shun Chan
 */
public class RestrictionsFactoryImpl implements RestrictionsFactory {

	public Criterion allEq(Map<String, Criterion> propertyNameValues) {
		throw new UnsupportedOperationException();
	}

	public Criterion and(Criterion lhs, Criterion rhs) {
		throw new UnsupportedOperationException();
	}

	public Criterion between(String propertyName, Object lo, Object hi) {
		throw new UnsupportedOperationException();
	}

	public Conjunction conjunction() {
		throw new UnsupportedOperationException();
	}

	public Disjunction disjunction() {
		throw new UnsupportedOperationException();
	}

	public Criterion eq(String propertyName, Object value) {
		throw new UnsupportedOperationException();
	}

	public Criterion eqProperty(String propertyName, String otherPropertyName) {
		throw new UnsupportedOperationException();
	}

	public Criterion ge(String propertyName, Object value) {
		throw new UnsupportedOperationException();
	}

	public Criterion geProperty(String propertyName, String otherPropertyName) {
		throw new UnsupportedOperationException();
	}

	public Criterion gt(String propertyName, Object value) {
		throw new UnsupportedOperationException();
	}

	public Criterion gtProperty(String propertyName, String otherPropertyName) {
		throw new UnsupportedOperationException();
	}

	public Criterion ilike(String propertyName, Object value) {
		throw new UnsupportedOperationException();
	}

	public Criterion in(String propertyName, Collection<Object> values) {
		throw new UnsupportedOperationException();
	}

	public Criterion in(String propertyName, Object[] values) {
		throw new UnsupportedOperationException();
	}

	public Criterion isEmpty(String propertyName) {
		throw new UnsupportedOperationException();
	}

	public Criterion isNotEmpty(String propertyName) {
		throw new UnsupportedOperationException();
	}

	public Criterion isNotNull(String propertyName) {
		throw new UnsupportedOperationException();
	}

	public Criterion isNull(String propertyName) {
		throw new UnsupportedOperationException();
	}

	public Criterion le(String propertyName, Object value) {
		throw new UnsupportedOperationException();
	}

	public Criterion leProperty(String propertyName, String otherPropertyName) {
		throw new UnsupportedOperationException();
	}

	public Criterion like(String propertyName, Object value) {
		throw new UnsupportedOperationException();
	}

	public Criterion lt(String propertyName, Object value) {
		throw new UnsupportedOperationException();
	}

	public Criterion ltProperty(String propertyName, String otherPropertyName) {
		throw new UnsupportedOperationException();
	}

	public Criterion ne(String propertyName, Object value) {
		throw new UnsupportedOperationException();
	}

	public Criterion neProperty(String propertyName, String otherPropertyName) {
		throw new UnsupportedOperationException();
	}

	public Criterion not(Criterion expression) {
		throw new UnsupportedOperationException();
	}

	public Criterion or(Criterion lhs, Criterion rhs) {
		throw new UnsupportedOperationException();
	}

	public Criterion sizeEq(String propertyName, int size) {
		throw new UnsupportedOperationException();
	}

	public Criterion sizeGe(String propertyName, int size) {
		throw new UnsupportedOperationException();
	}

	public Criterion sizeGt(String propertyName, int size) {
		throw new UnsupportedOperationException();
	}

	public Criterion sizeLe(String propertyName, int size) {
		throw new UnsupportedOperationException();
	}

	public Criterion sizeLt(String propertyName, int size) {
		throw new UnsupportedOperationException();
	}

	public Criterion sizeNe(String propertyName, int size) {
		throw new UnsupportedOperationException();
	}

}