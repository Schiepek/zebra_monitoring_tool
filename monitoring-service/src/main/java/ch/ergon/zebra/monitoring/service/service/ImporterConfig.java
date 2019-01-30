/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.common.base.Preconditions;

public final class ImporterConfig {

	private final String name;

	private final String client;

	private final String environment;

	private final Path pendingDirectory;

	private final Path doneDirectory;

	private final Path failedDirectory;

	public ImporterConfig (String name, String client, String environment, String pendingDirectory, String doneDirectory,
		String failedDirectory) {
		this.name= Preconditions.checkNotNull(name, "Field \"name\" can not be null");
		this.client= Preconditions.checkNotNull(client, "Field \"client\" can not be null");
		this.environment= Preconditions.checkNotNull(environment, "Field \"environment\" can not be null");
		this.pendingDirectory= Paths.get(pendingDirectory);
		this.doneDirectory= Paths.get(doneDirectory);
		this.failedDirectory= Paths.get(failedDirectory);
	}

	public String getName () {
		return name;
	}

	public Path getPendingDirectory () {
		return pendingDirectory;
	}

	public Path getDoneDirectory () {
		return doneDirectory;
	}

	public Path getFailedDirectory () {
		return failedDirectory;
	}

	public String getClient () {
		return client;
	}

	public String getEnvironment () {
		return environment;
	}

	@Override
	public String toString () {
		StringBuilder builder= new StringBuilder();
		builder
		.append("ImporterConfiguration [name=")
		.append(name).append(", client=")
		.append(client).append(", environment=")
		.append(environment)
		.append(", pending=")
		.append(pendingDirectory)
		.append(", done=")
		.append(doneDirectory)
		.append(", failed=")
		.append(failedDirectory)
		.append("]");
		return builder.toString();
	}

}
