/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */
package ch.ergon.zebra.monitoring.arff.source;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;

public class PathSource implements Source {

	private final Path path;

	public PathSource (Path path) {
		this.path= path;
	}

	@Override
	public String getName () {
		return path.getFileName().toString();
	}

	@Override
	public InputStream getInputStream () {
		try {
			final FileInputStream inputStream= new FileInputStream(path.toFile());
			return inputStream;
		} catch (final FileNotFoundException e) {
			throw new ArffSourceException("Invalid path: " + path, e);
		}

	}

	@Override
	public String toString () {
		final StringBuilder builder= new StringBuilder();
		builder
			.append("PathSource [name=")
			.append(getName())
			.append("]");

		return builder.toString();
	}

}
