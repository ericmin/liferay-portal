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

package com.liferay.portal.kernel.workflow;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class DefaultWorkflowInstance implements Serializable, WorkflowInstance {

	public void addChildWorkflowInstance(
		WorkflowInstance childWorkflowInstance) {

		_childrenWorkflowInstances.add(childWorkflowInstance);
	}

	public int getChildrenWorkflowInstanceCount() {
		return _childrenWorkflowInstances.size();
	}

	public List<WorkflowInstance> getChildrenWorkflowInstances() {
		return _childrenWorkflowInstances;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public WorkflowInstance getParentWorkflowInstance() {
		return _parentWorkflowInstance;
	}

	public long getParentWorkflowInstanceId() {
		if (_parentWorkflowInstance != null) {
			return _parentWorkflowInstance.getWorkflowInstanceId();
		}
		else {
			return 0;
		}
	}

	public Date getStartDate() {
		return _startDate;
	}

	public String getState() {
		return _state;
	}

	public Map<String, Serializable> getWorkflowContext() {
		return _workflowContext;
	}

	public String getWorkflowDefinitionName() {
		return _workflowDefinitionName;
	}

	public int getWorkflowDefinitionVersion() {
		return _workflowDefinitionVersion;
	}

	public long getWorkflowInstanceId() {
		return _workflowInstanceId;
	}

	public boolean isComplete() {
		if (getEndDate() != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setChildrenWorkflowInstances(
		List<WorkflowInstance> childrenWorkflowInstances) {

		_childrenWorkflowInstances = childrenWorkflowInstances;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public void setParentWorkflowInstance(
		WorkflowInstance parentWorkflowInstance) {

		_parentWorkflowInstance = parentWorkflowInstance;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public void setState(String state) {
		_state = state;
	}

	public void setWorkflowContext(Map<String, Serializable> workflowContext) {
		_workflowContext = workflowContext;
	}

	public void setWorkflowDefinitionName(String workflowDefinitionName) {
		_workflowDefinitionName = workflowDefinitionName;
	}

	public void setWorkflowDefinitionVersion(int workflowDefinitionVersion) {
		_workflowDefinitionVersion = workflowDefinitionVersion;
	}

	public void setWorkflowInstanceId(long workflowInstanceId) {
		_workflowInstanceId = workflowInstanceId;
	}

	private List<WorkflowInstance> _childrenWorkflowInstances =
		new ArrayList<WorkflowInstance>();
	private Date _endDate;
	private WorkflowInstance _parentWorkflowInstance;
	private Date _startDate;
	private String _state;
	private Map<String, Serializable> _workflowContext;
	private String _workflowDefinitionName;
	private int _workflowDefinitionVersion;
	private long _workflowInstanceId;

}