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

package com.liferay.portlet.journal.util;

import com.liferay.portal.freemarker.JournalTemplateLoader;
import com.liferay.portal.kernel.freemarker.FreeMarkerContext;
import com.liferay.portal.kernel.freemarker.FreeMarkerEngineUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.templateparser.TemplateContext;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PropsValues;
import com.liferay.util.ContentUtil;

import freemarker.core.ParseException;

import freemarker.template.TemplateException;

/**
 * @author Mika Koivisto
 */
public class FreeMarkerTemplateParser extends VelocityTemplateParser {

	@Override
	protected String getErrorTemplateContent() {
		return ContentUtil.get(PropsValues.JOURNAL_ERROR_TEMPLATE_FREEMARKER);
	}

	@Override
	protected String getErrorTemplateId() {
		return PropsValues.JOURNAL_ERROR_TEMPLATE_FREEMARKER;
	}

	@Override
	protected String getJournalTemplatesPath() {
		StringBundler sb = new StringBundler(5);

		sb.append(JournalTemplateLoader.JOURNAL_SEPARATOR);
		sb.append(StringPool.SLASH);
		sb.append(getCompanyId());
		sb.append(StringPool.SLASH);
		sb.append(getGroupId());

		return sb.toString();
	}

	@Override
	protected TemplateContext getTemplateContext() {
		return FreeMarkerEngineUtil.getWrappedRestrictedToolsContext();
	}

	@Override
	protected boolean mergeTemplate(
			TemplateContext templateContext,
			UnsyncStringWriter unsyncStringWriter)
		throws Exception {

		FreeMarkerContext freeMarkerContext =
			(FreeMarkerContext)templateContext;

		try {
			return FreeMarkerEngineUtil.mergeTemplate(
				getTemplateId(), getScript(), freeMarkerContext,
				unsyncStringWriter);
		}
		catch (Exception e) {
			if (e instanceof ParseException || e instanceof TemplateException) {
				String errorTemplateId = getErrorTemplateId();
				String errorTemplateContent = getErrorTemplateContent();

				freeMarkerContext.put("exception", e.getMessage());
				freeMarkerContext.put("script", getScript());

				if (e instanceof ParseException) {
					ParseException pe = (ParseException)e;

					freeMarkerContext.put("column", pe.getColumnNumber());
					freeMarkerContext.put("line", pe.getLineNumber());
				}

				unsyncStringWriter.reset();

				return FreeMarkerEngineUtil.mergeTemplate(
					errorTemplateId, errorTemplateContent, freeMarkerContext,
					unsyncStringWriter);
			}
			else {
				throw e;
			}
		}
	}

}