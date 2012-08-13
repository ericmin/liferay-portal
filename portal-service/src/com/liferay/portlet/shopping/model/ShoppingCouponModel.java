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

package com.liferay.portlet.shopping.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.GroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ShoppingCoupon service. Represents a row in the &quot;ShoppingCoupon&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.shopping.model.impl.ShoppingCouponModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.shopping.model.impl.ShoppingCouponImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCoupon
 * @see com.liferay.portlet.shopping.model.impl.ShoppingCouponImpl
 * @see com.liferay.portlet.shopping.model.impl.ShoppingCouponModelImpl
 * @generated
 */
public interface ShoppingCouponModel extends BaseModel<ShoppingCoupon>,
	GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a shopping coupon model instance should use the {@link ShoppingCoupon} interface instead.
	 */

	/**
	 * Returns the primary key of this shopping coupon.
	 *
	 * @return the primary key of this shopping coupon
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this shopping coupon.
	 *
	 * @param primaryKey the primary key of this shopping coupon
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the coupon ID of this shopping coupon.
	 *
	 * @return the coupon ID of this shopping coupon
	 */
	public long getCouponId();

	/**
	 * Sets the coupon ID of this shopping coupon.
	 *
	 * @param couponId the coupon ID of this shopping coupon
	 */
	public void setCouponId(long couponId);

	/**
	 * Returns the group ID of this shopping coupon.
	 *
	 * @return the group ID of this shopping coupon
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this shopping coupon.
	 *
	 * @param groupId the group ID of this shopping coupon
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this shopping coupon.
	 *
	 * @return the company ID of this shopping coupon
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this shopping coupon.
	 *
	 * @param companyId the company ID of this shopping coupon
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this shopping coupon.
	 *
	 * @return the user ID of this shopping coupon
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this shopping coupon.
	 *
	 * @param userId the user ID of this shopping coupon
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this shopping coupon.
	 *
	 * @return the user uuid of this shopping coupon
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this shopping coupon.
	 *
	 * @param userUuid the user uuid of this shopping coupon
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this shopping coupon.
	 *
	 * @return the user name of this shopping coupon
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this shopping coupon.
	 *
	 * @param userName the user name of this shopping coupon
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this shopping coupon.
	 *
	 * @return the create date of this shopping coupon
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this shopping coupon.
	 *
	 * @param createDate the create date of this shopping coupon
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this shopping coupon.
	 *
	 * @return the modified date of this shopping coupon
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this shopping coupon.
	 *
	 * @param modifiedDate the modified date of this shopping coupon
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the code of this shopping coupon.
	 *
	 * @return the code of this shopping coupon
	 */
	@AutoEscape
	public String getCode();

	/**
	 * Sets the code of this shopping coupon.
	 *
	 * @param code the code of this shopping coupon
	 */
	public void setCode(String code);

	/**
	 * Returns the name of this shopping coupon.
	 *
	 * @return the name of this shopping coupon
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this shopping coupon.
	 *
	 * @param name the name of this shopping coupon
	 */
	public void setName(String name);

	/**
	 * Returns the description of this shopping coupon.
	 *
	 * @return the description of this shopping coupon
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this shopping coupon.
	 *
	 * @param description the description of this shopping coupon
	 */
	public void setDescription(String description);

	/**
	 * Returns the start date of this shopping coupon.
	 *
	 * @return the start date of this shopping coupon
	 */
	public Date getStartDate();

	/**
	 * Sets the start date of this shopping coupon.
	 *
	 * @param startDate the start date of this shopping coupon
	 */
	public void setStartDate(Date startDate);

	/**
	 * Returns the end date of this shopping coupon.
	 *
	 * @return the end date of this shopping coupon
	 */
	public Date getEndDate();

	/**
	 * Sets the end date of this shopping coupon.
	 *
	 * @param endDate the end date of this shopping coupon
	 */
	public void setEndDate(Date endDate);

	/**
	 * Returns the active of this shopping coupon.
	 *
	 * @return the active of this shopping coupon
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this shopping coupon is active.
	 *
	 * @return <code>true</code> if this shopping coupon is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this shopping coupon is active.
	 *
	 * @param active the active of this shopping coupon
	 */
	public void setActive(boolean active);

	/**
	 * Returns the limit categories of this shopping coupon.
	 *
	 * @return the limit categories of this shopping coupon
	 */
	@AutoEscape
	public String getLimitCategories();

	/**
	 * Sets the limit categories of this shopping coupon.
	 *
	 * @param limitCategories the limit categories of this shopping coupon
	 */
	public void setLimitCategories(String limitCategories);

	/**
	 * Returns the limit skus of this shopping coupon.
	 *
	 * @return the limit skus of this shopping coupon
	 */
	@AutoEscape
	public String getLimitSkus();

	/**
	 * Sets the limit skus of this shopping coupon.
	 *
	 * @param limitSkus the limit skus of this shopping coupon
	 */
	public void setLimitSkus(String limitSkus);

	/**
	 * Returns the min order of this shopping coupon.
	 *
	 * @return the min order of this shopping coupon
	 */
	public double getMinOrder();

	/**
	 * Sets the min order of this shopping coupon.
	 *
	 * @param minOrder the min order of this shopping coupon
	 */
	public void setMinOrder(double minOrder);

	/**
	 * Returns the discount of this shopping coupon.
	 *
	 * @return the discount of this shopping coupon
	 */
	public double getDiscount();

	/**
	 * Sets the discount of this shopping coupon.
	 *
	 * @param discount the discount of this shopping coupon
	 */
	public void setDiscount(double discount);

	/**
	 * Returns the discount type of this shopping coupon.
	 *
	 * @return the discount type of this shopping coupon
	 */
	@AutoEscape
	public String getDiscountType();

	/**
	 * Sets the discount type of this shopping coupon.
	 *
	 * @param discountType the discount type of this shopping coupon
	 */
	public void setDiscountType(String discountType);

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

	public int compareTo(ShoppingCoupon shoppingCoupon);

	public int hashCode();

	public CacheModel<ShoppingCoupon> toCacheModel();

	public ShoppingCoupon toEscapedModel();

	public String toString();

	public String toXmlString();
}