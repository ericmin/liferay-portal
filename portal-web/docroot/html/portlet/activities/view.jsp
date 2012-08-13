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

<%@ include file="/html/portlet/activities/init.jsp" %>

<%
Group group = GroupLocalServiceUtil.getGroup(scopeGroupId);

List<SocialActivity> activities = null;

if (group.isOrganization()) {
	activities = SocialActivityLocalServiceUtil.getOrganizationActivities(group.getOrganizationId(), 0, max);
}
else if (group.isRegularSite()) {
	activities = SocialActivityLocalServiceUtil.getGroupActivities(group.getGroupId(), 0, max);
}
else if (group.isUser()) {
	activities = SocialActivityLocalServiceUtil.getUserActivities(group.getClassPK(), 0, max);
}

ResourceURL rssURL = liferayPortletResponse.createResourceURL();

rssURL.setCacheability(ResourceURL.FULL);
rssURL.setParameter("struts_action", "/activities/rss");

String taglibFeedTitle = LanguageUtil.format(pageContext, "subscribe-to-x's-activities", group.getDescriptiveName(locale));
String taglibFeedLinkMessage = LanguageUtil.format(pageContext, "subscribe-to-x's-activities", group.getDescriptiveName(locale));
%>

<liferay-ui:social-activities
	activities="<%= activities %>"
	feedEnabled="<%= true %>"
	feedLink="<%= rssURL.toString() %>"
	feedLinkMessage="<%= HtmlUtil.escape(taglibFeedLinkMessage) %>"
	feedTitle="<%= HtmlUtil.escape(taglibFeedTitle) %>"
/>