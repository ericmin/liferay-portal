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

package com.liferay.portal.kernel.io.delta;

import java.io.IOException;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/**
 * @author Connor McKay
 */
public class ByteChannelWriter {

	public ByteChannelWriter(WritableByteChannel writableByteChannel) {
		this(writableByteChannel, 1024);
	}

	public ByteChannelWriter(
		WritableByteChannel writableByteChannel, int bufferLength) {

		_writableByteChannel = writableByteChannel;

		_byteBuffer = ByteBuffer.allocate(bufferLength);
	}

	public void ensureSpace(int length) throws IOException {
		if (_byteBuffer.remaining() < length) {
			write();
		}
	}

	public void finish() throws IOException {
		_byteBuffer.flip();

		_writableByteChannel.write(_byteBuffer);
	}

	public ByteBuffer getBuffer() {
		return _byteBuffer;
	}

	public void resizeBuffer(int minBufferLength) {
		if (_byteBuffer.capacity() >= minBufferLength) {
			return;
		}

		ByteBuffer newBuffer = ByteBuffer.allocate(minBufferLength);

		_byteBuffer.flip();

		newBuffer.put(_byteBuffer);

		_byteBuffer = newBuffer;
	}

	protected void write() throws IOException {
		_byteBuffer.flip();

		_writableByteChannel.write(_byteBuffer);

		_byteBuffer.clear();
	}

	private ByteBuffer _byteBuffer;
	private WritableByteChannel _writableByteChannel;

}