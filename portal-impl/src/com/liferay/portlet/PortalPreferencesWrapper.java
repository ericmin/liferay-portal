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

import java.io.IOException;

import java.util.Enumeration;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;

/**
 * @author Alexander Chow
 */
public class PortalPreferencesWrapper implements PortletPreferences {

	public PortalPreferencesWrapper(
		PortalPreferencesImpl portalPreferencesImpl) {

		_portalPreferencesImpl = portalPreferencesImpl;
	}

	public Map<String, String[]> getMap() {
		return _portalPreferencesImpl.getMap();
	}

	public Enumeration<String> getNames() {
		return _portalPreferencesImpl.getNames();
	}

	public PortalPreferencesImpl getPortalPreferencesImpl() {
		return _portalPreferencesImpl;
	}

	public String getValue(String key, String def) {
		return _portalPreferencesImpl.getValue(null, key, def);
	}

	public String[] getValues(String key, String[] def) {
		return _portalPreferencesImpl.getValues(null, key, def);
	}

	public boolean isReadOnly(String key) {
		return _portalPreferencesImpl.isReadOnly(key);
	}

	public void reset(String key) throws ReadOnlyException {
		_portalPreferencesImpl.reset(key);
	}

	public void setValue(String key, String value) throws ReadOnlyException {
		_portalPreferencesImpl.setValue(key, value);
	}

	public void setValues(String key, String[] values)
		throws ReadOnlyException {

		_portalPreferencesImpl.setValues(key, values);
	}

	public void store() throws IOException {
		_portalPreferencesImpl.store();
	}

	private PortalPreferencesImpl _portalPreferencesImpl;

}