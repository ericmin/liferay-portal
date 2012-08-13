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

package com.liferay.portal.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the User service. Represents a row in the &quot;User_&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.UserModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.UserImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see User
 * @see com.liferay.portal.model.impl.UserImpl
 * @see com.liferay.portal.model.impl.UserModelImpl
 * @generated
 */
public interface UserModel extends BaseModel<User> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a user model instance should use the {@link User} interface instead.
	 */

	/**
	 * Returns the primary key of this user.
	 *
	 * @return the primary key of this user
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this user.
	 *
	 * @param primaryKey the primary key of this user
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this user.
	 *
	 * @return the uuid of this user
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this user.
	 *
	 * @param uuid the uuid of this user
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the user ID of this user.
	 *
	 * @return the user ID of this user
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this user.
	 *
	 * @param userId the user ID of this user
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this user.
	 *
	 * @return the user uuid of this user
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this user.
	 *
	 * @param userUuid the user uuid of this user
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the company ID of this user.
	 *
	 * @return the company ID of this user
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this user.
	 *
	 * @param companyId the company ID of this user
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this user.
	 *
	 * @return the create date of this user
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this user.
	 *
	 * @param createDate the create date of this user
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this user.
	 *
	 * @return the modified date of this user
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this user.
	 *
	 * @param modifiedDate the modified date of this user
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the default user of this user.
	 *
	 * @return the default user of this user
	 */
	public boolean getDefaultUser();

	/**
	 * Returns <code>true</code> if this user is default user.
	 *
	 * @return <code>true</code> if this user is default user; <code>false</code> otherwise
	 */
	public boolean isDefaultUser();

	/**
	 * Sets whether this user is default user.
	 *
	 * @param defaultUser the default user of this user
	 */
	public void setDefaultUser(boolean defaultUser);

	/**
	 * Returns the contact ID of this user.
	 *
	 * @return the contact ID of this user
	 */
	public long getContactId();

	/**
	 * Sets the contact ID of this user.
	 *
	 * @param contactId the contact ID of this user
	 */
	public void setContactId(long contactId);

	/**
	 * Returns the password of this user.
	 *
	 * @return the password of this user
	 */
	@AutoEscape
	public String getPassword();

	/**
	 * Sets the password of this user.
	 *
	 * @param password the password of this user
	 */
	public void setPassword(String password);

	/**
	 * Returns the password encrypted of this user.
	 *
	 * @return the password encrypted of this user
	 */
	public boolean getPasswordEncrypted();

	/**
	 * Returns <code>true</code> if this user is password encrypted.
	 *
	 * @return <code>true</code> if this user is password encrypted; <code>false</code> otherwise
	 */
	public boolean isPasswordEncrypted();

	/**
	 * Sets whether this user is password encrypted.
	 *
	 * @param passwordEncrypted the password encrypted of this user
	 */
	public void setPasswordEncrypted(boolean passwordEncrypted);

	/**
	 * Returns the password reset of this user.
	 *
	 * @return the password reset of this user
	 */
	public boolean getPasswordReset();

	/**
	 * Returns <code>true</code> if this user is password reset.
	 *
	 * @return <code>true</code> if this user is password reset; <code>false</code> otherwise
	 */
	public boolean isPasswordReset();

	/**
	 * Sets whether this user is password reset.
	 *
	 * @param passwordReset the password reset of this user
	 */
	public void setPasswordReset(boolean passwordReset);

	/**
	 * Returns the password modified date of this user.
	 *
	 * @return the password modified date of this user
	 */
	public Date getPasswordModifiedDate();

	/**
	 * Sets the password modified date of this user.
	 *
	 * @param passwordModifiedDate the password modified date of this user
	 */
	public void setPasswordModifiedDate(Date passwordModifiedDate);

	/**
	 * Returns the digest of this user.
	 *
	 * @return the digest of this user
	 */
	@AutoEscape
	public String getDigest();

	/**
	 * Sets the digest of this user.
	 *
	 * @param digest the digest of this user
	 */
	public void setDigest(String digest);

	/**
	 * Returns the reminder query question of this user.
	 *
	 * @return the reminder query question of this user
	 */
	@AutoEscape
	public String getReminderQueryQuestion();

	/**
	 * Sets the reminder query question of this user.
	 *
	 * @param reminderQueryQuestion the reminder query question of this user
	 */
	public void setReminderQueryQuestion(String reminderQueryQuestion);

	/**
	 * Returns the reminder query answer of this user.
	 *
	 * @return the reminder query answer of this user
	 */
	@AutoEscape
	public String getReminderQueryAnswer();

	/**
	 * Sets the reminder query answer of this user.
	 *
	 * @param reminderQueryAnswer the reminder query answer of this user
	 */
	public void setReminderQueryAnswer(String reminderQueryAnswer);

	/**
	 * Returns the grace login count of this user.
	 *
	 * @return the grace login count of this user
	 */
	public int getGraceLoginCount();

	/**
	 * Sets the grace login count of this user.
	 *
	 * @param graceLoginCount the grace login count of this user
	 */
	public void setGraceLoginCount(int graceLoginCount);

	/**
	 * Returns the screen name of this user.
	 *
	 * @return the screen name of this user
	 */
	@AutoEscape
	public String getScreenName();

	/**
	 * Sets the screen name of this user.
	 *
	 * @param screenName the screen name of this user
	 */
	public void setScreenName(String screenName);

	/**
	 * Returns the email address of this user.
	 *
	 * @return the email address of this user
	 */
	@AutoEscape
	public String getEmailAddress();

	/**
	 * Sets the email address of this user.
	 *
	 * @param emailAddress the email address of this user
	 */
	public void setEmailAddress(String emailAddress);

	/**
	 * Returns the facebook ID of this user.
	 *
	 * @return the facebook ID of this user
	 */
	public long getFacebookId();

	/**
	 * Sets the facebook ID of this user.
	 *
	 * @param facebookId the facebook ID of this user
	 */
	public void setFacebookId(long facebookId);

	/**
	 * Returns the open ID of this user.
	 *
	 * @return the open ID of this user
	 */
	@AutoEscape
	public String getOpenId();

	/**
	 * Sets the open ID of this user.
	 *
	 * @param openId the open ID of this user
	 */
	public void setOpenId(String openId);

	/**
	 * Returns the portrait ID of this user.
	 *
	 * @return the portrait ID of this user
	 */
	public long getPortraitId();

	/**
	 * Sets the portrait ID of this user.
	 *
	 * @param portraitId the portrait ID of this user
	 */
	public void setPortraitId(long portraitId);

	/**
	 * Returns the language ID of this user.
	 *
	 * @return the language ID of this user
	 */
	@AutoEscape
	public String getLanguageId();

	/**
	 * Sets the language ID of this user.
	 *
	 * @param languageId the language ID of this user
	 */
	public void setLanguageId(String languageId);

	/**
	 * Returns the time zone ID of this user.
	 *
	 * @return the time zone ID of this user
	 */
	@AutoEscape
	public String getTimeZoneId();

	/**
	 * Sets the time zone ID of this user.
	 *
	 * @param timeZoneId the time zone ID of this user
	 */
	public void setTimeZoneId(String timeZoneId);

	/**
	 * Returns the greeting of this user.
	 *
	 * @return the greeting of this user
	 */
	@AutoEscape
	public String getGreeting();

	/**
	 * Sets the greeting of this user.
	 *
	 * @param greeting the greeting of this user
	 */
	public void setGreeting(String greeting);

	/**
	 * Returns the comments of this user.
	 *
	 * @return the comments of this user
	 */
	@AutoEscape
	public String getComments();

	/**
	 * Sets the comments of this user.
	 *
	 * @param comments the comments of this user
	 */
	public void setComments(String comments);

	/**
	 * Returns the first name of this user.
	 *
	 * @return the first name of this user
	 */
	@AutoEscape
	public String getFirstName();

	/**
	 * Sets the first name of this user.
	 *
	 * @param firstName the first name of this user
	 */
	public void setFirstName(String firstName);

	/**
	 * Returns the middle name of this user.
	 *
	 * @return the middle name of this user
	 */
	@AutoEscape
	public String getMiddleName();

	/**
	 * Sets the middle name of this user.
	 *
	 * @param middleName the middle name of this user
	 */
	public void setMiddleName(String middleName);

	/**
	 * Returns the last name of this user.
	 *
	 * @return the last name of this user
	 */
	@AutoEscape
	public String getLastName();

	/**
	 * Sets the last name of this user.
	 *
	 * @param lastName the last name of this user
	 */
	public void setLastName(String lastName);

	/**
	 * Returns the job title of this user.
	 *
	 * @return the job title of this user
	 */
	@AutoEscape
	public String getJobTitle();

	/**
	 * Sets the job title of this user.
	 *
	 * @param jobTitle the job title of this user
	 */
	public void setJobTitle(String jobTitle);

	/**
	 * Returns the login date of this user.
	 *
	 * @return the login date of this user
	 */
	public Date getLoginDate();

	/**
	 * Sets the login date of this user.
	 *
	 * @param loginDate the login date of this user
	 */
	public void setLoginDate(Date loginDate);

	/**
	 * Returns the login i p of this user.
	 *
	 * @return the login i p of this user
	 */
	@AutoEscape
	public String getLoginIP();

	/**
	 * Sets the login i p of this user.
	 *
	 * @param loginIP the login i p of this user
	 */
	public void setLoginIP(String loginIP);

	/**
	 * Returns the last login date of this user.
	 *
	 * @return the last login date of this user
	 */
	public Date getLastLoginDate();

	/**
	 * Sets the last login date of this user.
	 *
	 * @param lastLoginDate the last login date of this user
	 */
	public void setLastLoginDate(Date lastLoginDate);

	/**
	 * Returns the last login i p of this user.
	 *
	 * @return the last login i p of this user
	 */
	@AutoEscape
	public String getLastLoginIP();

	/**
	 * Sets the last login i p of this user.
	 *
	 * @param lastLoginIP the last login i p of this user
	 */
	public void setLastLoginIP(String lastLoginIP);

	/**
	 * Returns the last failed login date of this user.
	 *
	 * @return the last failed login date of this user
	 */
	public Date getLastFailedLoginDate();

	/**
	 * Sets the last failed login date of this user.
	 *
	 * @param lastFailedLoginDate the last failed login date of this user
	 */
	public void setLastFailedLoginDate(Date lastFailedLoginDate);

	/**
	 * Returns the failed login attempts of this user.
	 *
	 * @return the failed login attempts of this user
	 */
	public int getFailedLoginAttempts();

	/**
	 * Sets the failed login attempts of this user.
	 *
	 * @param failedLoginAttempts the failed login attempts of this user
	 */
	public void setFailedLoginAttempts(int failedLoginAttempts);

	/**
	 * Returns the lockout of this user.
	 *
	 * @return the lockout of this user
	 */
	public boolean getLockout();

	/**
	 * Returns <code>true</code> if this user is lockout.
	 *
	 * @return <code>true</code> if this user is lockout; <code>false</code> otherwise
	 */
	public boolean isLockout();

	/**
	 * Sets whether this user is lockout.
	 *
	 * @param lockout the lockout of this user
	 */
	public void setLockout(boolean lockout);

	/**
	 * Returns the lockout date of this user.
	 *
	 * @return the lockout date of this user
	 */
	public Date getLockoutDate();

	/**
	 * Sets the lockout date of this user.
	 *
	 * @param lockoutDate the lockout date of this user
	 */
	public void setLockoutDate(Date lockoutDate);

	/**
	 * Returns the agreed to terms of use of this user.
	 *
	 * @return the agreed to terms of use of this user
	 */
	public boolean getAgreedToTermsOfUse();

	/**
	 * Returns <code>true</code> if this user is agreed to terms of use.
	 *
	 * @return <code>true</code> if this user is agreed to terms of use; <code>false</code> otherwise
	 */
	public boolean isAgreedToTermsOfUse();

	/**
	 * Sets whether this user is agreed to terms of use.
	 *
	 * @param agreedToTermsOfUse the agreed to terms of use of this user
	 */
	public void setAgreedToTermsOfUse(boolean agreedToTermsOfUse);

	/**
	 * Returns the email address verified of this user.
	 *
	 * @return the email address verified of this user
	 */
	public boolean getEmailAddressVerified();

	/**
	 * Returns <code>true</code> if this user is email address verified.
	 *
	 * @return <code>true</code> if this user is email address verified; <code>false</code> otherwise
	 */
	public boolean isEmailAddressVerified();

	/**
	 * Sets whether this user is email address verified.
	 *
	 * @param emailAddressVerified the email address verified of this user
	 */
	public void setEmailAddressVerified(boolean emailAddressVerified);

	/**
	 * Returns the status of this user.
	 *
	 * @return the status of this user
	 */
	public int getStatus();

	/**
	 * Sets the status of this user.
	 *
	 * @param status the status of this user
	 */
	public void setStatus(int status);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(User user);

	public int hashCode();

	public CacheModel<User> toCacheModel();

	public User toEscapedModel();

	public String toString();

	public String toXmlString();
}