/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.file;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import ch.ergon.zebra.monitoring.arff.TypeConverter;
import ch.ergon.zebra.monitoring.data.querydsl.domain.CpuUsage;
import ch.ergon.zebra.monitoring.service.arff.OracleTypeConverter;
import ch.ergon.zebra.monitoring.service.cpu.CpuUsageService;
import ch.ergon.zebra.monitoring.service.mock.MockCpuUsageRepository;
import ch.ergon.zebra.monitoring.service.mock.MockImportedFileRepository;
import ch.ergon.zebra.monitoring.service.service.ServiceContext;

public class ExistingRecordTest {

	final static String SAMPLE_CPU_LOG= "/ch/ergon/zebra/monitoring/service/cpu/sample_cpu_usage.log";

	final String client= "testClient";

	final String environment= "testEnvironment";

	TypeConverter typeConverter= new OracleTypeConverter();

	final MockCpuUsageRepository repository= new MockCpuUsageRepository();

	final MockImportedFileRepository fileRepository= new MockImportedFileRepository();

	final ServiceContext<CpuUsage> context= new ServiceContext<CpuUsage>(client, environment, typeConverter, repository, fileRepository);

	CpuUsageService service= new CpuUsageService(context);

	@Test
	public void testImportExistingRecord () throws URISyntaxException, IOException {
		// Arrange
		Path samplePath= Paths.get(ClassLoader.class.getResource(SAMPLE_CPU_LOG).toURI());

		// Act
		service.importCpuUsageFile(samplePath);
		service.importCpuUsageFile(samplePath);

		// Assert
		List<CpuUsage> actualProfilings= repository.getEntities();
		assertThat(actualProfilings.size(), is(1));
	}

}
