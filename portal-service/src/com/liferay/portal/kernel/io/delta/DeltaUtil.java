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
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author Connor McKay
 */
public class DeltaUtil {

	public static final int BUFFER_FACTOR = 16;

	public static final byte DATA_KEY = 1;

	public static final byte EOF_KEY = 0;

	public static final byte PROTOCOL_VERSION = 1;

	public static final byte REFERENCE_KEY = 2;

	public static final byte REFERENCE_RANGE_KEY = 3;

	public static void checksums(
			FileChannel originalFileChannel,
			ByteChannelWriter checksumsByteChannelWriter)
		throws IOException {

		checksums(originalFileChannel, checksumsByteChannelWriter, 512);
	}

	public static void checksums(
			FileChannel originalFileChannel,
			ByteChannelWriter checksumsByteChannelWriter, int blockLength)
		throws IOException {

		RollingChecksum rollingChecksum = new RollingChecksum(
			originalFileChannel, blockLength);

		checksumsByteChannelWriter.resizeBuffer(BUFFER_FACTOR * 20);

		ByteBuffer byteBuffer = checksumsByteChannelWriter.getBuffer();

		int blocksCount = (int)Math.ceil(
			originalFileChannel.size() / (double)blockLength);

		checksumsByteChannelWriter.ensureSpace(9);

		byteBuffer.put(PROTOCOL_VERSION);
		byteBuffer.putInt(blockLength);
		byteBuffer.putInt(blocksCount);

		while (rollingChecksum.hasNext()) {
			rollingChecksum.nextBlock();

			checksumsByteChannelWriter.ensureSpace(20);

			byteBuffer.putInt(rollingChecksum.weakChecksum());
			byteBuffer.put(rollingChecksum.strongChecksum());
		}
	}

	public static void delta(
			ReadableByteChannel modifiedReadableByteChannel,
			ByteChannelReader checksumsByteChannelReader,
			ByteChannelWriter deltaByteChannelWriter)
		throws IOException {

		Differ differ = new Differ();

		differ.delta(
			modifiedReadableByteChannel, checksumsByteChannelReader,
			deltaByteChannelWriter);
	}

	public static void patch(
			FileChannel originalFileChannel,
			WritableByteChannel patchedWritableByteChannel,
			ByteChannelReader deltaByteChannelReader)
		throws IOException {

		Patcher patcher = new Patcher();

		patcher.patch(
			originalFileChannel, patchedWritableByteChannel,
			deltaByteChannelReader);
	}

}