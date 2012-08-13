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

package com.liferay.portlet.asset.action;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.JSONAction;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetCategoryConstants;
import com.liferay.portlet.asset.service.AssetCategoryServiceUtil;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Eduardo Lundgren
 */
public class GetCategoriesAction extends JSONAction {

	@Override
	public String getJSON(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<AssetCategory> categories = getCategories(request);

		for (AssetCategory category : categories) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			List<AssetCategory> childCategories =
				AssetCategoryServiceUtil.getChildCategories(
					category.getCategoryId());

			jsonObject.put("categoryId", category.getCategoryId());
			jsonObject.put("hasChildren", !childCategories.isEmpty());
			jsonObject.put("name", category.getName());
			jsonObject.put("parentCategoryId", category.getParentCategoryId());
			jsonObject.put(
				"titleCurrentValue", category.getTitleCurrentValue());

			jsonArray.put(jsonObject);
		}

		return jsonArray.toString();
	}

	protected List<AssetCategory> getCategories(HttpServletRequest request)
		throws Exception {

		long categoryId = ParamUtil.getLong(request, "categoryId");
		long vocabularyId = ParamUtil.getLong(request, "vocabularyId");
		int start = ParamUtil.getInteger(request, "start", QueryUtil.ALL_POS);
		int end = ParamUtil.getInteger(request, "end", QueryUtil.ALL_POS);

		List<AssetCategory> categories = Collections.emptyList();

		if (categoryId > 0) {
			categories = AssetCategoryServiceUtil.getChildCategories(
				categoryId, start, end, null);
		}
		else if (vocabularyId > 0) {
			long parentCategoryId = ParamUtil.getLong(
				request, "parentCategoryId",
				AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);

			categories = AssetCategoryServiceUtil.getVocabularyCategories(
				parentCategoryId, vocabularyId, start, end, null);
		}

		return categories;
	}

}