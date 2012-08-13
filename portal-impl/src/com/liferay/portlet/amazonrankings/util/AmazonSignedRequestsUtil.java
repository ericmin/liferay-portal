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

package com.liferay.portlet.amazonrankings.util;

import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.net.URLEncoder;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import jodd.util.StringPool;

/**
 * @author Barrie Selack
 * @author Brian Wing Shun Chan
 */
public class AmazonSignedRequestsUtil {

	public static String generateUrlWithSignature(
			Map<String, String> parameters)
		throws Exception {

		String canonicalizedParameters = _canonicalizeParameters(parameters);

		String signature = _generateSignature(
			"GET\necs.amazonaws.com\n/onca/xml\n" + canonicalizedParameters);

		return "http://ecs.amazonaws.com/onca/xml?" + canonicalizedParameters +
			"&Signature=" + signature;
	}

	private static String _canonicalizeParameters(
			Map<String, String> parameters)
		throws Exception {

		if (parameters.isEmpty()) {
			return StringPool.EMPTY;
		}

		StringBundler sb = new StringBundler();

		parameters = new TreeMap<String, String>(parameters);

		Set<Map.Entry<String, String>> parametersSet = parameters.entrySet();

		Iterator<Map.Entry<String, String>> itr = parametersSet.iterator();

		while (itr.hasNext()) {
			Map.Entry<String, String> parameter = itr.next();

			sb.append(_rfc3986Encode(parameter.getKey()));
			sb.append(StringPool.EQUALS);
			sb.append(_rfc3986Encode(parameter.getValue()));

			if (itr.hasNext()) {
				sb.append(StringPool.AMPERSAND);
			}
		}

		return sb.toString();
	}

	private static String _generateSignature(String data) throws Exception {
		String amazonSecretAccessKey =
			AmazonRankingsUtil.getAmazonSecretAccessKey();

		SecretKeySpec secretKeySpec = new SecretKeySpec(
			amazonSecretAccessKey.getBytes(), _HMAC_SHA256_ALGORITHM);

		Mac mac = Mac.getInstance(_HMAC_SHA256_ALGORITHM);

		mac.init(secretKeySpec);

		byte[] bytes = mac.doFinal(data.getBytes());

		String signature = Base64.encode(bytes);

		return StringUtil.replace(
			signature, new String[] {StringPool.EQUALS, StringPool.PLUS},
			new String[] {"%3D", "%2B"});
	}

	private static String _rfc3986Encode(String string) throws Exception {
		string = URLEncoder.encode(string, StringPool.UTF_8);

		string = StringUtil.replace(
			string, new String[] {StringPool.ASTERISK, StringPool.PLUS, "%7E"},
			new String[] {"%2A", "%2B", "~"});

		return string;
	}

	private static final String _HMAC_SHA256_ALGORITHM = "HmacSHA256";

}