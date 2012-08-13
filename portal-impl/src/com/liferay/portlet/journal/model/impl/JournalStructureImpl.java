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

package com.liferay.portlet.journal.model.impl;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.service.JournalStructureLocalServiceUtil;

import java.util.Iterator;

/**
 * @author Brian Wing Shun Chan
 */
public class JournalStructureImpl extends JournalStructureBaseImpl {

	public JournalStructureImpl() {
	}

	public String getMergedXsd() {
		String parentStructureId = getParentStructureId();

		String xsd = getXsd();

		if (Validator.isNull(parentStructureId)) {
			return xsd;
		}

		try {
			JournalStructure parentStructure =
				JournalStructureLocalServiceUtil.getStructure(
					getGroupId(), parentStructureId, true);

			Document doc = SAXReaderUtil.read(getXsd());

			Element root = doc.getRootElement();

			Document parentDoc = SAXReaderUtil.read(
				parentStructure.getMergedXsd());

			Element parentRoot = parentDoc.getRootElement();

			addParentStructureId(parentRoot, parentStructureId);

			root.content().addAll(0, parentRoot.content());

			xsd = root.asXML();
		}
		catch (Exception e) {
		}

		return xsd;
	}

	protected void addParentStructureId(
		Element parentEl, String parentStructureId) {

		Iterator<Element> itr = parentEl.elements(_DYNAMIC_ELEMENT).iterator();

		while (itr.hasNext()) {
			Element dynamicEl = itr.next();

			dynamicEl.addAttribute(_PARENT_STRUCTURE_ID, parentStructureId);

			addParentStructureId(dynamicEl, parentStructureId);
		}
	}

	private static final String _DYNAMIC_ELEMENT = "dynamic-element";

	private static final String _PARENT_STRUCTURE_ID = "parent-structure-id";

}