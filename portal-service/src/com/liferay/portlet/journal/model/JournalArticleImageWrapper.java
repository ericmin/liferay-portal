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

package com.liferay.portlet.journal.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link JournalArticleImage}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JournalArticleImage
 * @generated
 */
public class JournalArticleImageWrapper implements JournalArticleImage,
	ModelWrapper<JournalArticleImage> {
	public JournalArticleImageWrapper(JournalArticleImage journalArticleImage) {
		_journalArticleImage = journalArticleImage;
	}

	public Class<?> getModelClass() {
		return JournalArticleImage.class;
	}

	public String getModelClassName() {
		return JournalArticleImage.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("articleImageId", getArticleImageId());
		attributes.put("groupId", getGroupId());
		attributes.put("articleId", getArticleId());
		attributes.put("version", getVersion());
		attributes.put("elInstanceId", getElInstanceId());
		attributes.put("elName", getElName());
		attributes.put("languageId", getLanguageId());
		attributes.put("tempImage", getTempImage());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long articleImageId = (Long)attributes.get("articleImageId");

		if (articleImageId != null) {
			setArticleImageId(articleImageId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String articleId = (String)attributes.get("articleId");

		if (articleId != null) {
			setArticleId(articleId);
		}

		Double version = (Double)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String elInstanceId = (String)attributes.get("elInstanceId");

		if (elInstanceId != null) {
			setElInstanceId(elInstanceId);
		}

		String elName = (String)attributes.get("elName");

		if (elName != null) {
			setElName(elName);
		}

		String languageId = (String)attributes.get("languageId");

		if (languageId != null) {
			setLanguageId(languageId);
		}

		Boolean tempImage = (Boolean)attributes.get("tempImage");

		if (tempImage != null) {
			setTempImage(tempImage);
		}
	}

	/**
	* Returns the primary key of this journal article image.
	*
	* @return the primary key of this journal article image
	*/
	public long getPrimaryKey() {
		return _journalArticleImage.getPrimaryKey();
	}

	/**
	* Sets the primary key of this journal article image.
	*
	* @param primaryKey the primary key of this journal article image
	*/
	public void setPrimaryKey(long primaryKey) {
		_journalArticleImage.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the article image ID of this journal article image.
	*
	* @return the article image ID of this journal article image
	*/
	public long getArticleImageId() {
		return _journalArticleImage.getArticleImageId();
	}

	/**
	* Sets the article image ID of this journal article image.
	*
	* @param articleImageId the article image ID of this journal article image
	*/
	public void setArticleImageId(long articleImageId) {
		_journalArticleImage.setArticleImageId(articleImageId);
	}

	/**
	* Returns the group ID of this journal article image.
	*
	* @return the group ID of this journal article image
	*/
	public long getGroupId() {
		return _journalArticleImage.getGroupId();
	}

	/**
	* Sets the group ID of this journal article image.
	*
	* @param groupId the group ID of this journal article image
	*/
	public void setGroupId(long groupId) {
		_journalArticleImage.setGroupId(groupId);
	}

	/**
	* Returns the article ID of this journal article image.
	*
	* @return the article ID of this journal article image
	*/
	public java.lang.String getArticleId() {
		return _journalArticleImage.getArticleId();
	}

	/**
	* Sets the article ID of this journal article image.
	*
	* @param articleId the article ID of this journal article image
	*/
	public void setArticleId(java.lang.String articleId) {
		_journalArticleImage.setArticleId(articleId);
	}

	/**
	* Returns the version of this journal article image.
	*
	* @return the version of this journal article image
	*/
	public double getVersion() {
		return _journalArticleImage.getVersion();
	}

	/**
	* Sets the version of this journal article image.
	*
	* @param version the version of this journal article image
	*/
	public void setVersion(double version) {
		_journalArticleImage.setVersion(version);
	}

	/**
	* Returns the el instance ID of this journal article image.
	*
	* @return the el instance ID of this journal article image
	*/
	public java.lang.String getElInstanceId() {
		return _journalArticleImage.getElInstanceId();
	}

	/**
	* Sets the el instance ID of this journal article image.
	*
	* @param elInstanceId the el instance ID of this journal article image
	*/
	public void setElInstanceId(java.lang.String elInstanceId) {
		_journalArticleImage.setElInstanceId(elInstanceId);
	}

	/**
	* Returns the el name of this journal article image.
	*
	* @return the el name of this journal article image
	*/
	public java.lang.String getElName() {
		return _journalArticleImage.getElName();
	}

	/**
	* Sets the el name of this journal article image.
	*
	* @param elName the el name of this journal article image
	*/
	public void setElName(java.lang.String elName) {
		_journalArticleImage.setElName(elName);
	}

	/**
	* Returns the language ID of this journal article image.
	*
	* @return the language ID of this journal article image
	*/
	public java.lang.String getLanguageId() {
		return _journalArticleImage.getLanguageId();
	}

	/**
	* Sets the language ID of this journal article image.
	*
	* @param languageId the language ID of this journal article image
	*/
	public void setLanguageId(java.lang.String languageId) {
		_journalArticleImage.setLanguageId(languageId);
	}

	/**
	* Returns the temp image of this journal article image.
	*
	* @return the temp image of this journal article image
	*/
	public boolean getTempImage() {
		return _journalArticleImage.getTempImage();
	}

	/**
	* Returns <code>true</code> if this journal article image is temp image.
	*
	* @return <code>true</code> if this journal article image is temp image; <code>false</code> otherwise
	*/
	public boolean isTempImage() {
		return _journalArticleImage.isTempImage();
	}

	/**
	* Sets whether this journal article image is temp image.
	*
	* @param tempImage the temp image of this journal article image
	*/
	public void setTempImage(boolean tempImage) {
		_journalArticleImage.setTempImage(tempImage);
	}

	public boolean isNew() {
		return _journalArticleImage.isNew();
	}

	public void setNew(boolean n) {
		_journalArticleImage.setNew(n);
	}

	public boolean isCachedModel() {
		return _journalArticleImage.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_journalArticleImage.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _journalArticleImage.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _journalArticleImage.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_journalArticleImage.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _journalArticleImage.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_journalArticleImage.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new JournalArticleImageWrapper((JournalArticleImage)_journalArticleImage.clone());
	}

	public int compareTo(
		com.liferay.portlet.journal.model.JournalArticleImage journalArticleImage) {
		return _journalArticleImage.compareTo(journalArticleImage);
	}

	@Override
	public int hashCode() {
		return _journalArticleImage.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.journal.model.JournalArticleImage> toCacheModel() {
		return _journalArticleImage.toCacheModel();
	}

	public com.liferay.portlet.journal.model.JournalArticleImage toEscapedModel() {
		return new JournalArticleImageWrapper(_journalArticleImage.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _journalArticleImage.toString();
	}

	public java.lang.String toXmlString() {
		return _journalArticleImage.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_journalArticleImage.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public JournalArticleImage getWrappedJournalArticleImage() {
		return _journalArticleImage;
	}

	public JournalArticleImage getWrappedModel() {
		return _journalArticleImage;
	}

	public void resetOriginalValues() {
		_journalArticleImage.resetOriginalValues();
	}

	private JournalArticleImage _journalArticleImage;
}