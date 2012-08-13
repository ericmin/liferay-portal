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
import java.nio.channels.ReadableByteChannel;

/**
 * @author Connor McKay
 */
public class ByteChannelReader {

	public ByteChannelReader(ReadableByteChannel readableByteChannel)
		throws IOException {

		this(readableByteChannel, 1024);
	}

	public ByteChannelReader(
			ReadableByteChannel readableByteChannel, int bufferLength)
		throws IOException {

		_readableByteChannel = readableByteChannel;

		_byteBuffer = ByteBuffer.allocate(bufferLength);

		if (_readableByteChannel.read(_byteBuffer) == -1) {
			_eof = true;
		}
		else {
			_eof = false;
		}

		_byteBuffer.flip();
	}

	public void ensureData(int length) throws IOException {
		if (_byteBuffer.remaining() < length) {
			read();

			if (_eof || (_byteBuffer.remaining() < length)) {
				throw new IOException("Unexpected EOF");
			}
		}
	}

	public byte get() {
		return _byteBuffer.get();
	}

	public byte get(int offset) {
		return _byteBuffer.get(_byteBuffer.position() + offset);
	}

	public ByteBuffer getBuffer() {
		return _byteBuffer;
	}

	public boolean hasRemaining() {
		return _byteBuffer.hasRemaining();
	}

	public void maybeRead(int length) throws IOException {
		if (!_eof && (_byteBuffer.remaining() < length)) {
			read();
		}
	}

	public void read() throws IOException {
		if (_eof) {
			return;
		}

		_byteBuffer.compact();

		if (_readableByteChannel.read(_byteBuffer) == -1) {
			_eof = true;
		}
		else {
			_eof = false;
		}

		_byteBuffer.flip();
	}

	public int remaining() {
		return _byteBuffer.remaining();
	}

	public void resizeBuffer(int minBufferLength) {
		if (_byteBuffer.capacity() >= minBufferLength) {
			return;
		}

		ByteBuffer newBuffer = ByteBuffer.allocate(minBufferLength);

		newBuffer.put(_byteBuffer);
		newBuffer.flip();

		_byteBuffer = newBuffer;
	}

	public int skip(int length) {
		length = Math.min(_byteBuffer.remaining(), length);

		_byteBuffer.position(_byteBuffer.position() + length);

		return length;
	}

	private ByteBuffer _byteBuffer;
	private boolean _eof;
	private ReadableByteChannel _readableByteChannel;

}