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

package com.liferay.portlet.dynamicdatamapping.storage.query;

/**
 * @author Marcellus Tavares
 */
public class ConditionFactoryImpl implements ConditionFactory {

	public Junction conjunction() {
		return new JunctionImpl(LogicalOperator.AND);
	}

	public Junction disjunction() {
		return new JunctionImpl(LogicalOperator.OR);
	}

	public Condition eq(String name, Object value) {
		return new FieldConditionImpl(name, value, ComparisonOperator.EQUALS);
	}

	public Condition gt(String name, Object value) {
		return new FieldConditionImpl(
			name, value, ComparisonOperator.GREATER_THAN);
	}

	public Condition gte(String name, Object value) {
		return new FieldConditionImpl(
			name, value, ComparisonOperator.GREATER_THAN_OR_EQUAL_TO);
	}

	public Condition in(String name, Object value) {
		return new FieldConditionImpl(name, value, ComparisonOperator.IN);
	}

	public Condition like(String name, Object value) {
		return new FieldConditionImpl(name, value, ComparisonOperator.LIKE);
	}

	public Condition lt(String name, Object value) {
		return new FieldConditionImpl(
			name, value, ComparisonOperator.LESS_THAN);
	}

	public Condition lte(String name, Object value) {
		return new FieldConditionImpl(
			name, value, ComparisonOperator.LESS_THAN_OR_EQUAL_TO);
	}

	public Condition ne(String name, Object value) {
		return new FieldConditionImpl(
			name, value, ComparisonOperator.NOT_EQUALS);
	}

	public Condition notIn(String name, Object value) {
		return new FieldConditionImpl(name, value, ComparisonOperator.NOT_IN);
	}

}