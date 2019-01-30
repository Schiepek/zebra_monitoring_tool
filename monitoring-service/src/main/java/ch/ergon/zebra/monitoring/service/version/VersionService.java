/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */
package ch.ergon.zebra.monitoring.service.version;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import com.google.common.io.ByteSource;
import com.google.common.io.Closeables;
import com.google.common.io.Resources;

public class VersionService {

	private static final String DEFAULT_VERSION_FILE= "ch/ergon/zebra/monitoring/service/version/version.properties";

	private static final String VERSION_KEY= "version";

	private static final String TIMESTAMP_KEY= "timestamp";

	private final String versionFile;

	public VersionService () {
		this(DEFAULT_VERSION_FILE);
	}

	/*package*/VersionService (String versionFile) {
		this.versionFile= versionFile;
	}

	public Version getVersion () {
		final Properties properties= getProperties(versionFile);
		final String version= properties.getProperty(VERSION_KEY);
		final String timestamp= properties.getProperty(TIMESTAMP_KEY);

		return new Version(version, timestamp);
	}

	private static Properties getProperties (String versionFile) {
		final URL url= Resources.getResource(versionFile);
		final ByteSource byteSource= Resources.asByteSource(url);

		InputStream inputStream= null;
		try {
			inputStream= byteSource.openBufferedStream();

			final Properties properties= new Properties();
			properties.load(inputStream);

			return properties;
		} catch (final IOException e) {
			throw new IllegalStateException("Failed to load properties file", e);
		} finally {
			Closeables.closeQuietly(inputStream);
		}
	}

}
