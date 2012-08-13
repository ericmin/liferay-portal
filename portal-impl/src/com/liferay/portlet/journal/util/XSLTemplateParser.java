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

import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.templateparser.TemplateContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.util.ContentUtil;

import java.util.Locale;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author Alexander Chow
 * @author Raymond Augé
 */
public class XSLTemplateParser extends VelocityTemplateParser {

	@Override
	protected String getErrorTemplateContent() {
		return ContentUtil.get(PropsValues.JOURNAL_ERROR_TEMPLATE_XSL);
	}

	@Override
	protected TemplateContext getTemplateContext() throws Exception {
		return _getTemplateContext(getScript());
	}

	@Override
	protected boolean mergeTemplate(
			TemplateContext templateContext,
			UnsyncStringWriter unsyncStringWriter)
		throws Exception {

		StreamSource xmlSource = new StreamSource(
			new UnsyncStringReader(getXML()));

		XSLContext xslContext = (XSLContext)templateContext;

		Transformer transformer = xslContext.getTransformer();

		try {
			transformer.transform(
				xmlSource, new StreamResult(unsyncStringWriter));
		}
		catch (Exception e) {
			String errorTemplateContent = getErrorTemplateContent();

			XSLContext errorXSLContext = (XSLContext)_getTemplateContext(
				errorTemplateContent);

			populateTemplateContext(errorXSLContext);

			Transformer errorTransformer = errorXSLContext.getTransformer();

			XSLErrorListener xslErrorListener = _getXSLErrorListener();

			errorTransformer.setParameter(
				"exception", xslErrorListener.getMessageAndLocation());
			errorTransformer.setParameter("script", getScript());

			if (xslErrorListener.getLocation() != null) {
				errorTransformer.setParameter(
					"column", new Integer(xslErrorListener.getColumnNumber()));
				errorTransformer.setParameter(
					"line", new Integer(xslErrorListener.getLineNumber()));
			}

			unsyncStringWriter.reset();

			errorTransformer.transform(
				xmlSource, new StreamResult(unsyncStringWriter));
		}

		return true;
	}

	private TemplateContext _getTemplateContext(String script)
		throws Exception {

		TransformerFactory transformerFactory = _getTransformerFactory();

		StreamSource scriptSource = new StreamSource(
			new UnsyncStringReader(script));

		return new XSLContext(transformerFactory.newTransformer(scriptSource));
	}

	private TransformerFactory _getTransformerFactory() {
		if (_transformerFactory == null) {
			_transformerFactory = TransformerFactory.newInstance();

			_transformerFactory.setErrorListener(_getXSLErrorListener());
			_transformerFactory.setURIResolver(
				new URIResolver(getTokens(), getLanguageId()));
		}

		return _transformerFactory;
	}

	private XSLErrorListener _getXSLErrorListener() {
		if (_xslErrorListener == null) {
			Locale locale = LocaleUtil.fromLanguageId(getLanguageId());

			_xslErrorListener = new XSLErrorListener(locale);
		}

		return _xslErrorListener;
	}

	private TransformerFactory _transformerFactory;
	private XSLErrorListener _xslErrorListener;

}