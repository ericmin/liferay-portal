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

package com.liferay.portal.util;

import com.liferay.portal.kernel.concurrent.ConcurrentLFUCache;

import java.io.File;

/**
 * @author Igor Spasic
 */
public class LimitedFilesCache<T> {

	public LimitedFilesCache(int maxSize) {
		_fileRemovingLFUCache = new FileRemovingLFUCache<T>(maxSize);
	}

	public File get(T key) {
		return _fileRemovingLFUCache.get(key);
	}

	public void put(T key) {
		_fileRemovingLFUCache.put(key, null);
	}

	public void put(T key, File file) {
		_fileRemovingLFUCache.put(key, file);
	}

	private FileRemovingLFUCache<T> _fileRemovingLFUCache;

	private class FileRemovingLFUCache<K> extends
		ConcurrentLFUCache<K, File> {

		public FileRemovingLFUCache(int maxSize) {
			super(maxSize);
		}

		@Override
		protected void onRemove(K key, File cachedFile) {
			if (cachedFile != null) {
				cachedFile.delete();
			}
			else {
				File file = new File(key.toString());

				file.delete();
			}
		}

	}

}