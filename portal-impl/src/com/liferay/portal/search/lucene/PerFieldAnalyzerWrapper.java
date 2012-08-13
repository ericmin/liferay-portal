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

package com.liferay.portal.search.lucene;

import java.io.IOException;
import java.io.Reader;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Fieldable;

/**
 * @author Raymond Augé
 */
public class PerFieldAnalyzerWrapper
	extends org.apache.lucene.analysis.PerFieldAnalyzerWrapper {

	public PerFieldAnalyzerWrapper(
		Analyzer defaultAnalyzer, Map<String, Analyzer> analyzerMap) {

		super(defaultAnalyzer, analyzerMap);

		_analyzer = defaultAnalyzer;
		_analyzers = analyzerMap;
	}

	@Override
	public void addAnalyzer(String fieldName, Analyzer analyzer) {
		super.addAnalyzer(fieldName, analyzer);

		_analyzers.put(fieldName, analyzer);
	}

	public Analyzer getAnalyzer(String fieldName) {
		Analyzer analyzer = _analyzers.get(fieldName);

		if (analyzer != null) {
			return analyzer;
		}

		for (String key : _analyzers.keySet()) {
			if (Pattern.matches(key, fieldName)) {
				return _analyzers.get(key);
			}
		}

		return _analyzer;
	}

	@Override
	public int getOffsetGap(Fieldable field) {
		Analyzer analyzer = getAnalyzer(field.name());

		return analyzer.getOffsetGap(field);
	}

	@Override
	public int getPositionIncrementGap(String fieldName) {
		Analyzer analyzer = getAnalyzer(fieldName);

		return analyzer.getPositionIncrementGap(fieldName);
	}

	@Override
	public TokenStream reusableTokenStream(String fieldName, Reader reader)
		throws IOException {

		Analyzer analyzer = getAnalyzer(fieldName);

		return analyzer.reusableTokenStream(fieldName, reader);
	}

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		Analyzer analyzer = getAnalyzer(fieldName);

		return analyzer.tokenStream(fieldName, reader);
	}

	private Analyzer _analyzer;
	private Map<String, Analyzer> _analyzers = new HashMap<String, Analyzer>();

}