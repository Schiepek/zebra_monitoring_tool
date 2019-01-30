/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.importer.memory;

import java.nio.file.Path;

import ch.ergon.zebra.monitoring.data.querydsl.domain.MemoryUsage;
import ch.ergon.zebra.monitoring.importer.AbstractImporter;
import ch.ergon.zebra.monitoring.service.memory.MemoryUsageService;
import ch.ergon.zebra.monitoring.service.service.ImportStatus;
import ch.ergon.zebra.monitoring.service.service.ImporterConfig;
import ch.ergon.zebra.monitoring.service.service.ServiceContext;

public class MemoryUsageImporter extends AbstractImporter<MemoryUsage> {

	public MemoryUsageImporter (ImporterConfig importerConfiguration, ServiceContext<MemoryUsage> serviceContext) {
		super(importerConfiguration, serviceContext);
	}

	@Override
	protected ImportStatus doImport (Path path) {
		final MemoryUsageService service= new MemoryUsageService(getServiceContext());
		return service.importMemoryUsageFile(path);
	}

}
