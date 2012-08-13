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

package com.liferay.portlet.social.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portlet.social.model.SocialActivityAchievement;
import com.liferay.portlet.social.service.SocialActivityAchievementLocalServiceUtil;

/**
 * The extended model base implementation for the SocialActivityAchievement service. Represents a row in the &quot;SocialActivityAchievement&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SocialActivityAchievementImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityAchievementImpl
 * @see com.liferay.portlet.social.model.SocialActivityAchievement
 * @generated
 */
public abstract class SocialActivityAchievementBaseImpl
	extends SocialActivityAchievementModelImpl
	implements SocialActivityAchievement {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a social activity achievement model instance should use the {@link SocialActivityAchievement} interface instead.
	 */
	public void persist() throws SystemException {
		if (this.isNew()) {
			SocialActivityAchievementLocalServiceUtil.addSocialActivityAchievement(this);
		}
		else {
			SocialActivityAchievementLocalServiceUtil.updateSocialActivityAchievement(this);
		}
	}
}