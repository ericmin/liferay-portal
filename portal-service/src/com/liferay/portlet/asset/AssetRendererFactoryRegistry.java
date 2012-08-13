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

package com.liferay.portlet.asset;

import com.liferay.portlet.asset.model.AssetRendererFactory;

import java.util.List;

/**
 * @author Bruno Farache
 * @author Marcellus Tavares
 */
public interface AssetRendererFactoryRegistry {

	public List<AssetRendererFactory> getAssetRendererFactories();

	public AssetRendererFactory getAssetRendererFactoryByClassName(
		String className);

	public AssetRendererFactory getAssetRendererFactoryByType(String type);

	public long[] getClassNameIds();

	public void register(AssetRendererFactory assetRendererFactory);

	public void unregister(AssetRendererFactory assetRendererFactory);

}