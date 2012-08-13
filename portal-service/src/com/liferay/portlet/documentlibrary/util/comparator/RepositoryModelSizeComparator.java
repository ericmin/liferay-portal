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

package com.liferay.portlet.documentlibrary.util.comparator;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

/**
 * @author Alexander Chow
 */
public class RepositoryModelSizeComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "size_ ASC";

	public static final String ORDER_BY_DESC = "size_ DESC";

	public static final String[] ORDER_BY_FIELDS = {"size_"};

	public RepositoryModelSizeComparator() {
		this(false);
	}

	public RepositoryModelSizeComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		Long size1 = getSize(obj1);
		Long size2 = getSize(obj2);

		int value = size1.compareTo(size2);

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	protected long getSize(Object obj) {
		if (obj instanceof DLFileEntry) {
			DLFileEntry dlFileEntry = (DLFileEntry)obj;

			return dlFileEntry.getSize();
		}
		else if (obj instanceof DLFileShortcut) {
			DLFileShortcut dlFileShortcut = (DLFileShortcut)obj;

			long toFileEntryId = dlFileShortcut.getToFileEntryId();

			try {
				DLFileEntry dlFileEntry =
					DLFileEntryLocalServiceUtil.getFileEntry(toFileEntryId);

				return dlFileEntry.getSize();
			}
			catch (Exception e) {
				return 0;
			}
		}
		else if ((obj instanceof DLFolder) || (obj instanceof Folder)) {
			return 0;
		}
		else {
			FileEntry fileEntry = (FileEntry)obj;

			return fileEntry.getSize();
		}
	}

	private boolean _ascending;

}