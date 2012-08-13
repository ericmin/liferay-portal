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

package com.liferay.portal.xml;

import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.QName;

/**
 * @author Brian Wing Shun Chan
 */
public class QNameImpl implements QName {

	public QNameImpl(org.dom4j.QName qName) {
		_qName = qName;
	}

	@Override
	public boolean equals(Object obj) {
		org.dom4j.QName qName = ((QNameImpl)obj).getWrappedQName();

		return _qName.equals(qName);
	}

	public String getLocalPart() {
		return getName();
	}

	public String getName() {
		return _qName.getName();
	}

	public Namespace getNamespace() {
		org.dom4j.Namespace namespace = _qName.getNamespace();

		if (namespace == null) {
			return null;
		}
		else {
			return new NamespaceImpl(namespace);
		}
	}

	public String getNamespacePrefix() {
		return _qName.getNamespacePrefix();
	}

	public String getNamespaceURI() {
		return _qName.getNamespaceURI();
	}

	public String getQualifiedName() {
		return _qName.getQualifiedName();
	}

	public org.dom4j.QName getWrappedQName() {
		return _qName;
	}

	@Override
	public int hashCode() {
		return _qName.hashCode();
	}

	@Override
	public String toString() {
		return _qName.toString();
	}

	private org.dom4j.QName _qName;

}