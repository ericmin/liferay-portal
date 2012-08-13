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

package com.liferay.portlet.wiki.engines.jspwiki;

import com.ecyrd.jspwiki.WikiEngine;
import com.ecyrd.jspwiki.auth.authorize.Group;
import com.ecyrd.jspwiki.auth.authorize.GroupDatabase;

import java.security.Principal;

import java.util.Properties;

/**
 * @author Jorge Ferrer
 */
public class LiferayGroupDatabase implements GroupDatabase {

	public void commit() {
	}

	public void delete(Group group) {
	}

	public Group[] groups() {
		return new Group[0];
	}

	public void initialize(WikiEngine engine, Properties props) {
	}

	public void save(Group group, Principal modifier) {
	}

}