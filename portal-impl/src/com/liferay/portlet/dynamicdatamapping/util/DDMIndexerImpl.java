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

package com.liferay.portlet.dynamicdatamapping.util;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.io.Serializable;

import java.util.Date;
import java.util.Iterator;

/**
 * @author Alexander Chow
 */
public class DDMIndexerImpl implements DDMIndexer {

	public void addAttributes(
		Document document, DDMStructure ddmStructure, Fields fields) {

		Iterator<Field> itr = fields.iterator();

		while (itr.hasNext()) {
			Field field = itr.next();

			String name = encodeName(
				ddmStructure.getStructureId(), field.getName());

			Serializable value = field.getValue();

			if (value instanceof Boolean) {
				document.addKeyword(name, (Boolean)value);
			}
			else if (value instanceof Date) {
				document.addDate(name, (Date)value);
			}
			else if (value instanceof Double) {
				document.addKeyword(name, (Double)value);
			}
			else if (value instanceof Integer) {
				document.addKeyword(name, (Integer)value);
			}
			else {
				String valueString = String.valueOf(value);

				document.addText(name, valueString);
			}
		}
	}

	public String encodeName(long ddmStructureId, String fieldName) {
		StringBundler sb = new StringBundler(5);

		sb.append(_FIELD_NAMESPACE);
		sb.append(StringPool.FORWARD_SLASH);
		sb.append(ddmStructureId);
		sb.append(StringPool.FORWARD_SLASH);
		sb.append(fieldName);

		return sb.toString();
	}

	private static final String _FIELD_NAMESPACE = "ddm";

}