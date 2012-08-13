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

package com.liferay.portal.deploy.auto.exploded.tomcat;

import com.liferay.portal.kernel.deploy.auto.AutoDeployException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.Portal;

import java.io.File;

/**
 * @author Gregory Amerson
 */
public class HookExplodedTomcatListener extends BaseExplodedTomcatListener {

	public HookExplodedTomcatListener() {
		_deployer = new HookExplodedTomcatDeployer();
	}

	@Override
	protected void deploy(File file) throws AutoDeployException {
		if (_log.isDebugEnabled()) {
			_log.debug("Invoking deploy for " + file.getPath());
		}

		File docBaseDir = getDocBaseDir(
			file, "WEB-INF/" + Portal.PORTLET_XML_FILE_NAME_STANDARD);

		if (docBaseDir != null) {
			return;
		}

		docBaseDir = getDocBaseDir(file, "WEB-INF/liferay-hook.xml");

		if (docBaseDir == null) {
			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Modifying hook for " + file.getPath());
		}

		_deployer.explodedTomcatDeploy(file, docBaseDir, null);

		if (_log.isInfoEnabled()) {
			_log.info("Hook for " + file.getPath() + " modified successfully");
		}

		copyContextFile(file);
	}

	private static Log _log = LogFactoryUtil.getLog(
		HookExplodedTomcatListener.class);

	private ExplodedTomcatDeployer _deployer;

}