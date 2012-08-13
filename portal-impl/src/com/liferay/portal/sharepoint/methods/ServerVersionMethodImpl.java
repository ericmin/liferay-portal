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

package com.liferay.portal.sharepoint.methods;

import com.liferay.portal.sharepoint.Leaf;
import com.liferay.portal.sharepoint.Property;
import com.liferay.portal.sharepoint.ResponseElement;
import com.liferay.portal.sharepoint.SharepointRequest;
import com.liferay.portal.sharepoint.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Farache
 */
public class ServerVersionMethodImpl extends BaseMethodImpl {

	public ServerVersionMethodImpl() {
		Tree serverVersionTree = new Tree();

		serverVersionTree.addChild(new Leaf("major ver", "6", true));
		serverVersionTree.addChild(new Leaf("minor ver", "0", true));
		serverVersionTree.addChild(new Leaf("phase ver", "2", true));
		serverVersionTree.addChild(new Leaf("ver incr", "8117", true));

		Property serverVersionProperty = new Property(
			getMethodName(), serverVersionTree);

		_elements.add(serverVersionProperty);
		_elements.add(new Property("source control", "1"));
	}

	public String getMethodName() {
		return _METHOD_NAME;
	}

	@Override
	protected List<ResponseElement> getElements(
		SharepointRequest sharepointRequest) {

		return _elements;
	}

	private static final String _METHOD_NAME = "server version";

	private List<ResponseElement> _elements = new ArrayList<ResponseElement>();

}