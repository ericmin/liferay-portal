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

package com.liferay.portlet.asset.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.http.TunnelUtil;

import com.liferay.portlet.asset.service.AssetEntryServiceUtil;

/**
 * <p>
 * This class provides a HTTP utility for the
 * {@link com.liferay.portlet.asset.service.AssetEntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link com.liferay.portal.security.auth.HttpPrincipal} parameter.
 * </p>
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetEntryServiceSoap
 * @see       com.liferay.portal.security.auth.HttpPrincipal
 * @see       com.liferay.portlet.asset.service.AssetEntryServiceUtil
 * @generated
 */
public class AssetEntryServiceHttp {
	public static java.util.List<com.liferay.portlet.asset.model.AssetEntry> getCompanyEntries(
		HttpPrincipal httpPrincipal, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(AssetEntryServiceUtil.class.getName(),
					"getCompanyEntries", _getCompanyEntriesParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portlet.asset.model.AssetEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCompanyEntriesCount(HttpPrincipal httpPrincipal,
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(AssetEntryServiceUtil.class.getName(),
					"getCompanyEntriesCount",
					_getCompanyEntriesCountParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, companyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetEntry> getEntries(
		HttpPrincipal httpPrincipal,
		com.liferay.portlet.asset.service.persistence.AssetEntryQuery entryQuery)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(AssetEntryServiceUtil.class.getName(),
					"getEntries", _getEntriesParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					entryQuery);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portlet.asset.model.AssetEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getEntriesCount(HttpPrincipal httpPrincipal,
		com.liferay.portlet.asset.service.persistence.AssetEntryQuery entryQuery)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(AssetEntryServiceUtil.class.getName(),
					"getEntriesCount", _getEntriesCountParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					entryQuery);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portlet.asset.model.AssetEntry getEntry(
		HttpPrincipal httpPrincipal, long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(AssetEntryServiceUtil.class.getName(),
					"getEntry", _getEntryParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, entryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portlet.asset.model.AssetEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portlet.asset.model.AssetEntry incrementViewCounter(
		HttpPrincipal httpPrincipal, java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(AssetEntryServiceUtil.class.getName(),
					"incrementViewCounter", _incrementViewCounterParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					className, classPK);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portlet.asset.model.AssetEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portlet.asset.model.AssetEntry updateEntry(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String className,
		long classPK, java.lang.String classUuid, long classTypeId,
		long[] categoryIds, java.lang.String[] tagNames, boolean visible,
		java.util.Date startDate, java.util.Date endDate,
		java.util.Date publishDate, java.util.Date expirationDate,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String summary,
		java.lang.String url, java.lang.String layoutUuid, int height,
		int width, java.lang.Integer priority, boolean sync)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(AssetEntryServiceUtil.class.getName(),
					"updateEntry", _updateEntryParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					className, classPK, classUuid, classTypeId, categoryIds,
					tagNames, visible, startDate, endDate, publishDate,
					expirationDate, mimeType, title, description, summary, url,
					layoutUuid, height, width, priority, sync);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portlet.asset.model.AssetEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AssetEntryServiceHttp.class);
	private static final Class<?>[] _getCompanyEntriesParameterTypes0 = new Class[] {
			long.class, int.class, int.class
		};
	private static final Class<?>[] _getCompanyEntriesCountParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getEntriesParameterTypes2 = new Class[] {
			com.liferay.portlet.asset.service.persistence.AssetEntryQuery.class
		};
	private static final Class<?>[] _getEntriesCountParameterTypes3 = new Class[] {
			com.liferay.portlet.asset.service.persistence.AssetEntryQuery.class
		};
	private static final Class<?>[] _getEntryParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _incrementViewCounterParameterTypes5 = new Class[] {
			java.lang.String.class, long.class
		};
	private static final Class<?>[] _updateEntryParameterTypes6 = new Class[] {
			long.class, java.lang.String.class, long.class,
			java.lang.String.class, long.class, long[].class,
			java.lang.String[].class, boolean.class, java.util.Date.class,
			java.util.Date.class, java.util.Date.class, java.util.Date.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, int.class, int.class,
			java.lang.Integer.class, boolean.class
		};
}