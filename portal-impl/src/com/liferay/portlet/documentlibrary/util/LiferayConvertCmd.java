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

package com.liferay.portlet.documentlibrary.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import jodd.util.StringPool;

import org.im4java.core.ConvertCmd;

/**
 * @author Alexander Chow
 */
public class LiferayConvertCmd extends ConvertCmd {

	public static void run(
			String globalSearchPath, Properties resourceLimitsProperties,
			List<String> commandArguments)
		throws Exception {

		setGlobalSearchPath(globalSearchPath);

		LinkedList<String> arguments = new LinkedList<String>();

		arguments.addAll(_instance.getCommand());

		for (Object key : resourceLimitsProperties.keySet()) {
			String value = (String)resourceLimitsProperties.get(key);

			if (Validator.isNull(value)) {
				continue;
			}

			arguments.add("-limit");
			arguments.add((String)key);
			arguments.add(value);
		}

		arguments.addAll(commandArguments);

		if (_log.isInfoEnabled()) {
			StringBundler sb = new StringBundler(arguments.size() * 2);

			for (String argument : arguments) {
				sb.append(argument);
				sb.append(StringPool.SPACE);
			}

			_log.info("Excecuting command '" + sb.toString() + "'");
		}

		_instance.run(arguments);
	}

	private static Log _log = LogFactoryUtil.getLog(LiferayConvertCmd.class);

	private static LiferayConvertCmd _instance = new LiferayConvertCmd();

}