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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.QueryTranslator;
import com.liferay.portal.kernel.search.StringQueryImpl;
import com.liferay.portal.kernel.util.StringPool;

import java.lang.reflect.Field;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;

/**
 * @author Brian Wing Shun Chan
 */
public class QueryTranslatorImpl implements QueryTranslator {

	public Object translate(Query query) throws ParseException {
		if (query instanceof BooleanQueryImpl) {
			return ((BooleanQueryImpl)query).getBooleanQuery();
		}
		else if (query instanceof LuceneQueryImpl) {
			return ((LuceneQueryImpl)query).getQuery();
		}
		else if (query instanceof StringQueryImpl) {
			QueryParser queryParser = new QueryParser(
				LuceneHelperUtil.getVersion(), StringPool.BLANK,
				LuceneHelperUtil.getAnalyzer());

			try {
				return queryParser.parse(query.toString());
			}
			catch (org.apache.lucene.queryParser.ParseException pe) {
				throw new ParseException(pe);
			}
		}
		else if (query instanceof TermQueryImpl) {
			return ((TermQueryImpl)query).getTermQuery();
		}
		else if (query instanceof TermRangeQueryImpl) {
			return ((TermRangeQueryImpl)query).getTermRangeQuery();
		}
		else {
			return null;
		}
	}

	public Object translateForSolr(Query query) {
		Object queryObject = query.getWrappedQuery();

		if (queryObject instanceof org.apache.lucene.search.Query) {
			adjustQuery((org.apache.lucene.search.Query)queryObject);
		}

		return query;
	}

	protected void adjustQuery(org.apache.lucene.search.Query query) {
		if (query instanceof BooleanQuery) {
			BooleanQuery booleanQuery = (BooleanQuery)query;

			for (BooleanClause booleanClause : booleanQuery.getClauses()) {
				adjustQuery(booleanClause.getQuery());
			}
		}
		else if (query instanceof TermQuery) {
			TermQuery termQuery = (TermQuery)query;

			Term term = termQuery.getTerm();

			try {
				String text = term.text();

				if (text.matches("^\\s*[^\"].*\\s+.*[^\"]\\s*$(?m)")) {
					text = StringPool.QUOTE.concat(text).concat(
						StringPool.QUOTE);

					_textField.set(term, text);
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		else if (query instanceof WildcardQuery) {
			WildcardQuery wildcardQuery = (WildcardQuery)query;

			Term term = wildcardQuery.getTerm();

			try {
				String text = term.text();

				if (text.matches("^\\s*\\*.*(?m)")) {
					text = text.replaceFirst("\\*", StringPool.BLANK);

					_textField.set(term, text);
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(QueryTranslatorImpl.class);

	private static Field _textField = null;

	static {
		try {
			_textField = Term.class.getDeclaredField("text");

			_textField.setAccessible(true);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

}