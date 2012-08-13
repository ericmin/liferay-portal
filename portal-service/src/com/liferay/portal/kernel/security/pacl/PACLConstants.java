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

package com.liferay.portal.kernel.security.pacl;

/**
 * @author Raymond Augé
 */
public interface PACLConstants {

	public static final String FILE_PERMISSION_ACTION_DELETE = "delete";

	public static final String FILE_PERMISSION_ACTION_EXECUTE = "execute";

	public static final String FILE_PERMISSION_ACTION_READ = "read";

	public static final String FILE_PERMISSION_ACTION_WRITE = "write";

	public static final String MBEAN_PERMISSION_IS_INSTANCE_OF = "isInstanceOf";

	public static final String MBEAN_PERMISSION_REGISTER_MBEAN =
		"registerMBean";

	public static final String MBEAN_TRUST_PERMISSION_REGISTER = "register";

	public static final String NET_PERMISSION_GET_PROXY_SELECTOR =
		"getProxySelector";

	public static final String NET_PERMISSION_SPECIFY_STREAM_HANDLER =
		"specifyStreamHandler";

	public static final String PORTAL_HOOK_PERMISSION_CUSTOM_JSP_DIR =
		"customJspDir";

	public static final String PORTAL_HOOK_PERMISSION_INDEXER = "hasIndexer";

	public static final String
		PORTAL_HOOK_PERMISSION_LANGUAGE_PROPERTIES_LOCALE =
			"languagePropertiesLocale";

	public static final String PORTAL_HOOK_PERMISSION_PORTAL_PROPERTIES_KEY =
		"hasPortalPropertiesKey";

	public static final String PORTAL_HOOK_PERMISSION_SERVICE = "service";

	public static final String PORTAL_HOOK_PERMISSION_SERVLET_FILTERS =
		"servletFilters";

	public static final String PORTAL_HOOK_PERMISSION_STRUTS_ACTION_PATH =
		"strutsActionPath";

	public static final String PORTAL_MESSAGE_BUS_PERMISSION_LISTEN = "listen";

	public static final String PORTAL_MESSAGE_BUS_PERMISSION_SEND = "send";

	public static final String PORTAL_RUNTIME_PERMISSION_EXPANDO_BRIDGE =
		"expandoBridge";

	public static final String PORTAL_RUNTIME_PERMISSION_GET_BEAN_PROPERTY =
		"getBeanProperty";

	public static final String PORTAL_RUNTIME_PERMISSION_SEARCH_ENGINE =
		"searchEngine";

	public static final String PORTAL_RUNTIME_PERMISSION_SET_BEAN_PROPERTY =
		"setBeanProperty";

	public static final String PORTAL_RUNTIME_PERMISSION_THREAD_POOL_EXECUTOR =
		"threadPoolExecutor";

	public static final String PORTAL_SERVICE_PERMISSION_DYNAMIC_QUERY =
		"dynamicQuery";

	public static final String PORTAL_SERVICE_PERMISSION_SERVICE = "service";

	public static final String RUNTIME_PERMISSION_ACCESS_CLASS_IN_PACKAGE =
		"accessClassInPackage";

	public static final String RUNTIME_PERMISSION_ACCESS_DECLARED_MEMBERS =
		"accessDeclaredMembers";

	public static final String RUNTIME_PERMISSION_CREATE_CLASS_LOADER =
		"createClassLoader";

	public static final String RUNTIME_PERMISSION_GET_CLASSLOADER =
		"getClassLoader";

	public static final String RUNTIME_PERMISSION_GET_ENV = "getenv";

	public static final String RUNTIME_PERMISSION_GET_PROTECTION_DOMAIN =
		"getProtectionDomain";

	public static final String RUNTIME_PERMISSION_READ_FILE_DESCRIPTOR =
		"readFileDescriptor";

	public static final String RUNTIME_PERMISSION_SET_CONTEXT_CLASS_LOADER =
		"setContextClassLoader";

	public static final String RUNTIME_PERMISSION_SET_SECURITY_MANAGER =
		"setSecurityManager";

	public static final String RUNTIME_PERMISSION_WRITE_FILE_DESCRIPTOR =
		"writeFileDescriptor";

	public static final String SECURITY_PERMISSION_GET_POLICY = "getPolicy";

	public static final String SECURITY_PERMISSION_SET_POLICY = "setPolicy";

	public static final String SOCKET_PERMISSION_ACCEPT = "accept";

	public static final String SOCKET_PERMISSION_CONNECT = "connect";

	public static final String SOCKET_PERMISSION_LISTEN = "listen";

}