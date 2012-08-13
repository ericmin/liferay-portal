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

package com.liferay.portal.kernel.atom;

import java.util.List;

/**
 * @author Igor Spasic
 */
public interface AtomCollectionAdapterRegistry {

	public AtomCollectionAdapter<?> getAtomCollectionAdapter(
		String collectionName);

	public List<AtomCollectionAdapter<?>> getAtomCollectionAdapters();

	public void register(AtomCollectionAdapter<?> atomCollectionAdapter)
		throws AtomException;

	public void unregister(AtomCollectionAdapter<?> atomCollectionAdapter);

}