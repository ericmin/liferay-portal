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

package com.liferay.portlet.asset.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.base.AssetVocabularyServiceBaseImpl;
import com.liferay.portlet.asset.service.permission.AssetPermission;
import com.liferay.portlet.asset.service.permission.AssetVocabularyPermission;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Alvaro del Castillo
 * @author Eduardo Lundgren
 * @author Jorge Ferrer
 * @author Juan Fernández
 */
public class AssetVocabularyServiceImpl extends AssetVocabularyServiceBaseImpl {

	/**
	 * @deprecated
	 */
	public AssetVocabulary addVocabulary(
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String settings, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return addVocabulary(
			StringPool.BLANK, titleMap, descriptionMap, settings,
			serviceContext);
	}

	public AssetVocabulary addVocabulary(
			String title, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String settings,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		AssetPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_VOCABULARY);

		return assetVocabularyLocalService.addVocabulary(
			getUserId(), title, titleMap, descriptionMap, settings,
			serviceContext);
	}

	public void deleteVocabularies(long[] vocabularyIds)
		throws PortalException, SystemException {

		PermissionChecker permissionChecker = getPermissionChecker();

		for (long vocabularyId : vocabularyIds) {
			AssetVocabularyPermission.check(
				permissionChecker, vocabularyId, ActionKeys.DELETE);

			assetVocabularyLocalService.deleteVocabulary(vocabularyId);
		}
	}

	public void deleteVocabulary(long vocabularyId)
		throws PortalException, SystemException {

		AssetVocabularyPermission.check(
			getPermissionChecker(), vocabularyId, ActionKeys.DELETE);

		assetVocabularyLocalService.deleteVocabulary(vocabularyId);
	}

	public List<AssetVocabulary> getCompanyVocabularies(long companyId)
		throws PortalException, SystemException {

		return filterVocabularies(
			assetVocabularyLocalService.getCompanyVocabularies(companyId));
	}

	public List<AssetVocabulary> getGroupsVocabularies(long[] groupIds)
		throws PortalException, SystemException {

		return getGroupsVocabularies(groupIds, null);
	}

	public List<AssetVocabulary> getGroupsVocabularies(
			long[] groupIds, String className)
		throws PortalException, SystemException {

		return filterVocabularies(
			assetVocabularyLocalService.getGroupsVocabularies(
				groupIds, className));
	}

	public List<AssetVocabulary> getGroupVocabularies(long groupId)
		throws PortalException, SystemException {

		return filterVocabularies(
			assetVocabularyLocalService.getGroupVocabularies(groupId));
	}

	public List<AssetVocabulary> getGroupVocabularies(
			long groupId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return assetVocabularyPersistence.filterFindByGroupId(
			groupId, start, end, obc);
	}

	public List<AssetVocabulary> getGroupVocabularies(
			long groupId, String name, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return assetVocabularyFinder.filterFindByG_N(
			groupId, name, start, end, obc);
	}

	public int getGroupVocabulariesCount(long groupId) throws SystemException {
		return assetVocabularyPersistence.filterCountByGroupId(groupId);
	}

	public int getGroupVocabulariesCount(long groupId, String name)
		throws SystemException {

		return assetVocabularyFinder.filterCountByG_N(groupId, name);
	}

	public JSONObject getJSONGroupVocabularies(
			long groupId, String name, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		int page = end / (end - start);

		jsonObject.put("page", page);

		List<AssetVocabulary> vocabularies;
		int total = 0;

		if (Validator.isNotNull(name)) {
			name = (CustomSQLUtil.keywords(name))[0];

			vocabularies = getGroupVocabularies(groupId, name, start, end, obc);
			total = getGroupVocabulariesCount(groupId, name);
		}
		else {
			vocabularies = getGroupVocabularies(groupId, start, end, obc);
			total = getGroupVocabulariesCount(groupId);
		}

		String vocabulariesJSON = JSONFactoryUtil.looseSerialize(vocabularies);

		JSONArray vocabulariesJSONArray = JSONFactoryUtil.createJSONArray(
			vocabulariesJSON);

		jsonObject.put("vocabularies", vocabulariesJSONArray);

		jsonObject.put("total", total);

		return jsonObject;
	}

	public List<AssetVocabulary> getVocabularies(long[] vocabularyIds)
		throws PortalException, SystemException {

		return filterVocabularies(
			assetVocabularyLocalService.getVocabularies(vocabularyIds));
	}

	public AssetVocabulary getVocabulary(long vocabularyId)
		throws PortalException, SystemException {

		AssetVocabularyPermission.check(
			getPermissionChecker(), vocabularyId, ActionKeys.VIEW);

		return assetVocabularyLocalService.getVocabulary(vocabularyId);
	}

	/**
	 * @deprecated
	 */
	public AssetVocabulary updateVocabulary(
			long vocabularyId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String settings,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return updateVocabulary(
			vocabularyId, StringPool.BLANK, titleMap, descriptionMap, settings,
			serviceContext);
	}

	public AssetVocabulary updateVocabulary(
			long vocabularyId, String title, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String settings,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		AssetVocabularyPermission.check(
			getPermissionChecker(), vocabularyId, ActionKeys.UPDATE);

		return assetVocabularyLocalService.updateVocabulary(
			vocabularyId, title, titleMap, descriptionMap, settings,
			serviceContext);
	}

	protected List<AssetVocabulary> filterVocabularies(
			List<AssetVocabulary> vocabularies)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		vocabularies = ListUtil.copy(vocabularies);

		Iterator<AssetVocabulary> itr = vocabularies.iterator();

		while (itr.hasNext()) {
			AssetVocabulary vocabulary = itr.next();

			if (!AssetVocabularyPermission.contains(
					permissionChecker, vocabulary, ActionKeys.VIEW)) {

				itr.remove();
			}
		}

		return vocabularies;
	}

}