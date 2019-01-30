/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.arff.source;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipEntrySource implements Source {

	private final ZipFile zipFile;

	private final ZipEntry zipEntry;

	public ZipEntrySource (ZipFile zipFile, ZipEntry zipEntry) {
		this.zipFile= zipFile;
		this.zipEntry= zipEntry;
	}

	@Override
	public String getName () {
		return zipEntry.getName();
	}

	@Override
	public InputStream getInputStream () {
		try {
			final InputStream inputStream= zipFile.getInputStream(zipEntry);
			return inputStream;
		} catch (final IOException e) {
			throw new ArffSourceException("Failed to get an InputStream from the ZipEntry: " + zipEntry, e);
		}
	}

	@Override
	public String toString () {
		final StringBuilder builder= new StringBuilder();
		builder
			.append("ZipEntrySource [name=")
			.append(getName())
			.append("]");

		return builder.toString();
	}

}
