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

package com.liferay.portal.kernel.workflow.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;

/**
 * @author Shuyang Zhou
 */
public abstract class BaseWorkflowDefinitionNameComparator
	extends OrderByComparator {

	public BaseWorkflowDefinitionNameComparator() {
		this(false);
	}

	public BaseWorkflowDefinitionNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		WorkflowDefinition workflowDefinition1 = (WorkflowDefinition)obj1;
		WorkflowDefinition workflowDefinition2 = (WorkflowDefinition)obj2;

		String name1 = workflowDefinition1.getName();
		String name2 = workflowDefinition2.getName();

		int value = name1.compareTo(name2);

		if (value == 0) {
			Integer version1 = workflowDefinition1.getVersion();
			Integer version2 = workflowDefinition2.getVersion();

			value = version1.compareTo(version2);
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}