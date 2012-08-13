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

package com.liferay.portlet.blogs.social;

import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.blogs.service.permission.BlogsEntryPermission;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityConstants;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

import java.text.Format;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public class BlogsActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!BlogsEntryPermission.contains(
				permissionChecker, activity.getClassPK(), ActionKeys.VIEW)) {

			return null;
		}

		String groupName = StringPool.BLANK;

		if (activity.getGroupId() != themeDisplay.getScopeGroupId()) {
			groupName = getGroupName(activity.getGroupId(), themeDisplay);
		}

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);
		String receiverUserName = getUserName(
			activity.getReceiverUserId(), themeDisplay);

		int activityType = activity.getType();

		// Link

		BlogsEntry entry = BlogsEntryLocalServiceUtil.getEntry(
			activity.getClassPK());

		String link =
			themeDisplay.getPortalURL() + themeDisplay.getPathMain() +
				"/blogs/find_entry?entryId=" + activity.getClassPK();

		// Title

		String entryTitle = getValue(
			activity.getExtraData(), "title", entry.getTitle());

		String displayTitle = wrapLink(link, entryTitle);
		String displayDate = StringPool.BLANK;

		String titlePattern = null;

		if ((activityType == BlogsActivityKeys.ADD_COMMENT) ||
			(activityType == SocialActivityConstants.TYPE_ADD_COMMENT)) {

			if (Validator.isNull(groupName)) {
				titlePattern = "activity-blogs-add-comment";
			}
			else {
				titlePattern = "activity-blogs-add-comment-in";
			}
		}
		else if (activityType == BlogsActivityKeys.ADD_ENTRY) {
			if (entry.getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
				if (Validator.isNull(groupName)) {
					titlePattern = "activity-blogs-scheduled-entry";
				}
				else {
					titlePattern = "activity-blogs-scheduled-entry-in";
				}

				Format dateFormatDate =
					FastDateFormatFactoryUtil.getSimpleDateFormat(
						"MMMM d", themeDisplay.getLocale(),
						themeDisplay.getTimeZone());

				displayDate = dateFormatDate.format(entry.getDisplayDate());

				displayTitle = entryTitle;
			}
			else {
				if (Validator.isNull(groupName)) {
					titlePattern = "activity-blogs-add-entry";
				}
				else {
					titlePattern = "activity-blogs-add-entry-in";
				}
			}
		}
		else if (activityType == BlogsActivityKeys.UPDATE_ENTRY) {
			if (Validator.isNull(groupName)) {
				titlePattern = "activity-blogs-update-entry";
			}
			else {
				titlePattern = "activity-blogs-update-entry-in";
			}
		}

		Object[] titleArguments = new Object[] {
			groupName, creatorUserName, receiverUserName, displayTitle,
			displayDate
		};

		String title = themeDisplay.translate(titlePattern, titleArguments);

		// Body

		String body = StringPool.BLANK;

		return new SocialActivityFeedEntry(link, title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		BlogsEntry.class.getName()
	};

}