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

package com.liferay.portal.search.lucene.dump;

import com.liferay.portal.search.lucene.IndexAccessorImpl;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.ExecutionTestListeners;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.PropsValues;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Shuyang Zhou
 * @author Mate Thurzo
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class IndexAccessorImplTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		_documentsCount = PropsValues.LUCENE_COMMIT_BATCH_SIZE;

		if (_documentsCount == 0) {
			_documentsCount = 1;
		}

		_indexAccessorImpl = new IndexAccessorImpl(_TEST_COMPANY_ID);
	}

	@After
	public void tearDown() throws Exception {
		System.gc();

		_indexAccessorImpl.delete();
		_indexAccessorImpl.close();
	}

	@Test
	public void testEmptyDump() throws Exception {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		_indexAccessorImpl.dumpIndex(byteArrayOutputStream);

		_indexAccessorImpl.loadIndex(
			new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
	}

	@Test
	public void testOneCommitDump() throws Exception {
		_addDocuments("test");

		_assertHits("test", true);

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		_indexAccessorImpl.dumpIndex(byteArrayOutputStream);

		_indexAccessorImpl.loadIndex(
			new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));

		_assertHits("test", true);
	}

	@Test
	public void testThreeCommitsDump() throws Exception {
		_addDocuments("test1");
		_addDocuments("test2");
		_addDocuments("test3");

		_assertHits("test1", true);
		_assertHits("test2", true);
		_assertHits("test3", true);

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		_indexAccessorImpl.dumpIndex(byteArrayOutputStream);

		_indexAccessorImpl.loadIndex(
			new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));

		_assertHits("test1", true);
		_assertHits("test2", true);
		_assertHits("test3", true);
	}

	@Test
	public void testThreeCommitsOneDeletionDump() throws Exception {
		_addDocuments("test1");
		_addDocuments("test2");
		_addDocuments("test3");

		_deleteDocuments("test2");

		_assertHits("test1", true);
		_assertHits("test2", false);
		_assertHits("test3", true);

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		_indexAccessorImpl.dumpIndex(byteArrayOutputStream);

		_indexAccessorImpl.loadIndex(
			new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));

		_assertHits("test1", true);
		_assertHits("test2", false);
		_assertHits("test3", true);
	}

	@Test
	public void testThreeCommitsTwoDeletionsDump() throws Exception {
		_addDocuments("test1");
		_addDocuments("test2");
		_addDocuments("test3");

		_deleteDocuments("test2");
		_deleteDocuments("test3");

		_assertHits("test1", true);
		_assertHits("test2", false);
		_assertHits("test3", false);

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		_indexAccessorImpl.dumpIndex(byteArrayOutputStream);

		_indexAccessorImpl.loadIndex(
			new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));

		_assertHits("test1", true);
		_assertHits("test2", false);
		_assertHits("test3", false);
	}

	@Test
	public void testTwoCommitsDump() throws Exception {
		_addDocuments("test1");
		_addDocuments("test2");

		_assertHits("test1", true);
		_assertHits("test2", true);

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		_indexAccessorImpl.dumpIndex(byteArrayOutputStream);

		_indexAccessorImpl.loadIndex(
			new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));

		_assertHits("test1", true);
		_assertHits("test2", true);
	}

	private void _addDocuments(String key) throws Exception {
		for (int i = 0; i < _documentsCount; i++) {
			Document document = new Document();

			Field field = new Field(
				"name", key + i, Field.Store.YES, Field.Index.ANALYZED);

			document.add(field);

			_indexAccessorImpl.addDocument(document);
		}
	}

	private void _assertHits(String key, boolean expectHit) throws Exception {
		IndexSearcher indexSearcher = new IndexSearcher(
			_indexAccessorImpl.getLuceneDir());

		for (int i = 0; i < _documentsCount * 2; i++) {
			Term term = new Term("name", key + i);

			TermQuery termQuery = new TermQuery(term);

			TopDocs topDocs = indexSearcher.search(termQuery, 1);

			if (i < _documentsCount) {
				if (expectHit) {
					Assert.assertEquals(1, topDocs.totalHits);
				}
				else {
					Assert.assertEquals(0, topDocs.totalHits);
				}
			}
			else {
				Assert.assertEquals(0, topDocs.totalHits);
			}
		}

		indexSearcher.close();
	}

	private void _deleteDocuments(String key) throws Exception {
		for (int i = 0; i < _documentsCount; i++) {
			Term term = new Term("name", key + i);

			_indexAccessorImpl.deleteDocuments(term);
		}
	}

	private static final long _TEST_COMPANY_ID = 1000;

	private int _documentsCount;
	private IndexAccessorImpl _indexAccessorImpl;

}