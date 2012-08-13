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

package com.liferay.util.bridges.jsf.sun;

import com.sun.faces.renderkit.RenderKitFactoryImpl;

import java.util.HashMap;

import javax.faces.render.RenderKitFactory;

/**
 * @author Brian Myunghun Kim
 */
public class LiferayRenderKitFactoryImpl extends RenderKitFactoryImpl {

	public LiferayRenderKitFactoryImpl() {
		super();

		renderKits = new HashMap<String, RenderKitFactory>();

		addRenderKit(HTML_BASIC_RENDER_KIT, new LiferayRenderKitImpl());
	}

}