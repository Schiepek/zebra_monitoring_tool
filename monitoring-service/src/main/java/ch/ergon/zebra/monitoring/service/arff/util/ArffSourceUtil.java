/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.arff.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import ch.ergon.zebra.monitoring.arff.source.PathSource;
import ch.ergon.zebra.monitoring.arff.source.Source;
import ch.ergon.zebra.monitoring.arff.source.ZipEntrySource;

public final class ArffSourceUtil {

	private ArffSourceUtil () {
		// utility class
	}

	public static List<Source> getSources (Path path) {
		final List<Source> sources= new ArrayList<>();

		try {
			if (FileUtil.isZipFile(path)) {
				sources.addAll(createZipSources(path));
			} else {
				sources.add(new PathSource(path));
			}
		} catch (IOException e) {
			throw new IllegalStateException("Failed to open zip file: " + path, e);
		}

		return sources;
	}

	private static List<Source> createZipSources (Path path) throws ZipException, IOException {
		final List<Source> sources= new ArrayList<>();

		final ZipFile zipFile= new ZipFile(path.toFile());
		final Enumeration<? extends ZipEntry> entries= zipFile.entries();
		while (entries.hasMoreElements()) {
			final ZipEntry zipEntry= entries.nextElement();
			sources.add(new ZipEntrySource(zipFile, zipEntry));
		}

		return sources;
	}

}
