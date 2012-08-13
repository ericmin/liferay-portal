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

package com.liferay.portal.asset;

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portlet.asset.AssetRendererFactoryRegistry;
import com.liferay.portlet.asset.model.AssetRendererFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Bruno Farache
 * @author Marcellus Tavares
 */
public class AssetRendererFactoryRegistryImpl
	implements AssetRendererFactoryRegistry {

	public List<AssetRendererFactory> getAssetRendererFactories() {
		return ListUtil.fromMapValues(_assetRenderFactoriesMapByClassName);
	}

	public AssetRendererFactory getAssetRendererFactoryByClassName(
		String className) {

		return _assetRenderFactoriesMapByClassName.get(className);
	}

	public AssetRendererFactory getAssetRendererFactoryByType(String type) {
		return _assetRenderFactoriesMapByClassType.get(type);
	}

	public long[] getClassNameIds() {
		long[] classNameIds = new long[
			_assetRenderFactoriesMapByClassName.size()];

		int i = 0;

		for (AssetRendererFactory assetRendererFactory :
				_assetRenderFactoriesMapByClassName.values()) {

			classNameIds[i] = assetRendererFactory.getClassNameId();

			i++;
		}

		return classNameIds;
	}

	public void register(AssetRendererFactory assetRendererFactory) {
		_assetRenderFactoriesMapByClassName.put(
			assetRendererFactory.getClassName(), assetRendererFactory);
		_assetRenderFactoriesMapByClassType.put(
			assetRendererFactory.getType(), assetRendererFactory);
	}

	public void unregister(AssetRendererFactory assetRendererFactory) {
		_assetRenderFactoriesMapByClassName.remove(
			assetRendererFactory.getClassName());
		_assetRenderFactoriesMapByClassType.remove(
			assetRendererFactory.getType());
	}

	private Map<String, AssetRendererFactory>
		_assetRenderFactoriesMapByClassName =
			new ConcurrentHashMap<String, AssetRendererFactory>();
	private Map<String, AssetRendererFactory>
		_assetRenderFactoriesMapByClassType =
			new ConcurrentHashMap<String, AssetRendererFactory>();

}