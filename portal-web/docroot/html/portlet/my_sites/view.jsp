<%--
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
--%>

<%@ include file="/html/portlet/sites_admin/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "my-sites");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/my_sites/view");
portletURL.setParameter("tabs1", tabs1);

pageContext.setAttribute("portletURL", portletURL);

request.setAttribute("view.jsp-tabs1", tabs1);
%>

<liferay-ui:success key="membership_request_sent" message="your-request-was-sent-you-will-receive-a-reply-by-email" />

<aui:form action="<%= portletURL.toString() %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="portletURL" />

	<liferay-ui:tabs
		names="my-sites,available-sites"
		url="<%= portletURL.toString() %>"
	/>

	<liferay-ui:search-container
		searchContainer="<%= new GroupSearch(renderRequest, portletURL) %>"
	>

		<%
		GroupSearchTerms searchTerms = (GroupSearchTerms)searchContainer.getSearchTerms();

		LinkedHashMap groupParams = new LinkedHashMap();

		groupParams.put("site", Boolean.TRUE);

		if (tabs1.equals("my-sites")) {
			groupParams.put("usersGroups", new Long(user.getUserId()));
			groupParams.put("active", Boolean.TRUE);
		}
		else if (tabs1.equals("available-sites")) {
			List types = new ArrayList();

			types.add(new Integer(GroupConstants.TYPE_SITE_OPEN));
			types.add(new Integer(GroupConstants.TYPE_SITE_RESTRICTED));

			groupParams.put("types", types);
			groupParams.put("active", Boolean.TRUE);
		}
		%>

		<liferay-ui:search-container-results
			results="<%= GroupLocalServiceUtil.search(company.getCompanyId(), classNameIds, searchTerms.getName(), searchTerms.getDescription(), groupParams, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
			total="<%= GroupLocalServiceUtil.searchCount(company.getCompanyId(), classNameIds, searchTerms.getName(), searchTerms.getDescription(), groupParams) %>"
		/>

		<liferay-ui:search-form
			page="/html/portlet/users_admin/group_search.jsp"
			searchContainer="<%= searchContainer %>"
			showAddButton="<%= false %>"
		/>

		<liferay-ui:error exception="<%= RequiredGroupException.class %>">

			<%
			RequiredGroupException rge = (RequiredGroupException)errorException;

			long groupId = GetterUtil.getLong(rge.getMessage());

			Group group = GroupLocalServiceUtil.getGroup(groupId);
			%>

			<c:choose>
				<c:when test="<%= PortalUtil.isSystemGroup(group.getName()) %>">
					<liferay-ui:message key="the-site-cannot-be-deleted-or-deactivated-because-it-is-a-required-system-site" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="the-site-cannot-be-deleted-or-deactivated-because-you-are-accessing-the-site" />
				</c:otherwise>
			</c:choose>
		</liferay-ui:error>

		<liferay-ui:search-container-row
			className="com.liferay.portal.model.Group"
			escapedModel="<%= true %>"
			keyProperty="groupId"
			modelVar="group"
			rowIdProperty="friendlyURL"
		>

			<%
			PortletURL rowURL = null;

			if (group.getPublicLayoutsPageCount() > 0) {
				rowURL = renderResponse.createActionURL();

				rowURL.setWindowState(WindowState.NORMAL);

				rowURL.setParameter("struts_action", "/sites_admin/page");
				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("groupId", String.valueOf(group.getGroupId()));
				rowURL.setParameter("privateLayout", Boolean.FALSE.toString());
			}
			else if (tabs1.equals("my-sites") && (group.getPrivateLayoutsPageCount() > 0)) {
				rowURL = renderResponse.createActionURL();

				rowURL.setWindowState(WindowState.NORMAL);

				rowURL.setParameter("struts_action", "/sites_admin/page");
				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("groupId", String.valueOf(group.getGroupId()));
				rowURL.setParameter("privateLayout", Boolean.TRUE.toString());
			}
			%>

			<liferay-ui:search-container-column-text
				buffer="buffer"
				name="name"
				orderable="<%= true %>"
			>

			<%
			if (rowURL != null) {
				buffer.append("<a href=\"");
				buffer.append(rowURL.toString());
				buffer.append("\" target=\"_blank\"><strong>");
				buffer.append(HtmlUtil.escape(group.getDescriptiveName(locale)));
				buffer.append("</strong></a>");
			}
			else {
				buffer.append("<strong>");
				buffer.append(HtmlUtil.escape(group.getDescriptiveName(locale)));
				buffer.append("</strong>");
			}

			if (!tabs1.equals("my-sites") && Validator.isNotNull(group.getDescription())) {
				buffer.append("<br /><em>");
				buffer.append(group.getDescription());
				buffer.append("</em>");
			}
			%>

			</liferay-ui:search-container-column-text>

			<%
			LinkedHashMap userParams = new LinkedHashMap();

			userParams.put("inherit", true);
			userParams.put("usersGroups", new Long(group.getGroupId()));
			%>

			<liferay-ui:search-container-column-text
				name="members"
				value="<%= String.valueOf(UserLocalServiceUtil.searchCount(company.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED, userParams)) %>"
			/>

			<c:if test='<%= tabs1.equals("my-sites") && PropsValues.LIVE_USERS_ENABLED %>'>
				<liferay-ui:search-container-column-text
					name="online-now"
					value="<%= String.valueOf(LiveUsers.getGroupUsersCount(company.getCompanyId(), group.getGroupId())) %>"
				/>
			</c:if>

			<liferay-ui:search-container-column-text
				name="tags"
			>
				<liferay-ui:asset-tags-summary
					className="<%= Group.class.getName() %>"
					classPK="<%= group.getGroupId() %>"
				/>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/html/portlet/my_sites/site_action.jsp"
			/>

		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>