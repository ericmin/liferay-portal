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

package com.liferay.portlet.messageboards.model.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListTree;
import com.liferay.portal.kernel.util.TreeNode;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.model.MBCategoryDisplay;
import com.liferay.portlet.messageboards.service.MBCategoryServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class MBCategoryDisplayImpl implements MBCategoryDisplay {

	public MBCategoryDisplayImpl(long scopeGroupId, long categoryId) {
		try {
			init(scopeGroupId, categoryId);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public List<MBCategory> getAllCategories() {
		return _allCategories;
	}

	public int getAllCategoriesCount() {
		return _allCategories.size();
	}

	public List<MBCategory> getCategories() {
		return _categoryTree.getRootNode().getChildValues();
	}

	public List<MBCategory> getCategories(MBCategory category) {
		TreeNode<MBCategory> node = _categoryNodesMap.get(
			category.getCategoryId());

		return node.getChildValues();
	}

	public MBCategory getRootCategory() {
		return _categoryTree.getRootNode().getValue();
	}

	public int getSubcategoriesCount(MBCategory category) {
		TreeNode<MBCategory> node = _categoryNodesMap.get(
			category.getCategoryId());

		return _categoryTree.getChildNodes(node).size();
	}

	public int getSubcategoriesMessagesCount(MBCategory category) {
		int count = category.getMessageCount();

		TreeNode<MBCategory> node = _categoryNodesMap.get(
			category.getCategoryId());

		List<TreeNode<MBCategory>> childNodes = _categoryTree.getChildNodes(
			node);

		for (TreeNode<MBCategory> curNode : childNodes) {
			MBCategory curCategory = curNode.getValue();

			count += curCategory.getMessageCount();
		}

		return count;
	}

	public int getSubcategoriesThreadsCount(MBCategory category) {
		int count = category.getThreadCount();

		TreeNode<MBCategory> node = _categoryNodesMap.get(
			category.getCategoryId());

		List<TreeNode<MBCategory>> childNodes = _categoryTree.getChildNodes(
			node);

		for (TreeNode<MBCategory> curNode : childNodes) {
			MBCategory curCategory = curNode.getValue();

			count += curCategory.getThreadCount();
		}

		return count;
	}

	public void getSubcategoryIds(MBCategory category, List<Long> categoryIds) {
		List<MBCategory> categories = getCategories(category);

		for (MBCategory curCategory : categories) {
			categoryIds.add(curCategory.getCategoryId());

			getSubcategoryIds(curCategory, categoryIds);
		}
	}

	protected void init(long scopeGroupId, long categoryId) throws Exception {
		_allCategories = MBCategoryServiceUtil.getCategories(scopeGroupId);

		_rootCategory = new MBCategoryImpl();

		_rootCategory.setCategoryId(categoryId);

		_categoryTree = new ListTree<MBCategory>(_rootCategory);

		_categoryNodesMap = new HashMap<Long, TreeNode<MBCategory>>();

		Map<Long, List<MBCategory>> categoriesMap =
			new HashMap<Long, List<MBCategory>>();

		for (MBCategory category : _allCategories) {
			Long parentCategoryId = category.getParentCategoryId();

			List<MBCategory> curCategories = categoriesMap.get(
				parentCategoryId);

			if (curCategories == null) {
				curCategories = new ArrayList<MBCategory>();

				categoriesMap.put(parentCategoryId, curCategories);
			}

			curCategories.add(category);
		}

		populateCategoryNodesMap(_categoryTree.getRootNode(), categoriesMap);
	}

	protected void populateCategoryNodesMap(
		TreeNode<MBCategory> node, Map<Long, List<MBCategory>> categoriesMap) {

		MBCategory category = node.getValue();

		if (category.getCategoryId() ==
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {

			_categoryNodesMap.put(category.getCategoryId(), node);
		}

		List<MBCategory> categories = categoriesMap.get(
			category.getCategoryId());

		if (categories == null) {
			return;
		}

		for (MBCategory curCategory : categories) {
			TreeNode<MBCategory> curNode = node.addChildNode(curCategory);

			_categoryNodesMap.put(curCategory.getCategoryId(), curNode);

			populateCategoryNodesMap(curNode, categoriesMap);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		MBCategoryDisplayImpl.class);

	private List<MBCategory> _allCategories;
	private Map<Long, TreeNode<MBCategory>> _categoryNodesMap;
	private ListTree<MBCategory> _categoryTree;
	private MBCategory _rootCategory;

}