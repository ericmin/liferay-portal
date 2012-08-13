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

import com.liferay.portal.kernel.freemarker.FreeMarkerEngineUtil;
import com.liferay.portal.kernel.velocity.VelocityEngineUtil;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.servlet.filters.cache.CacheUtil;
import com.liferay.portlet.journalcontent.util.JournalContentUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Jon Steer
 * @author Raymond Augé
 * @author Shuyang Zhou
 */
public class JournalTemplateListener
	extends BaseModelListener<JournalTemplate> {

	@Override
	public void onAfterRemove(JournalTemplate template) {
		clearCache(template);
	}

	@Override
	public void onAfterUpdate(JournalTemplate template) {
		clearCache(template);
	}

	protected void clearCache(JournalTemplate template) {

		// Freemarker cache

		String freeMarkerTemplateId =
			template.getCompanyId() + template.getGroupId() +
				template.getTemplateId();

		FreeMarkerEngineUtil.flushTemplate(freeMarkerTemplateId);

		// Journal content

		JournalContentUtil.clearCache();

		// Layout cache

		CacheUtil.clearCache(template.getCompanyId());

		// Velocity cache

		VelocityEngineUtil.flushTemplate(freeMarkerTemplateId);
	}

}