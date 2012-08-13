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

package com.liferay.portal.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.PortalServiceUtil;

/**
 * <p>
 * This class provides a HTTP utility for the
 * {@link com.liferay.portal.service.PortalServiceUtil} service utility. The
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
 * @see       PortalServiceSoap
 * @see       com.liferay.portal.security.auth.HttpPrincipal
 * @see       com.liferay.portal.service.PortalServiceUtil
 * @generated
 */
public class PortalServiceHttp {
	public static java.lang.String getAutoDeployDirectory(
		HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(PortalServiceUtil.class.getName(),
					"getAutoDeployDirectory",
					_getAutoDeployDirectoryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey);

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

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getBuildNumber(HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(PortalServiceUtil.class.getName(),
					"getBuildNumber", _getBuildNumberParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void testAddClassName_Rollback(HttpPrincipal httpPrincipal,
		java.lang.String classNameValue)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(PortalServiceUtil.class.getName(),
					"testAddClassName_Rollback",
					_testAddClassName_RollbackParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					classNameValue);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void testAddClassName_Success(HttpPrincipal httpPrincipal,
		java.lang.String classNameValue)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(PortalServiceUtil.class.getName(),
					"testAddClassName_Success",
					_testAddClassName_SuccessParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					classNameValue);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void testAddClassNameAndTestTransactionPortletBar_PortalRollback(
		HttpPrincipal httpPrincipal, java.lang.String transactionPortletBarText)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(PortalServiceUtil.class.getName(),
					"testAddClassNameAndTestTransactionPortletBar_PortalRollback",
					_testAddClassNameAndTestTransactionPortletBar_PortalRollbackParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					transactionPortletBarText);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void testAddClassNameAndTestTransactionPortletBar_PortletRollback(
		HttpPrincipal httpPrincipal, java.lang.String transactionPortletBarText)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(PortalServiceUtil.class.getName(),
					"testAddClassNameAndTestTransactionPortletBar_PortletRollback",
					_testAddClassNameAndTestTransactionPortletBar_PortletRollbackParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					transactionPortletBarText);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void testAddClassNameAndTestTransactionPortletBar_Success(
		HttpPrincipal httpPrincipal, java.lang.String transactionPortletBarText)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(PortalServiceUtil.class.getName(),
					"testAddClassNameAndTestTransactionPortletBar_Success",
					_testAddClassNameAndTestTransactionPortletBar_SuccessParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					transactionPortletBarText);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void testCounterIncrement_Rollback(
		HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(PortalServiceUtil.class.getName(),
					"testCounterIncrement_Rollback",
					_testCounterIncrement_RollbackParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void testDeleteClassName(HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(PortalServiceUtil.class.getName(),
					"testDeleteClassName", _testDeleteClassNameParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
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
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int testGetBuildNumber(HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(PortalServiceUtil.class.getName(),
					"testGetBuildNumber", _testGetBuildNumberParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void testGetUserId(HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(PortalServiceUtil.class.getName(),
					"testGetUserId", _testGetUserIdParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static boolean testHasClassName(HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(PortalServiceUtil.class.getName(),
					"testHasClassName", _testHasClassNameParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey);

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

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PortalServiceHttp.class);
	private static final Class<?>[] _getAutoDeployDirectoryParameterTypes0 = new Class[] {
			
		};
	private static final Class<?>[] _getBuildNumberParameterTypes1 = new Class[] {
			
		};
	private static final Class<?>[] _testAddClassName_RollbackParameterTypes2 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _testAddClassName_SuccessParameterTypes3 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _testAddClassNameAndTestTransactionPortletBar_PortalRollbackParameterTypes4 =
		new Class[] { java.lang.String.class };
	private static final Class<?>[] _testAddClassNameAndTestTransactionPortletBar_PortletRollbackParameterTypes5 =
		new Class[] { java.lang.String.class };
	private static final Class<?>[] _testAddClassNameAndTestTransactionPortletBar_SuccessParameterTypes6 =
		new Class[] { java.lang.String.class };
	private static final Class<?>[] _testCounterIncrement_RollbackParameterTypes7 =
		new Class[] {  };
	private static final Class<?>[] _testDeleteClassNameParameterTypes8 = new Class[] {
			
		};
	private static final Class<?>[] _testGetBuildNumberParameterTypes9 = new Class[] {
			
		};
	private static final Class<?>[] _testGetUserIdParameterTypes10 = new Class[] {
			
		};
	private static final Class<?>[] _testHasClassNameParameterTypes11 = new Class[] {
			
		};
}