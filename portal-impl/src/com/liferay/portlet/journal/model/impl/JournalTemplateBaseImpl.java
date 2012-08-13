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

package com.liferay.portlet.journal.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil;

/**
 * The extended model base implementation for the JournalTemplate service. Represents a row in the &quot;JournalTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link JournalTemplateImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JournalTemplateImpl
 * @see com.liferay.portlet.journal.model.JournalTemplate
 * @generated
 */
public abstract class JournalTemplateBaseImpl extends JournalTemplateModelImpl
	implements JournalTemplate {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a journal template model instance should use the {@link JournalTemplate} interface instead.
	 */
	public void persist() throws SystemException {
		if (this.isNew()) {
			JournalTemplateLocalServiceUtil.addJournalTemplate(this);
		}
		else {
			JournalTemplateLocalServiceUtil.updateJournalTemplate(this);
		}
	}
}