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

package com.liferay.portal.security.ldap;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.pacl.PACLClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Brian Wing Shun Chan
 */
public class AttributesTransformerFactory {

	public static AttributesTransformer getInstance() {
		if (_attributesTransformer == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Instantiate " + PropsValues.LDAP_ATTRS_TRANSFORMER_IMPL);
			}

			ClassLoader classLoader =
				PACLClassLoaderUtil.getPortalClassLoader();

			try {
				_attributesTransformer =
					(AttributesTransformer)classLoader.loadClass(
						PropsValues.LDAP_ATTRS_TRANSFORMER_IMPL).newInstance();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Return " + _attributesTransformer.getClass().getName());
		}

		return _attributesTransformer;
	}

	public static void setInstance(
		AttributesTransformer attributesTransformer) {

		if (_log.isDebugEnabled()) {
			_log.debug("Set " + attributesTransformer.getClass().getName());
		}

		_attributesTransformer = attributesTransformer;
	}

	private static Log _log = LogFactoryUtil.getLog(
		AttributesTransformerFactory.class);

	private static AttributesTransformer _attributesTransformer;

}