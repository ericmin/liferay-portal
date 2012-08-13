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

package com.liferay.portal.kernel.process.log;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.process.ProcessCallable;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Shuyang Zhou
 */
public class ProcessOutputStream extends UnsyncByteArrayOutputStream {

	public ProcessOutputStream(ObjectOutputStream objectOutputStream) {
		this(objectOutputStream, false);
	}

	public ProcessOutputStream(
		ObjectOutputStream objectOutputStream, boolean error) {

		_objectOutputStream = objectOutputStream;
		_error = error;
	}

	@Override
	public void close() throws IOException {
		_objectOutputStream.close();
	}

	@Override
	public void flush() throws IOException {
		if (index > 0) {
			byte[] bytes = toByteArray();

			LoggingProcessCallable loggingProcessCallable =
				new LoggingProcessCallable(bytes, _error);

			writeProcessCallable(loggingProcessCallable);

			reset();
		}
	}

	public void writeProcessCallable(ProcessCallable<?> processCallable)
		throws IOException {

		_objectOutputStream.writeObject(processCallable);

		_objectOutputStream.flush();
	}

	private final boolean _error;
	private final ObjectOutputStream _objectOutputStream;

}