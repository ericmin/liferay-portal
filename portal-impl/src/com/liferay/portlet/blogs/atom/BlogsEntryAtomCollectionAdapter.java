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

package com.liferay.portlet.blogs.atom;

import com.liferay.portal.atom.AtomPager;
import com.liferay.portal.atom.AtomUtil;
import com.liferay.portal.kernel.atom.AtomEntryContent;
import com.liferay.portal.kernel.atom.AtomRequestContext;
import com.liferay.portal.kernel.atom.BaseAtomCollectionAdapter;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Image;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryServiceUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Igor Spasic
 */
public class BlogsEntryAtomCollectionAdapter
	extends BaseAtomCollectionAdapter<BlogsEntry> {

	public String getCollectionName() {
		return _COLLECTION_NAME;
	}

	public List<String> getEntryAuthors(BlogsEntry blogsEntry) {
		List<String> authors = new ArrayList<String>();

		authors.add(blogsEntry.getUserName());

		return authors;
	}

	public AtomEntryContent getEntryContent(
		BlogsEntry blogsEntry, AtomRequestContext atomRequestContext) {

		return new AtomEntryContent(blogsEntry.getContent());
	}

	public String getEntryId(BlogsEntry blogsEntry) {
		return String.valueOf(blogsEntry.getEntryId());
	}

	public String getEntrySummary(BlogsEntry blogsEntry) {
		return blogsEntry.getDescription();
	}

	public String getEntryTitle(BlogsEntry blogsEntry) {
		return blogsEntry.getTitle();
	}

	public Date getEntryUpdated(BlogsEntry blogsEntry) {
		return blogsEntry.getModifiedDate();
	}

	public String getFeedTitle(AtomRequestContext atomRequestContext) {
		return AtomUtil.createFeedTitleFromPortletName(
			atomRequestContext, PortletKeys.BLOGS);
	}

	@Override
	protected void doDeleteEntry(
			String resourceName, AtomRequestContext atomRequestContext)
		throws Exception {

		long blogsEntryId = GetterUtil.getLong(resourceName);

		BlogsEntryServiceUtil.deleteEntry(blogsEntryId);
	}

	@Override
	protected BlogsEntry doGetEntry(
			String resourceName, AtomRequestContext atomRequestContext)
		throws Exception {

		long blogsEntryId = GetterUtil.getLong(resourceName);

		return BlogsEntryServiceUtil.getEntry(blogsEntryId);
	}

	@Override
	protected Iterable<BlogsEntry> doGetFeedEntries(
			AtomRequestContext atomRequestContext)
		throws Exception {

		long groupId = atomRequestContext.getLongParameter("groupId");
		int status = WorkflowConstants.STATUS_APPROVED;

		int max = atomRequestContext.getIntParameter(
			"max", SearchContainer.DEFAULT_DELTA);

		if (groupId > 0) {
			int page = atomRequestContext.getIntParameter("page");

			if (page == 0) {
				return BlogsEntryServiceUtil.getGroupEntries(
					groupId, status, max);
			}

			int count = BlogsEntryServiceUtil.getGroupEntriesCount(
				groupId, new Date(), status);

			AtomPager atomPager = new AtomPager(page, max, count);

			AtomUtil.saveAtomPagerInRequest(atomRequestContext, atomPager);

			return BlogsEntryServiceUtil.getGroupEntries(
				groupId, new Date(), status, atomPager.getStart(),
				atomPager.getEnd() + 1);
		}

		long organizationId = atomRequestContext.getLongParameter(
			"organizationId");

		if (organizationId > 0) {
			return BlogsEntryServiceUtil.getOrganizationEntries(
				organizationId, new Date(), status, max);
		}

		long companyId = CompanyThreadLocal.getCompanyId();

		if (companyId > 0) {
			return BlogsEntryServiceUtil.getCompanyEntries(
				companyId, new Date(), status, max);
		}

		return Collections.emptyList();
	}

	@Override
	protected BlogsEntry doPostEntry(
			String title, String summary, String content, Date date,
			AtomRequestContext atomRequestContext)
		throws Exception {

		long groupId = atomRequestContext.getLongParameter("groupId");

		Calendar cal = Calendar.getInstance();

		cal.setTime(date);

		int displayDateMonth = cal.get(Calendar.MONTH);
		int displayDateDay = cal.get(Calendar.DAY_OF_MONTH);
		int displayDateYear = cal.get(Calendar.YEAR);
		int displayDateHour = cal.get(Calendar.HOUR_OF_DAY);
		int displayDateMinute = cal.get(Calendar.MINUTE);

		boolean allowPingbacks = true;
		boolean allowTrackbacks = true;
		String[] trackbacks = new String[0];

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setScopeGroupId(groupId);

		return BlogsEntryServiceUtil.addEntry(
			title, summary, content, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute, allowPingbacks,
			allowTrackbacks, trackbacks, false, null, null, null,
			serviceContext);
	}

	@Override
	protected void doPutEntry(
			BlogsEntry blogsEntry, String title, String summary, String content,
			Date date, AtomRequestContext atomRequestContext)
		throws Exception {

		Calendar cal = Calendar.getInstance();

		cal.setTime(date);

		int displayDateMonth = cal.get(Calendar.MONTH);
		int displayDateDay = cal.get(Calendar.DAY_OF_MONTH);
		int displayDateYear = cal.get(Calendar.YEAR);
		int displayDateHour = cal.get(Calendar.HOUR_OF_DAY);
		int displayDateMinute = cal.get(Calendar.MINUTE);

		String[] trackbacks = StringUtil.split(blogsEntry.getTrackbacks());

		String smallImageFileName = null;
		InputStream smallImageInputStream = null;

		try {
			long smallImageId = blogsEntry.getSmallImageId();

			if (smallImageId != 0) {
				Image smallImage = ImageLocalServiceUtil.getImage(smallImageId);

				if (smallImage != null) {
					smallImageFileName =
						smallImageId + StringPool.PERIOD +
							blogsEntry.getSmallImageType();

					byte[] smallImageBytes = smallImage.getTextObj();

					smallImageInputStream = new ByteArrayInputStream(
						smallImageBytes);
				}
			}

			ServiceContext serviceContext = new ServiceContext();

			BlogsEntryServiceUtil.updateEntry(
				blogsEntry.getEntryId(), title, summary, content,
				displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute,
				blogsEntry.getAllowPingbacks(), blogsEntry.isAllowTrackbacks(),
				trackbacks, blogsEntry.isSmallImage(),
				blogsEntry.getSmallImageURL(), smallImageFileName,
				smallImageInputStream, serviceContext);
		}
		finally {
			StreamUtil.cleanUp(smallImageInputStream);
		}

	}

	private static final String _COLLECTION_NAME = "blogs";

}