/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */
package ch.ergon.zebra.monitoring.service.version;

public final class Version {

	private final String version;

	private final String timestamp;

	public Version (String version, String timestamp) {
		this.version= version;
		this.timestamp= timestamp;
	}

	public String getVersion () {
		return version;
	}

	public String getTimestamp () {
		return timestamp;
	}

	@Override
	public String toString () {
		final StringBuilder builder= new StringBuilder();
		builder
			.append("Version [version=")
			.append(version)
			.append(", timestamp=")
			.append(timestamp)
			.append("]");

		return builder.toString();
	}

}
