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

package com.liferay.taglib.aui;

import com.liferay.taglib.aui.base.BaseToolTag;

/**
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 */
public class ToolTag extends BaseToolTag {

	@Override
	public int doStartTag() {
		PanelTag panelTag = (PanelTag)findAncestorWithClass(
			this, PanelTag.class);

		panelTag.addToolTag(this);

		return EVAL_PAGE;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();
	}

}