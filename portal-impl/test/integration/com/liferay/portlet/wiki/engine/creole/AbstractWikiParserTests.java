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

package com.liferay.portlet.wiki.engine.creole;

import com.liferay.portal.parsers.creole.ast.WikiPageNode;
import com.liferay.portal.parsers.creole.parser.Creole10Lexer;
import com.liferay.portal.parsers.creole.parser.Creole10Parser;

import java.io.IOException;
import java.io.InputStream;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import org.junit.Assert;

/**
 * @author Miguel Pastor
 */
public abstract class AbstractWikiParserTests {

	protected Creole10Parser getCreole10Parser(String fileName)
		throws IOException {

		Class<?> clazz = getClass();

		InputStream inputStream = clazz.getResourceAsStream(
			"dependencies/" + fileName);

		ANTLRInputStream antlrInputStream = new ANTLRInputStream(inputStream);

		Creole10Lexer creole10Lexer = new Creole10Lexer(antlrInputStream);

		CommonTokenStream commonTokenStream = new CommonTokenStream(
			creole10Lexer);

		return new Creole10Parser(commonTokenStream);
	}

	protected WikiPageNode getWikiPageNode(String fileName) {
		try {
			creole10Parser = getCreole10Parser(fileName);

			creole10Parser.wikipage();
		}
		catch (IOException ioe) {
			Assert.fail("File does not exist");
		}
		catch (RecognitionException re) {
			Assert.fail("File could not be parsed");
		}

		return creole10Parser.getWikiPageNode();
	}

	protected Creole10Parser creole10Parser;

}