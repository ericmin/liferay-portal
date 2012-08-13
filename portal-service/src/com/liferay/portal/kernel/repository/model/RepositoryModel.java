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

package com.liferay.portal.kernel.repository.model;

import java.io.Serializable;
import java.util.Map;

import com.liferay.portal.model.ClassedModel;
import com.liferay.portal.model.GroupedModel;

/**
 * @author Alexander Chow
 */
public interface RepositoryModel<T>
	extends GroupedModel, ClassedModel, Serializable {

	public Map<String, Serializable> getAttributes();

	public Object getModel();

	public long getPrimaryKey();

	public boolean isEscapedModel();

	public T toEscapedModel();

}