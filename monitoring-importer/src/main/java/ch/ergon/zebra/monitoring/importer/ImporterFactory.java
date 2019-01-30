/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.importer;

import ch.ergon.zebra.monitoring.arff.TypeConverter;
import ch.ergon.zebra.monitoring.data.ConnectionFactory;
import ch.ergon.zebra.monitoring.data.cpu.CpuUsageRepository;
import ch.ergon.zebra.monitoring.data.cpu.SqlCpuUsageRepository;
import ch.ergon.zebra.monitoring.data.file.ImportedFileRepository;
import ch.ergon.zebra.monitoring.data.file.SqlImportedFileRepository;
import ch.ergon.zebra.monitoring.data.memory.MemoryUsageRepository;
import ch.ergon.zebra.monitoring.data.memory.SqlMemoryUsageRepository;
import ch.ergon.zebra.monitoring.data.profiling.SqlUseCaseProfilerRepository;
import ch.ergon.zebra.monitoring.data.profiling.UseCaseProfilerRepository;
import ch.ergon.zebra.monitoring.data.querydsl.domain.CpuUsage;
import ch.ergon.zebra.monitoring.data.querydsl.domain.MemoryUsage;
import ch.ergon.zebra.monitoring.data.querydsl.domain.Profiling;
import ch.ergon.zebra.monitoring.importer.cpu.CpuUsageImporter;
import ch.ergon.zebra.monitoring.importer.memory.MemoryUsageImporter;
import ch.ergon.zebra.monitoring.importer.profiling.UseCaseProfilerImporter;
import ch.ergon.zebra.monitoring.service.arff.OracleTypeConverter;
import ch.ergon.zebra.monitoring.service.service.ImporterConfig;
import ch.ergon.zebra.monitoring.service.service.ServiceContext;

import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.OracleTemplates;

public class ImporterFactory {

	public static Importer createImporter (ImporterConfig importerConfig, ConnectionFactory connectionFactory) {
		final Configuration configuration= new Configuration(new OracleTemplates());
		final TypeConverter typeConverter= new OracleTypeConverter();
		final String client= importerConfig.getClient();
		final String environment= importerConfig.getEnvironment();
		final ImportedFileRepository fileRepository= new SqlImportedFileRepository(configuration, connectionFactory);

		switch (importerConfig.getName()) {
			case "profiling":
				final UseCaseProfilerRepository profilingRepository= new SqlUseCaseProfilerRepository(configuration, connectionFactory);
				final ServiceContext<Profiling> profilingContext= new ServiceContext<Profiling>(client, environment, typeConverter,
					profilingRepository, fileRepository);
				return new UseCaseProfilerImporter(importerConfig, profilingContext);
			case "cpu":
				final CpuUsageRepository cpuUsageRepository= new SqlCpuUsageRepository(configuration, connectionFactory);
				final ServiceContext<CpuUsage> cpuUsageContext= new ServiceContext<CpuUsage>(client, environment, typeConverter,
					cpuUsageRepository, fileRepository);
				return new CpuUsageImporter(importerConfig, cpuUsageContext);
			case "memory":
				final MemoryUsageRepository memoryUsageRepository= new SqlMemoryUsageRepository(configuration, connectionFactory);
				final ServiceContext<MemoryUsage> memoryUsageContext= new ServiceContext<MemoryUsage>(client, environment, typeConverter,
					memoryUsageRepository, fileRepository);
				return new MemoryUsageImporter(importerConfig, memoryUsageContext);
			default:
				throw new IllegalArgumentException("No mapping defined for the following configuration: " + importerConfig);
		}
	}

}
