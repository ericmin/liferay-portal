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

package com.liferay.portlet.dynamicdatamapping.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the DDMStructure service. Represents a row in the &quot;DDMStructure&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DDMStructureModel
 * @see com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureImpl
 * @see com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureModelImpl
 * @generated
 */
public interface DDMStructure extends DDMStructureModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.util.List<java.lang.String> getAvailableLocales();

	public java.lang.String getDefaultLocale();

	public com.liferay.portal.kernel.xml.Document getDocument();

	public java.lang.String getFieldDataType(java.lang.String fieldName)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException;

	public java.lang.String getFieldLabel(java.lang.String fieldName,
		java.util.Locale locale)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException;

	public java.lang.String getFieldLabel(java.lang.String fieldName,
		java.lang.String locale)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException;

	public java.util.Set<java.lang.String> getFieldNames();

	public java.lang.String getFieldProperty(java.lang.String fieldName,
		java.lang.String property)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException;

	public java.lang.String getFieldProperty(java.lang.String fieldName,
		java.lang.String property, java.lang.String locale)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException;

	public boolean getFieldRequired(java.lang.String fieldName)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException;

	public java.util.Map<java.lang.String, java.lang.String> getFields(
		java.lang.String fieldName, java.lang.String attributeName,
		java.lang.String attributeValue);

	public java.util.Map<java.lang.String, java.lang.String> getFields(
		java.lang.String fieldName, java.lang.String attributeName,
		java.lang.String attributeValue, java.lang.String locale);

	public java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> getFieldsMap();

	public java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> getFieldsMap(
		java.lang.String locale);

	public java.lang.String getFieldType(java.lang.String fieldName)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException;

	public java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> getTemplates()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean hasField(java.lang.String fieldName);

	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException;

	public void setDocument(com.liferay.portal.kernel.xml.Document document);

	public void setLocalizedFieldsMap(
		java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>>> localizedFieldsMap);

	public void setXsd(java.lang.String xsd);
}