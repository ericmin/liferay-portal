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
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author Connor McKay
 */
public class Patcher {

	public void patch(
			FileChannel originalFileChannel,
			WritableByteChannel patchedWritableByteChannel,
			ByteChannelReader deltaByteChannelReader)
		throws IOException {

		deltaByteChannelReader.resizeBuffer(5);

		ByteBuffer deltaByteBuffer = deltaByteChannelReader.getBuffer();

		deltaByteChannelReader.ensureData(5);

		if (DeltaUtil.PROTOCOL_VERSION != deltaByteBuffer.get()) {
			throw new IOException("Unknown protocol version");
		}

		int blockLength = deltaByteBuffer.getInt();

		deltaByteChannelReader.resizeBuffer(
			blockLength * DeltaUtil.BUFFER_FACTOR + 5);

		deltaByteBuffer = deltaByteChannelReader.getBuffer();

		while (true) {
			deltaByteChannelReader.ensureData(1);

			byte key = deltaByteBuffer.get();

			if (key == DeltaUtil.REFERENCE_RANGE_KEY) {
				deltaByteChannelReader.ensureData(9);

				int firstBlockNumber = deltaByteBuffer.getInt();
				int lastBlockNumber = deltaByteBuffer.getInt();

				long position = firstBlockNumber * (long)blockLength;
				long length =
					(lastBlockNumber - firstBlockNumber + 1) *
						(long)blockLength;

				transfer(
					originalFileChannel, patchedWritableByteChannel, position,
					length);
			}
			else if (key == DeltaUtil.REFERENCE_KEY) {
				deltaByteChannelReader.ensureData(4);

				int blockNumber = deltaByteBuffer.getInt();

				long position = blockNumber * (long)blockLength;

				transfer(
					originalFileChannel, patchedWritableByteChannel, position,
					blockLength);
			}
			else if (key == DeltaUtil.DATA_KEY) {
				deltaByteChannelReader.ensureData(4);

				int length = deltaByteBuffer.getInt();

				deltaByteChannelReader.ensureData(length);

				int oldLimit = deltaByteBuffer.limit();

				deltaByteBuffer.limit(deltaByteBuffer.position() + length);

				patchedWritableByteChannel.write(deltaByteBuffer);

				deltaByteBuffer.limit(oldLimit);
			}
			else if (key == DeltaUtil.EOF_KEY) {
				return;
			}
			else {
				throw new IOException("Invalid key");
			}
		}
	}

	protected void transfer(
			FileChannel source, WritableByteChannel destination, long position,
			long length)
		throws IOException {

		if (length > _NATIVE_TRANSFER_THRESHOLD) {
			source.transferTo(position, length, destination);
		}
		else {
			_transferByteBuffer.clear();
			_transferByteBuffer.limit((int)length);

			source.read(_transferByteBuffer, position);

			_transferByteBuffer.flip();

			destination.write(_transferByteBuffer);
		}
	}

	private static final int _NATIVE_TRANSFER_THRESHOLD = 1000000;

	private ByteBuffer _transferByteBuffer = ByteBuffer.allocate(
		_NATIVE_TRANSFER_THRESHOLD);

}