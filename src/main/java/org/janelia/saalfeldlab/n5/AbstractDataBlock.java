/**
 * Copyright (c) 2017, Stephan Saalfeld
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.janelia.saalfeldlab.n5;

import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;

abstract public class AbstractDataBlock<T>
{
	protected final int[] size;
	protected final long[] gridPosition;
	protected final T data;

	public AbstractDataBlock(final int[] size, final long[] gridPosition, final T data) {
		this.size = size;
		this.gridPosition = gridPosition;
		this.data = data;
	}

	public int[] getSize() {
		return size;
	}

	public long[] getGridPosition() {
		return gridPosition;
	}

	public T getData() {
		return data;
	}

	public static Path getPath(final String datasetPathName, final long[] gridPosition) {

		final String[] pathComponents = new String[gridPosition.length];
		for (int i = 0; i < pathComponents.length; ++i)
			pathComponents[i] = Long.toString(gridPosition[i]);

		return Paths.get(
				datasetPathName,
				pathComponents);
	}

	public static int getNumElements(final int[] size) {
		int n = size[0];
		for (int i = 1; i < size.length; ++i)
			n += size[i];
		return n;
	}

	abstract public ByteBuffer toByteBuffer();

	abstract public void readData(final ByteBuffer buffer);
}