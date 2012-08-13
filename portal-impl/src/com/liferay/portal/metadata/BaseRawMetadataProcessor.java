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

package com.liferay.portal.metadata;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.metadata.RawMetadataProcessor;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.io.File;
import java.io.InputStream;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tika.metadata.ClimateForcast;
import org.apache.tika.metadata.CreativeCommons;
import org.apache.tika.metadata.DublinCore;
import org.apache.tika.metadata.Geographic;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.MSOffice;
import org.apache.tika.metadata.Message;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.Property;
import org.apache.tika.metadata.TIFF;
import org.apache.tika.metadata.TikaMetadataKeys;
import org.apache.tika.metadata.TikaMimeKeys;

/**
 * @author Alexander Chow
 */
public abstract class BaseRawMetadataProcessor implements RawMetadataProcessor {

	public Map<String, Field[]> getFields() {
		return _fields;
	}

	public Map<String, Fields> getRawMetadataMap(
			String extension, String mimeType, File file)
		throws PortalException, SystemException {

		Metadata metadata = extractMetadata(extension, mimeType, file);

		return createDDMFieldsMap(metadata, getFields());
	}

	public Map<String, Fields> getRawMetadataMap(
			String extension, String mimeType, InputStream inputStream)
		throws PortalException, SystemException {

		Metadata metadata = extractMetadata(extension, mimeType, inputStream);

		return createDDMFieldsMap(metadata, getFields());
	}

	protected Fields createDDMFields(Metadata metadata, Field[] fields) {
		Fields ddmFields = new Fields();

		for (Field field : fields) {
			Class<?> fieldClass = field.getDeclaringClass();

			String fieldClassName = fieldClass.getSimpleName();

			String name = fieldClassName.concat(
				StringPool.UNDERLINE).concat(field.getName());

			String value = getMetadataValue(metadata, field);

			if (value == null) {
				continue;
			}

			com.liferay.portlet.dynamicdatamapping.storage.Field ddmField =
				new com.liferay.portlet.dynamicdatamapping.storage.Field(
					name, value);

			ddmFields.put(ddmField);
		}

		return ddmFields;
	}

	protected Map<String, Fields> createDDMFieldsMap(
		Metadata metadata, Map<String, Field[]> fieldsMap) {

		Map<String, Fields> ddmFieldsMap = new HashMap<String, Fields>();

		if (metadata == null) {
			return ddmFieldsMap;
		}

		for (String key : fieldsMap.keySet()) {
			Field[] fields = fieldsMap.get(key);

			Fields ddmFields = createDDMFields(metadata, fields);

			Set<String> names = ddmFields.getNames();

			if (names.isEmpty()) {
				continue;
			}

			ddmFieldsMap.put(key, ddmFields);
		}

		return ddmFieldsMap;
	}

	protected abstract Metadata extractMetadata(
			String extension, String mimeType, File file)
		throws PortalException, SystemException;

	protected abstract Metadata extractMetadata(
			String extension, String mimeType, InputStream inputStream)
		throws PortalException, SystemException;

	protected Object getFieldValue(Metadata metadata, Field field) {
		Object fieldValue = null;

		try {
			fieldValue = field.get(metadata);
		}
		catch (IllegalAccessException iae) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"The property " + field.getName() +
						" will not be added to the metatada set");
			}
		}

		return fieldValue;
	}

	protected String getMetadataValue(Metadata metadata, Field field) {
		Object fieldValue = getFieldValue(metadata, field);

		if (fieldValue instanceof String) {
			return metadata.get((String)fieldValue);
		}
		else {
			Property property = (Property)fieldValue;

			return metadata.get(property.getName());
		}
	}

	private static void _addFields(Class<?> clazz, List<Field> fields) {
		for (Field field : clazz.getFields()) {
			fields.add(field);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		BaseRawMetadataProcessor.class);

	private static Map<String, Field[]> _fields =
		new HashMap<String, Field[]>();

	static {
		List<Field> fields = new ArrayList<Field>();

		_addFields(ClimateForcast.class, fields);
		_addFields(CreativeCommons.class, fields);
		_addFields(DublinCore.class, fields);
		_addFields(Geographic.class, fields);
		_addFields(HttpHeaders.class, fields);
		_addFields(Message.class, fields);
		_addFields(MSOffice.class, fields);
		_addFields(TIFF.class, fields);
		_addFields(TikaMetadataKeys.class, fields);
		_addFields(TikaMimeKeys.class, fields);
		_addFields(XMPDM.class, fields);

		_fields.put(
			"TikaRawMetadata", fields.toArray(new Field[fields.size()]));
	}

}