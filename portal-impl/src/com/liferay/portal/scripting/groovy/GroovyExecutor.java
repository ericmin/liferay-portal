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

package com.liferay.portal.scripting.groovy;

import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.kernel.scripting.BaseScriptingExecutor;
import com.liferay.portal.kernel.scripting.ExecutionException;
import com.liferay.portal.kernel.scripting.ScriptingException;
import com.liferay.portal.kernel.util.AggregateClassLoader;
import com.liferay.portal.security.pacl.PACLClassLoaderUtil;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * @author Alberto Montero
 * @author Brian Wing Shun Chan
 */
public class GroovyExecutor extends BaseScriptingExecutor {

	@Override
	public void clearCache() {
		SingleVMPoolUtil.clear(_CACHE_NAME);
	}

	public Map<String, Object> eval(
			Set<String> allowedClasses, Map<String, Object> inputObjects,
			Set<String> outputNames, String script, ClassLoader... classLoaders)
		throws ScriptingException {

		if (allowedClasses != null) {
			throw new ExecutionException(
				"Constrained execution not supported for Groovy");
		}

		Script compiledScript = getCompiledScript(script, classLoaders);

		Binding binding = new Binding(inputObjects);

		compiledScript.setBinding(binding);

		compiledScript.run();

		if (outputNames == null) {
			return null;
		}

		Map<String, Object> outputObjects = new HashMap<String, Object>();

		for (String outputName : outputNames) {
			outputObjects.put(outputName, binding.getVariable(outputName));
		}

		return outputObjects;
	}

	public String getLanguage() {
		return _LANGUAGE;
	}

	protected Script getCompiledScript(
		String script, ClassLoader[] classLoaders) {

		GroovyShell groovyShell = getGroovyShell(classLoaders);

		String key = String.valueOf(script.hashCode());

		Script compiledScript = (Script)SingleVMPoolUtil.get(_CACHE_NAME, key);

		if (compiledScript == null) {
			compiledScript = groovyShell.parse(script);

			SingleVMPoolUtil.put(_CACHE_NAME, key, compiledScript);
		}

		return compiledScript;
	}

	protected GroovyShell getGroovyShell(ClassLoader[] classLoaders) {
		if ((classLoaders == null) || (classLoaders.length == 0)) {
			if (_groovyShell == null) {
				synchronized (this) {
					if (_groovyShell == null) {
						_groovyShell = new GroovyShell();
					}
				}
			}

			return _groovyShell;
		}

		ClassLoader aggregateClassLoader =
			AggregateClassLoader.getAggregateClassLoader(
				PACLClassLoaderUtil.getPortalClassLoader(), classLoaders);

		GroovyShell groovyShell = null;

		if (!_groovyShells.containsKey(aggregateClassLoader)) {
			synchronized (this) {
				if (!_groovyShells.containsKey(aggregateClassLoader)) {
					groovyShell = new GroovyShell(aggregateClassLoader);

					_groovyShells.put(aggregateClassLoader, groovyShell);
				}
			}
		}

		return groovyShell;
	}

	private static final String _CACHE_NAME = GroovyExecutor.class.getName();

	private static final String _LANGUAGE = "groovy";

	private volatile GroovyShell _groovyShell = new GroovyShell();
	private volatile Map<ClassLoader, GroovyShell> _groovyShells =
		new WeakHashMap<ClassLoader, GroovyShell>();

}