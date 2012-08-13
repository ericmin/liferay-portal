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

package com.liferay.portal.spring.aop;

/**
 * @author Shuyang Zhou
 */
public class ChainableMethodAdviceInjector {

	public void afterPropertiesSet() {
		if (!isInjectCondition()) {
			return;
		}

		ChainableMethodAdvice newChainableMethodAdvice =
			getNewChainableMethodAdvice();

		if (newChainableMethodAdvice == null) {
			throw new IllegalArgumentException(
				"New Chainable method advice is null");
		}

		ChainableMethodAdvice parentChainableMethodAdvice =
			getParentChainableMethodAdvice();

		if (parentChainableMethodAdvice == null) {
			throw new IllegalArgumentException(
				"Parent chainable method advice is null");
		}

		newChainableMethodAdvice.nextMethodInterceptor =
			parentChainableMethodAdvice.nextMethodInterceptor;
		parentChainableMethodAdvice.nextMethodInterceptor =
			newChainableMethodAdvice;
	}

	public void setInjectCondition(boolean injectCondition) {
		_injectCondition = injectCondition;
	}

	public void setNewChainableMethodAdvice(
		ChainableMethodAdvice newChainableMethodAdvice) {

		_newChainableMethodAdvice = newChainableMethodAdvice;
	}

	public void setParentChainableMethodAdvice(
		ChainableMethodAdvice parentChainableMethodAdvice) {

		_parentChainableMethodAdvice = parentChainableMethodAdvice;
	}

	protected ChainableMethodAdvice getNewChainableMethodAdvice() {
		return _newChainableMethodAdvice;
	}

	protected ChainableMethodAdvice getParentChainableMethodAdvice() {
		return _parentChainableMethodAdvice;
	}

	protected boolean isInjectCondition() {
		return _injectCondition;
	}

	private boolean _injectCondition;
	private ChainableMethodAdvice _newChainableMethodAdvice;
	private ChainableMethodAdvice _parentChainableMethodAdvice;

}