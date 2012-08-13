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

package com.liferay.portal.parsers.creole.ast;

import com.liferay.portal.parsers.creole.visitor.ASTVisitor;

/**
 * @author Miguel Pastor
 */
public class NoWikiSectionNode extends ASTNode {

	public NoWikiSectionNode(int tokenType) {
		this(tokenType, null);
	}

	public NoWikiSectionNode(int tokenType, String content) {
		super(tokenType);

		_content = content;
	}

	public NoWikiSectionNode(String content) {
		_content = content;
	}

	@Override
	public void accept(ASTVisitor astVisitor) {
		astVisitor.visit(this);
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	private String _content;

}