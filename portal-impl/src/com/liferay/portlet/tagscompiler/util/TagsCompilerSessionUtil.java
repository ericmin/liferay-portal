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

package com.liferay.portlet.tagscompiler.util;

import com.liferay.portal.util.WebKeys;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

/**
 * @author Brian Wing Shun Chan
 */
public class TagsCompilerSessionUtil {

	public static void addEntries(
		PortletRequest portletRequest, List<String> entries) {

		Set<String> entriesSet = _getEntriesSet(portletRequest);

		entriesSet.addAll(entries);
	}

	public static void addEntry(PortletRequest portletRequest, String entry) {
		Set<String> entriesSet = _getEntriesSet(portletRequest);

		entriesSet.add(entry);
	}

	public static void clearEntries(PortletRequest portletRequest) {
		Set<String> entriesSet = _getEntriesSet(portletRequest);

		entriesSet.clear();
	}

	public static Collection<String> getEntries(PortletRequest portletRequest) {
		Set<String> entriesSet = _getEntriesSet(portletRequest);

		return entriesSet;
	}

	public static void removeEntries(
		PortletRequest portletRequest, List<String> entries) {

		Set<String> entriesSet = _getEntriesSet(portletRequest);

		entriesSet.removeAll(entries);
	}

	public static void setEntries(
		PortletRequest portletRequest, List<String> entries) {

		Set<String> entriesSet = _getEntriesSet(portletRequest);

		entriesSet.clear();

		entriesSet.addAll(entries);
	}

	private static Set<String> _getEntriesSet(PortletRequest portletRequest) {
		PortletSession portletSession = portletRequest.getPortletSession();

		Set<String> entriesSet = (Set<String>)portletSession.getAttribute(
			WebKeys.TAGS_COMPILER_ENTRIES, PortletSession.APPLICATION_SCOPE);

		if (entriesSet == null) {
			entriesSet = new TreeSet<String>();

			portletSession.setAttribute(
				WebKeys.TAGS_COMPILER_ENTRIES, entriesSet,
				PortletSession.APPLICATION_SCOPE);
		}

		return entriesSet;
	}

}