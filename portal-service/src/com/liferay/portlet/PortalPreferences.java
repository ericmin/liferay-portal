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

package com.liferay.portlet;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public interface PortalPreferences extends Serializable {

	public String getValue(String namespace, String key);

	public String getValue(String namespace, String key, String defaultValue);

	public String[] getValues(String namespace, String key);

	public String[] getValues(
		String namespace, String key, String[] defaultValue);

	public boolean isSignedIn();

	public void resetValues(String namespace);

	public void setSignedIn(boolean signedIn);

	public void setValue(String namespace, String key, String value);

	public void setValues(String namespace, String key, String[] values);

	public int size();

}