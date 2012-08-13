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

import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.QName;
import com.liferay.portal.kernel.xml.Visitor;

/**
 * @author Brian Wing Shun Chan
 */
public class AttributeImpl extends NodeImpl implements Attribute {

	public AttributeImpl(org.dom4j.Attribute attribute) {
		super(attribute);

		_attribute = attribute;
	}

	@Override
	public <T, V extends Visitor<T>> T accept(V visitor) {
		return visitor.visitAttribute(this);
	}

	@Override
	public boolean equals(Object obj) {
		org.dom4j.Attribute attribute =
			((AttributeImpl)obj).getWrappedAttribute();

		return _attribute.equals(attribute);
	}

	public Object getData() {
		return _attribute.getData();
	}

	public Namespace getNamespace() {
		org.dom4j.Namespace namespace = _attribute.getNamespace();

		if (namespace == null) {
			return null;
		}
		else {
			return new NamespaceImpl(namespace);
		}
	}

	public String getNamespacePrefix() {
		return _attribute.getNamespacePrefix();
	}

	public String getNamespaceURI() {
		return _attribute.getNamespaceURI();
	}

	public QName getQName() {
		org.dom4j.QName qName = _attribute.getQName();

		if (qName == null) {
			return null;
		}
		else {
			return new QNameImpl(qName);
		}
	}

	public String getQualifiedName() {
		return _attribute.getQualifiedName();
	}

	public String getValue() {
		return _attribute.getValue();
	}

	public org.dom4j.Attribute getWrappedAttribute() {
		return _attribute;
	}

	@Override
	public int hashCode() {
		return _attribute.hashCode();
	}

	public void setData(Object data) {
		_attribute.setData(data);
	}

	public void setNamespace(Namespace namespace) {
		NamespaceImpl namespaceImpl = (NamespaceImpl)namespace;

		_attribute.setNamespace(namespaceImpl.getWrappedNamespace());
	}

	public void setValue(String value) {
		_attribute.setValue(value);
	}

	@Override
	public String toString() {
		return _attribute.toString();
	}

	private org.dom4j.Attribute _attribute;

}