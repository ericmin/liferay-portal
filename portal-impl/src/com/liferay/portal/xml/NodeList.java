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

import com.liferay.portal.kernel.util.TranslatedList;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Branch;
import com.liferay.portal.kernel.xml.CDATA;
import com.liferay.portal.kernel.xml.Comment;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Entity;
import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.ProcessingInstruction;
import com.liferay.portal.kernel.xml.QName;
import com.liferay.portal.kernel.xml.Text;
import com.liferay.portal.kernel.xml.XPath;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class NodeList<E, F> extends TranslatedList<E, F> {

	public NodeList(List<E> newList, List<F> oldList) {
		super(newList, oldList);
	}

	@Override
	protected TranslatedList<E, F> newInstance(
		List<E> newList, List<F> oldList) {

		return new NodeList<E, F>(newList, oldList);
	}

	@Override
	protected F toOldObject(E o) {
		if (o instanceof Attribute) {
			AttributeImpl attributeImpl = (AttributeImpl)o;

			return (F)attributeImpl.getWrappedAttribute();
		}
		else if (o instanceof CDATA) {
			CDATAImpl cdataImpl = (CDATAImpl)o;

			return (F)cdataImpl.getWrappedCDATA();
		}
		else if (o instanceof Comment) {
			CommentImpl commentImpl = (CommentImpl)o;

			return (F)commentImpl.getWrappedComment();
		}
		else if (o instanceof Document) {
			DocumentImpl documentImpl = (DocumentImpl)o;

			return (F)documentImpl.getWrappedDocument();
		}
		else if (o instanceof Element) {
			ElementImpl elementImpl = (ElementImpl)o;

			return (F)elementImpl.getWrappedElement();
		}
		else if (o instanceof Entity) {
			EntityImpl entityImpl = (EntityImpl)o;

			return (F)entityImpl.getWrappedEntity();
		}
		else if (o instanceof Namespace) {
			NamespaceImpl namespaceImpl = (NamespaceImpl)o;

			return (F)namespaceImpl.getWrappedNamespace();
		}
		else if (o instanceof ProcessingInstruction) {
			ProcessingInstructionImpl processingInstructionImpl =
				(ProcessingInstructionImpl)o;

			return
				(F)processingInstructionImpl.getWrappedProcessingInstruction();
		}
		else if (o instanceof QName) {
			QNameImpl qNameImpl = (QNameImpl)o;

			return (F)qNameImpl.getWrappedQName();
		}
		else if (o instanceof Text) {
			TextImpl textImpl = (TextImpl)o;

			return (F)textImpl.getWrappedText();
		}
		else if (o instanceof XPath) {
			XPathImpl xPathImpl = (XPathImpl)o;

			return (F)xPathImpl.getWrappedXPath();
		}
		else if (o instanceof Branch) {
			BranchImpl branchImpl = (BranchImpl)o;

			return (F)branchImpl.getWrappedBranch();
		}
		else if (o instanceof Node) {
			NodeImpl nodeImpl = (NodeImpl)o;

			return (F)nodeImpl.getWrappedNode();
		}

		throw new IllegalArgumentException(o.getClass().getName());
	}

}