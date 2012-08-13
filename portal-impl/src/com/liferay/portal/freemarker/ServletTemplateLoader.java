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

package com.liferay.portal.freemarker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.ContextPathUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;

import java.net.URL;

import javax.servlet.ServletContext;

/**
 * @author Mika Koivisto
 */
public class ServletTemplateLoader extends URLTemplateLoader {

	@Override
	public URL getURL(String name) throws IOException {
		URL url = null;

		int pos = name.indexOf(SERVLET_SEPARATOR);

		if (pos != -1) {
			String servletContextName = name.substring(0, pos);

			String servletContextPath = ContextPathUtil.getContextPath(
				StringPool.SLASH + servletContextName);

			String contextPath = PortalUtil.getPathContext();

			if (Validator.isNull(servletContextName) ||
				servletContextPath.equals(contextPath)) {

				servletContextName = contextPath;
			}

			ServletContext servletContext = ServletContextPool.get(
				servletContextName);

			if (servletContext != null) {
				String templateName = name.substring(
					pos + SERVLET_SEPARATOR.length());

				if (_log.isDebugEnabled()) {
					_log.debug(
						name + " is associated with the servlet context " +
							servletContextName + " " + servletContext);
				}

				url = servletContext.getResource(templateName);

				if ((url == null) &&
					templateName.endsWith("/init_custom.ftl")) {

					if (_log.isWarnEnabled()) {
						_log.warn(
							"The template " + name + " should be created");
					}

					String portalServletContextName =
						PortalUtil.getPathContext();

					ServletContext portalServletContext =
						ServletContextPool.get(portalServletContextName);

					url = portalServletContext.getResource(
						"/html/themes/_unstyled/template/init_custom.ftl");
				}
			}
			else {
				_log.error(
					name + " is not valid because " + servletContextName +
						" does not map to a servlet context");
			}
		}

		return url;
	}

	private static Log _log = LogFactoryUtil.getLog(
		ServletTemplateLoader.class);

}