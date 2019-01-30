/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.importer.cpu;

import java.nio.file.Path;

import ch.ergon.zebra.monitoring.data.querydsl.domain.CpuUsage;
import ch.ergon.zebra.monitoring.importer.AbstractImporter;
import ch.ergon.zebra.monitoring.service.cpu.CpuUsageService;
import ch.ergon.zebra.monitoring.service.service.ImportStatus;
import ch.ergon.zebra.monitoring.service.service.ImporterConfig;
import ch.ergon.zebra.monitoring.service.service.ServiceContext;

public class CpuUsageImporter extends AbstractImporter<CpuUsage> {

	public CpuUsageImporter (ImporterConfig importerConfiguration, ServiceContext<CpuUsage> serviceContext) {
		super(importerConfiguration, serviceContext);
	}

	@Override
	protected ImportStatus doImport (Path path) {
		final CpuUsageService service= new CpuUsageService(getServiceContext());
		return service.importCpuUsageFile(path);
	}

}
