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

package com.liferay.portal.deploy.auto;

import com.liferay.portal.kernel.deploy.auto.AutoDeployException;
import com.liferay.portal.kernel.deploy.auto.BaseAutoDeployListener;
import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.File;

/**
 * @author Ryan Park
 */
public class LiferayPackageAutoDeployListener extends BaseAutoDeployListener {

	public LiferayPackageAutoDeployListener() {
		_autoDeployer = new LiferayPackageAutoDeployer();
	}

	public void deploy(AutoDeploymentContext autoDeploymentContext)
		throws AutoDeployException {

		File file = autoDeploymentContext.getFile();

		if (_log.isDebugEnabled()) {
			_log.debug("Invoking deploy for " + file.getPath());
		}

		if (!isLiferayPackage(file)) {
			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Extracting Liferay package for " + file.getPath());
		}

		int code = _autoDeployer.autoDeploy(autoDeploymentContext);

		if ((code == AutoDeployer.CODE_DEFAULT) && _log.isInfoEnabled()) {
			_log.info(
				"Liferay package for " + file.getPath() +" extracted " +
					"successfully. Deployment will start in a few seconds.");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LiferayPackageAutoDeployListener.class);

	private AutoDeployer _autoDeployer;

}