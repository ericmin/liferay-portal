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

package com.liferay.portal.parsers.creole.ast.link;

/**
 * @author Miguel Pastor
 */
public abstract class InterwikiLinkNode extends LinkNode {

	public InterwikiLinkNode() {
	}

	public InterwikiLinkNode(int token) {
		super(token);
	}

	public InterwikiLinkNode(int token, String uri, String wikiName) {
		this(token);

		_uri = uri;
		_wikiName = wikiName;
	}

	public InterwikiLinkNode(String wikiname) {
		_wikiName = wikiname;
	}

	public InterwikiLinkNode(String uri, String wikiname) {
		_uri = uri;
		_wikiName = wikiname;
	}

	public String getUri() {
		return _uri;
	}

	public String getWikiName() {
		return _wikiName;
	}

	public void setUri(String uri) {
		_uri = uri;
	}

	private String _uri;
	private String _wikiName;

}