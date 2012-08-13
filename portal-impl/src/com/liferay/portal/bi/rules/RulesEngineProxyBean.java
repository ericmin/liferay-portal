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

package com.liferay.portal.bi.rules;

import com.liferay.portal.kernel.bi.rules.Fact;
import com.liferay.portal.kernel.bi.rules.Query;
import com.liferay.portal.kernel.bi.rules.RulesEngine;
import com.liferay.portal.kernel.bi.rules.RulesResourceRetriever;
import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class RulesEngineProxyBean extends BaseProxyBean implements RulesEngine {

	public void add(
		String domainName, RulesResourceRetriever RulesResourceRetriever,
		ClassLoader... clientClassLoaders) {

		throw new UnsupportedOperationException();
	}

	public boolean containsRuleDomain(String domainName) {
		throw new UnsupportedOperationException();
	}

	public void execute(
		RulesResourceRetriever RulesResourceRetriever, List<Fact<?>> facts,
		ClassLoader... clientClassLoaders) {

		throw new UnsupportedOperationException();
	}

	public Map<String, ?> execute(
		RulesResourceRetriever RulesResourceRetriever, List<Fact<?>> facts,
		Query query, ClassLoader... clientClassLoaders) {

		throw new UnsupportedOperationException();
	}

	public void execute(
		String domainName, List<Fact<?>> facts,
		ClassLoader... clientClassLoaders) {

		throw new UnsupportedOperationException();
	}

	public Map<String, ?> execute(
		String domainName, List<Fact<?>> facts, Query query,
		ClassLoader... clientClassLoaders) {

		throw new UnsupportedOperationException();
	}

	public void remove(String domainName) {
		throw new UnsupportedOperationException();
	}

	public void update(
		String domainName, RulesResourceRetriever RulesResourceRetriever,
		ClassLoader... clientClassLoaders) {

		throw new UnsupportedOperationException();
	}

}