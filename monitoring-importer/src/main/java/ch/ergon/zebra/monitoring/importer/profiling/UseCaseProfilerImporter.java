/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.importer.profiling;

import java.nio.file.Path;

import ch.ergon.zebra.monitoring.data.querydsl.domain.Profiling;
import ch.ergon.zebra.monitoring.importer.AbstractImporter;
import ch.ergon.zebra.monitoring.service.profiling.UseCaseProfilerService;
import ch.ergon.zebra.monitoring.service.service.ImportStatus;
import ch.ergon.zebra.monitoring.service.service.ImporterConfig;
import ch.ergon.zebra.monitoring.service.service.ServiceContext;

public class UseCaseProfilerImporter extends AbstractImporter<Profiling> {

	public UseCaseProfilerImporter (ImporterConfig importerConfiguration, ServiceContext<Profiling> serviceContext) {
		super(importerConfiguration, serviceContext);
	}

	@Override
	protected ImportStatus doImport (Path path) {
		final UseCaseProfilerService service= new UseCaseProfilerService(getServiceContext());
		return service.importUseCaseProfilerFile(path);
	}

}
