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

import com.liferay.util.xml.ElementComparator;
import com.liferay.util.xml.ElementIdentifier;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * @author Jorge Ferrer
 */
public abstract class SimpleXMLDescriptor implements XMLDescriptor {

	public boolean areEqual(Element el1, Element el2) {
		String name1 = el1.getName();
		String name2 = el2.getName();

		if ((name1 == null) || !name1.equals(name2)) {
			return false;
		}

		if (_isIncluded(el1, getUniqueElements())) {
			return true;
		}

		ElementIdentifier[] elIds = getElementsIdentifiedByAttribute();
		for (int i = 0; i < elIds.length; i++) {
			if (name1.equals(elIds[i].getElementName())) {
				if (_compareAttribute(
						el1, el2, elIds[i].getIdentifierName()) == 0) {

					return true;
				}
				else {
					return false;
				}
			}
		}

		elIds = getElementsIdentifiedByChild();
		for (int i = 0; i < elIds.length; i++) {
			if (name1.equals(elIds[i].getElementName())) {
				if (_compareChildText(
						el1, el2, elIds[i].getIdentifierName()) == 0) {
					return true;
				}
				else {
					return false;
				}
			}
		}

		ElementComparator comparator = new ElementComparator();

		if (comparator.compare(el1, el2) == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public abstract boolean canHandleType(String doctype, Document root);

	public boolean canJoinChildren(Element element) {
		return _isIncluded(element, getJoinableElements());
	}

	public String[] getChildrenOrder(Element parentElement) {
		return new String[0];
	}

	public ElementIdentifier[] getElementsIdentifiedByAttribute() {
		return new ElementIdentifier[0];
	}

	public ElementIdentifier[] getElementsIdentifiedByChild() {
		return new ElementIdentifier[0];
	}

	public String[] getJoinableElements() {
		return new String[0];
	}

	public String[] getRootChildrenOrder() {
		return new String[0];
	}

	public String[] getUniqueElements() {
		return new String[0];
	}

	private int _compareAttribute(Element el1, Element el2, String attrName) {
		String name1 = el1.attributeValue(attrName);
		String name2 = el2.attributeValue(attrName);

		if ((name1 == null) || (name2 == null)) {
			return -1;
		}

		return name1.compareTo(name2);
	}

	private int _compareChildText(Element el1, Element el2, String childName) {
		Element child1 = _getChild(el1, childName);
		Element child2 = _getChild(el2, childName);

		if ((child1 == null) || (child2 == null)) {
			return -1;
		}

		String name1 = child1.getText();
		String name2 = child2.getText();

		if ((name1 == null) || (name2 == null)) {
			return -1;
		}

		return name1.compareTo(name2);
	}

	private Element _getChild(Element parent, String childName) {
		Element child = parent.element(childName);

		/*if (child == null) {
			child = parent.element(childName, parent.getNamespace());
		}*/

		return child;
	}

	private boolean _isIncluded(Element element, String[] elemNames) {
		for (int i = 0; i < elemNames.length; i++) {
			if (element.getName().equals(elemNames[i])) {
				return true;
			}
		}

		return false;
	}

}