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

package com.liferay.portal.atom;

import com.liferay.portal.kernel.atom.AtomCollectionAdapter;
import com.liferay.portal.kernel.atom.AtomCollectionAdapterRegistry;
import com.liferay.portal.kernel.atom.AtomException;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Igor Spasic
 */
public class AtomCollectionAdapterRegistryImpl
	implements AtomCollectionAdapterRegistry {

	public AtomCollectionAdapter<?> getAtomCollectionAdapter(
		String collectionName) {

		return _atomCollectionAdapters.get(collectionName);
	}

	public List<AtomCollectionAdapter<?>> getAtomCollectionAdapters() {
		return ListUtil.fromMapValues(_atomCollectionAdapters);
	}

	public void register(AtomCollectionAdapter<?> atomCollectionAdapter)
		throws AtomException {

		if (_atomCollectionAdapters.containsKey(
				atomCollectionAdapter.getCollectionName())) {

			throw new AtomException(
				"Duplicate collection name " +
					atomCollectionAdapter.getCollectionName());
		}

		_atomCollectionAdapters.put(
			atomCollectionAdapter.getCollectionName(), atomCollectionAdapter);
	}

	public void unregister(AtomCollectionAdapter<?> atomCollectionAdapter) {
		_atomCollectionAdapters.remove(
			atomCollectionAdapter.getCollectionName());
	}

	private Map<String, AtomCollectionAdapter<?>> _atomCollectionAdapters =
		new ConcurrentHashMap<String, AtomCollectionAdapter<?>>();

}