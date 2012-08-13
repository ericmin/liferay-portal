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

package com.liferay.portal.tools.samplesqlbuilder;

import com.liferay.portal.tools.ArgumentsUtil;
import com.liferay.portal.tools.DBLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.util.Map;

/**
 * @author Tina Tian
 * @author Shuyang Zhou
 */
public class TestSampleSQLBuilder {

	public static void main(String[] args) throws Exception {
		Map<String, String> arguments = ArgumentsUtil.parseArguments(args);

		String sqlDir = arguments.get("sql.dir");
		String outputDir = arguments.get("sample.sql.output.dir");

		SampleSQLBuilder.main(args);

		new TestSampleSQLBuilder(sqlDir, outputDir);
	}

	public TestSampleSQLBuilder(String sqlDir, String outputDir)
		throws Exception {

		_sqlDir = sqlDir;
		_outputDir = outputDir;

		_loadHypersonic();
	}

	private void _loadHypersonic() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");

		Connection con = DriverManager.getConnection(
			"jdbc:hsqldb:mem:testSampleSQLBuilderDB;shutdown=true", "sa", "");

		DBLoader.loadHypersonic(
			con, _sqlDir + "/portal-minimal/portal-minimal-hypersonic.sql");
		DBLoader.loadHypersonic(
			con, _sqlDir + "/indexes/indexes-hypersonic.sql");
		DBLoader.loadHypersonic(con, _outputDir + "/sample-hypersonic.sql");

		Statement statement = con.createStatement();

		statement.execute("SHUTDOWN COMPACT");

		statement.close();

		con.close();
	}

	private String _outputDir;
	private String _sqlDir;

}