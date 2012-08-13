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

package com.liferay.portal.deploy;

import com.liferay.portal.events.GlobalStartupAction;
import com.liferay.portal.kernel.deploy.DeployManager;
import com.liferay.portal.kernel.deploy.auto.AutoDeployListener;
import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.plugin.PluginPackageUtil;

import java.io.File;

import java.util.List;
import java.util.Properties;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public class DeployManagerImpl implements DeployManager {

	public void deploy(AutoDeploymentContext autoDeploymentContext)
		throws Exception {

		List<AutoDeployListener> autoDeployListeners =
			GlobalStartupAction.getAutoDeployListeners();

		for (AutoDeployListener autoDeployListener : autoDeployListeners) {
			autoDeployListener.deploy(autoDeploymentContext);
		}
	}

	public String getDeployDir() throws Exception {
		return DeployUtil.getAutoDeployDestDir();
	}

	public String getInstalledDir() throws Exception {
		if (ServerDetector.isGlassfish()) {
			File file = new File(
				System.getProperty("com.sun.aas.instanceRoot"), "autodeploy");

			return file.getAbsolutePath();
		}

		return DeployUtil.getAutoDeployDestDir();
	}

	public PluginPackage getInstalledPluginPackage(String context) {
		return PluginPackageUtil.getInstalledPluginPackage(context);
	}

	public List<PluginPackage> getInstalledPluginPackages() {
		return PluginPackageUtil.getInstalledPluginPackages();
	}

	public boolean isDeployed(String context) {
		return PluginPackageUtil.isInstalled(context);
	}

	public PluginPackage readPluginPackageProperties(
		String displayName, Properties properties) {

		return PluginPackageUtil.readPluginPackageProperties(
			displayName, properties);
	}

	public PluginPackage readPluginPackageXml(String xml) throws Exception {
		return PluginPackageUtil.readPluginPackageXml(xml);
	}

	public void redeploy(String context) throws Exception {
		if (ServerDetector.isJetty()) {
			DeployUtil.redeployJetty(context);
		}
		else if (ServerDetector.isTomcat()) {
			DeployUtil.redeployTomcat(context);
		}
	}

	public void undeploy(String context) throws Exception {
		File deployDir = new File(getDeployDir(), context);

		if (!deployDir.exists()) {
			deployDir = new File(getDeployDir(), context + ".war");
		}

		DeployUtil.undeploy(ServerDetector.getServerId(), deployDir);
	}

}