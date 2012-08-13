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

package com.liferay.util.xml.descriptor;

import com.liferay.util.xml.ElementIdentifier;

import org.dom4j.Document;

/**
 * @author Jorge Ferrer
 */
public class FacesXMLDescriptor extends SimpleXMLDescriptor {

	@Override
	public boolean canHandleType(String doctype, Document root) {
		if (doctype.indexOf("faces-config") != -1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public ElementIdentifier[] getElementsIdentifiedByAttribute() {
		return _ELEMENTS_IDENTIFIED_BY_ATTR;
	}

	@Override
	public ElementIdentifier[] getElementsIdentifiedByChild() {
		return _ELEMENTS_IDENTIFIED_BY_CHILD;
	}

	@Override
	public String[] getJoinableElements() {
		return _JOINABLE_ELEMENTS;
	}

	@Override
	public String[] getRootChildrenOrder() {
		return _ROOT_ORDERED_CHILDREN;
	}

	@Override
	public String[] getUniqueElements() {
		return _UNIQUE_ELEMENTS;
	}

	private static final ElementIdentifier[] _ELEMENTS_IDENTIFIED_BY_ATTR = {
	};

	private static final ElementIdentifier[] _ELEMENTS_IDENTIFIED_BY_CHILD = {
	};

	private static final String[] _JOINABLE_ELEMENTS = {
	};

	private static final String[] _ROOT_ORDERED_CHILDREN = {
		"application", "factory", "component", "converter", "managed-bean",
		"navigation-rule", "referenced-bean", "render-kit", "lifecycle",
		"validator"
	};

	private static final String[] _UNIQUE_ELEMENTS = {
	};

}