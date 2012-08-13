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

package com.liferay.taglib.util;

import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

/**
 * @author Shuyang Zhou
 */
public class OutputTag extends PositionTagSupport {

	public OutputTag(String stringBundlerKey) {
		_webKey = stringBundlerKey;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			if (!_output) {
				return EVAL_PAGE;
			}

			if (isPositionInLine()) {
				JspWriter jspWriter = pageContext.getOut();

				StringBundler bodyContent = getBodyContentAsStringBundler();

				jspWriter.write(bodyContent.toString());
			}
			else {
				HttpServletRequest request =
					(HttpServletRequest)pageContext.getRequest();

				StringBundler sb = (StringBundler)request.getAttribute(_webKey);

				if (sb == null) {
					sb = new StringBundler();

					request.setAttribute(_webKey, sb);
				}

				sb.append(getBodyContentAsStringBundler());
			}

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
		finally {
			if (!ServerDetector.isResin()) {
				cleanUp();
			}
		}
	}

	@Override
	public int doStartTag() {
		if (Validator.isNotNull(_outputKey)) {
			Set<String> outputKeys = getOutputKeys();

			if (!outputKeys.add(_outputKey)) {
				_output = false;

				return SKIP_BODY;
			}
		}

		_output = true;

		return EVAL_BODY_BUFFERED;
	}

	public void setOutputKey(String outputKey) {
		_outputKey = outputKey;
	}

	protected Set<String> getOutputKeys() {
		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		Set<String> outputKeys = (Set<String>)request.getAttribute(
			OutputTag.class.getName());

		if (outputKeys == null) {
			outputKeys = new HashSet<String>();

			request.setAttribute(OutputTag.class.getName(), outputKeys);
		}

		return outputKeys;
	}

	private boolean _output;
	private String _outputKey;
	private String _webKey;

}