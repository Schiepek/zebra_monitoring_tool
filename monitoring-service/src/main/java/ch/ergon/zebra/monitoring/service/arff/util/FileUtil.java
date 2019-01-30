/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.arff.util;

import java.io.File;
import java.nio.file.Path;

public final class FileUtil {

	private static final String ZIP_EXTENSION= ".zip";

	private FileUtil () {
		// utility class
	}

	public static boolean isZipFile (Path path) {
		final File file= path.toFile();
		if (file.isDirectory()) {
			return false;
		}

		return file.getAbsolutePath().endsWith(ZIP_EXTENSION);
	}

}
