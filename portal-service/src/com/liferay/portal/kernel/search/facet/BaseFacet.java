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

package com.liferay.portal.kernel.search.facet;

import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.search.facet.util.BaseFacetValueValidator;
import com.liferay.portal.kernel.search.facet.util.FacetValueValidator;

/**
 * @author Raymond Augé
 */
public abstract class BaseFacet implements Facet {

	public BaseFacet(SearchContext searchContext) {
		_searchContext = searchContext;
	}

	public BooleanClause getFacetClause() {
		return doGetFacetClause();
	}

	public FacetCollector getFacetCollector() {
		return _facetCollector;
	}

	public FacetConfiguration getFacetConfiguration() {
		return _facetConfiguration;
	}

	public FacetValueValidator getFacetValueValidator() {
		if (_facetValueValidator == null) {
			_facetValueValidator = new BaseFacetValueValidator();
		}

		return _facetValueValidator;
	}

	public String getFieldName() {
		return _facetConfiguration.getFieldName();
	}

	public SearchContext getSearchContext() {
		return _searchContext;
	}

	public boolean isStatic() {
		return _facetConfiguration.isStatic();
	}

	public void setFacetCollector(FacetCollector facetCollector) {
		_facetCollector = facetCollector;
	}

	public void setFacetConfiguration(FacetConfiguration facetConfiguration) {
		_facetConfiguration = facetConfiguration;
	}

	public void setFacetValueValidator(
		FacetValueValidator facetValueValidator) {

		_facetValueValidator = facetValueValidator;
	}

	public void setFieldName(String fieldName) {
		_facetConfiguration.setFieldName(fieldName);
	}

	public void setStatic(boolean isStatic) {
		_facetConfiguration.setStatic(isStatic);
	}

	protected abstract BooleanClause doGetFacetClause();

	private FacetCollector _facetCollector;
	private FacetConfiguration _facetConfiguration = new FacetConfiguration();
	private FacetValueValidator _facetValueValidator;
	private SearchContext _searchContext;

}