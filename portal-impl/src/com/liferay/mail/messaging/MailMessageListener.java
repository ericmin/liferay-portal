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

package com.liferay.mail.messaging;

import com.liferay.mail.util.HookFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.security.auth.EmailAddressGenerator;
import com.liferay.portal.security.auth.EmailAddressGeneratorFactory;
import com.liferay.portal.util.PropsValues;
import com.liferay.util.mail.MailEngine;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;

/**
 * @author Brian Wing Shun Chan
 * @author Wesley Gong
 * @author Zsolt Balogh
 */
public class MailMessageListener extends BaseMessageListener {

	protected void doMailMessage(MailMessage mailMessage) throws Exception {
		InternetAddress[] auditTrail = InternetAddress.parse(
			PropsValues.MAIL_AUDIT_TRAIL);

		if (auditTrail.length > 0) {
			InternetAddress[] bcc = mailMessage.getBCC();

			if (bcc != null) {
				InternetAddress[] allBCC = new InternetAddress[
					bcc.length + auditTrail.length];

				ArrayUtil.combine(bcc, auditTrail, allBCC);

				mailMessage.setBCC(allBCC);
			}
			else {
				mailMessage.setBCC(auditTrail);
			}
		}

		InternetAddress from = filterInternetAddress(mailMessage.getFrom());

		if (from == null) {
			return;
		}
		else {
			mailMessage.setFrom(from);
		}

		InternetAddress[] to = filterInternetAddresses(mailMessage.getTo());

		mailMessage.setTo(to);

		InternetAddress[] cc = filterInternetAddresses(mailMessage.getCC());

		mailMessage.setCC(cc);

		InternetAddress[] bcc = filterInternetAddresses(mailMessage.getBCC());

		mailMessage.setBCC(bcc);

		InternetAddress[] bulkAddresses = filterInternetAddresses(
			mailMessage.getBulkAddresses());

		mailMessage.setBulkAddresses(bulkAddresses);

		if (((to != null) && (to.length > 0)) ||
			((cc != null) && (cc.length > 0)) ||
			((bcc != null) && (bcc.length > 0)) ||
			((bulkAddresses != null) && (bulkAddresses.length > 0))) {

			MailEngine.send(mailMessage);
		}
	}

	protected void doMethodHandler(MethodHandler methodHandler)
		throws Exception {

		methodHandler.invoke(HookFactory.getInstance());
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		Object payload = message.getPayload();

		if (payload instanceof MailMessage) {
			doMailMessage((MailMessage)payload);
		}
		else if (payload instanceof MethodHandler) {
			doMethodHandler((MethodHandler)payload);
		}
	}

	protected InternetAddress filterInternetAddress(
		InternetAddress internetAddress) {

		EmailAddressGenerator emailAddressGenerator =
			EmailAddressGeneratorFactory.getInstance();

		if (emailAddressGenerator.isFake(internetAddress.getAddress())) {
			return null;
		}

		String address = internetAddress.toString();

		for (char c : address.toCharArray()) {
			if ((c == CharPool.NEW_LINE) || (c == CharPool.RETURN)) {
				if (_log.isWarnEnabled()) {
					StringBundler sb = new StringBundler(4);

					sb.append("Email address ");
					sb.append(address);
					sb.append(" contains line break characters and will be ");
					sb.append("excluded from the email");

					_log.warn(sb.toString());
				}

				return null;
			}
		}

		return internetAddress;
	}

	protected InternetAddress[] filterInternetAddresses(
		InternetAddress[] internetAddresses) {

		if (internetAddresses == null) {
			return null;
		}

		List<InternetAddress> filteredInternetAddresses =
			new ArrayList<InternetAddress>(internetAddresses.length);

		for (InternetAddress internetAddress : internetAddresses) {
			InternetAddress filteredInternetAddress = filterInternetAddress(
				internetAddress);

			if (filteredInternetAddress != null) {
				filteredInternetAddresses.add(filteredInternetAddress);
			}
		}

		return filteredInternetAddresses.toArray(
			new InternetAddress[filteredInternetAddresses.size()]);
	}

	private static Log _log = LogFactoryUtil.getLog(MailMessageListener.class);

}