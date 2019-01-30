/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.importer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ergon.zebra.monitoring.service.service.ImportStatus;
import ch.ergon.zebra.monitoring.service.service.ImporterConfig;
import ch.ergon.zebra.monitoring.service.service.ServiceContext;

public abstract class AbstractImporter<T> implements Importer {

	private static final Logger LOG= LoggerFactory.getLogger(AbstractImporter.class);

	private final Path pending;

	private final Path done;

	private final Path failed;

	private final ServiceContext<T> serviceContext;

	public AbstractImporter (ImporterConfig importerConfiguration, ServiceContext<T> serviceContext) {
		this.serviceContext= serviceContext;
		this.pending= importerConfiguration.getPendingDirectory();
		this.done= importerConfiguration.getDoneDirectory();
		this.failed= importerConfiguration.getFailedDirectory();
	}

	@Override
	public void execute () {
		final Iterator<Path> iterator= getPendingDirectory().iterator();
		while (iterator.hasNext()) {
			final Path path= iterator.next();
			LOG.info("Importing file: " + path + "...");
			switch (doImport(path)) {
				case SUCCESS:
					LOG.info("Import of file " + path + " ended successfully");
					moveFileToDirectory(path, done);
					break;
				case TEMPORARY_ERROR:
					LOG.info("Import of file " + path + " failed temporarily");
					break;
				case DATA_ERROR:
					LOG.info("Import of file " + path + " failed due to an error in the file format");
					moveFileToDirectory(path, failed);
					break;
			}
		}
	}

	protected abstract ImportStatus doImport (Path path);

	protected ServiceContext<T> getServiceContext () {
		return serviceContext;
	}

	private Stream<Path> getPendingDirectory () {
		final Path pendingDirectory= pending;
		try {
			return Files.list(pendingDirectory);
		} catch (IOException e) {
			LOG.error("Failed to read 'pending' directory: " + pendingDirectory, e);
			throw new IllegalStateException(e);
		}
	}

	private void moveFileToDirectory (Path sourceFile, Path directory) {
		final Path targetFile= directory.resolve(sourceFile.getFileName());

		try {
			Files.move(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			LOG.error("Moving file from " + sourceFile + " to " + targetFile + " failed", e);
		}
	}

}
