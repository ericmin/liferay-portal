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
import com.liferay.portal.kernel.workflow.WorkflowInstance;

import java.util.Date;

/**
 * @author Shuyang Zhou
 */
public abstract class BaseWorkflowInstanceStartDateComparator
	extends OrderByComparator {

	public BaseWorkflowInstanceStartDateComparator() {
		this(false);
	}

	public BaseWorkflowInstanceStartDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		WorkflowInstance workflowInstance1 = (WorkflowInstance)obj1;
		WorkflowInstance workflowInstance2 = (WorkflowInstance)obj2;

		Date startDate1 = workflowInstance1.getStartDate();
		Date startDate2 = workflowInstance2.getStartDate();

		int value = startDate1.compareTo(startDate2);

		if (value == 0) {
			Long workflowInstanceId1 =
				workflowInstance1.getWorkflowInstanceId();
			Long workflowInstanceId2 =
				workflowInstance2.getWorkflowInstanceId();

			value = workflowInstanceId1.compareTo(workflowInstanceId2);
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