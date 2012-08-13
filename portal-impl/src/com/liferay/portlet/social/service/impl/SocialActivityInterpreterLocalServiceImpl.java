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

package com.liferay.portlet.social.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.model.SocialActivityInterpreter;
import com.liferay.portlet.social.model.impl.SocialActivityInterpreterImpl;
import com.liferay.portlet.social.service.base.SocialActivityInterpreterLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * The social activity interpreter local service. Activity interpreters are
 * classes responsible for translating activity records into human readable
 * form. This service holds a list of interpreters and provides methods to add
 * or remove items from this list.
 *
 * <p>
 * Activity interpreters use the language files to get text fragments based on
 * the activity's type and the type of asset on which the activity was done.
 * Interpreters are created for specific asset types and are only capable of
 * translating activities done on assets of those types. As an example, there is
 * an interpreter BlogsActivityInterpreter that can only translate activity
 * records for blog entries.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class SocialActivityInterpreterLocalServiceImpl
	extends SocialActivityInterpreterLocalServiceBaseImpl {

	/**
	 * Adds the activity interpreter to the list of available interpreters.
	 *
	 * @param activityInterpreter the activity interpreter
	 */
	public void addActivityInterpreter(
		SocialActivityInterpreter activityInterpreter) {

		_activityInterpreters.add(activityInterpreter);
	}

	/**
	 * Removes the activity interpreter from the list of available interpreters.
	 *
	 * @param activityInterpreter the activity interpreter
	 */
	public void deleteActivityInterpreter(
		SocialActivityInterpreter activityInterpreter) {

		if (activityInterpreter != null) {
			_activityInterpreters.remove(activityInterpreter);
		}
	}

	/**
	 * Creates a human readable activity feed entry for the activity using an
	 * available compatible activity interpreter.
	 *
	 * <p>
	 * This method finds the appropriate interpreter for the activity by going
	 * through the available interpreters and asking them if they can handle the
	 * asset type of the activity.
	 * </p>
	 *
	 * @param  activity the activity to be translated to human readable form
	 * @param  themeDisplay the theme display needed by interpreters to create
	 *         links and get localized text fragments
	 * @return the activity feed that is a human readable form of the activity
	 *         record or <code>null</code> if a compatible interpreter is not
	 *         found
	 */
	public SocialActivityFeedEntry interpret(
		SocialActivity activity, ThemeDisplay themeDisplay) {

		try {
			if (activity.getUserId() == themeDisplay.getDefaultUserId()) {
				return null;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (activity.getMirrorActivityId() > 0) {
			SocialActivity mirrorActivity = null;

			try {
				mirrorActivity = socialActivityLocalService.getActivity(
					activity.getMirrorActivityId());
			}
			catch (Exception e) {
			}

			if (mirrorActivity != null) {
				activity = mirrorActivity;
			}
		}

		String className = PortalUtil.getClassName(activity.getClassNameId());

		for (int i = 0; i < _activityInterpreters.size(); i++) {
			SocialActivityInterpreterImpl activityInterpreter =
				(SocialActivityInterpreterImpl)_activityInterpreters.get(i);

			if (activityInterpreter.hasClassName(className)) {
				SocialActivityFeedEntry activityFeedEntry =
					activityInterpreter.interpret(activity, themeDisplay);

				if (activityFeedEntry != null) {
					activityFeedEntry.setPortletId(
						activityInterpreter.getPortletId());

					return activityFeedEntry;
				}
			}
		}

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(
		SocialActivityInterpreterLocalServiceImpl.class);

	private List<SocialActivityInterpreter> _activityInterpreters =
		new ArrayList<SocialActivityInterpreter>();

}