/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.memory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import ch.ergon.zebra.monitoring.arff.TypeConverter;
import ch.ergon.zebra.monitoring.data.querydsl.domain.MemoryUsage;
import ch.ergon.zebra.monitoring.service.arff.OracleTypeConverter;
import ch.ergon.zebra.monitoring.service.mock.MockImportedFileRepository;
import ch.ergon.zebra.monitoring.service.mock.MockMeasurementRepository;
import ch.ergon.zebra.monitoring.service.service.ServiceContext;

public class MemoryUsageServiceTest {

	final static String SAMPLE_MEMORY_LOG= "/ch/ergon/zebra/monitoring/service/memory/sample_memory_usage.log";

	@Test
	public void testMemoryUsageImport () throws URISyntaxException, IOException {

		// Arrange
		Path samplePath= Paths.get(ClassLoader.class.getResource(SAMPLE_MEMORY_LOG).toURI());

		String client= "testClient";
		String environment= "testEnvironment";

		TypeConverter typeConverter= new OracleTypeConverter();
		final MockMeasurementRepository<MemoryUsage> repository= new MockMeasurementRepository<>();
		final MockImportedFileRepository fileRepository= new MockImportedFileRepository();
		final ServiceContext<MemoryUsage> context= new ServiceContext<MemoryUsage>(client, environment, typeConverter, repository, fileRepository);

		MemoryUsageService service= new MemoryUsageService(context);

		// Act
		service.importMemoryUsageFile(samplePath);

		// Assert
		List<MemoryUsage> actualCpuUsages= repository.getEntities();
		assertThat(actualCpuUsages.size(), is(1));

		MemoryUsage actualMemoryUsage= actualCpuUsages.get(0);

		assertThat(actualMemoryUsage.getClient(), equalTo(typeConverter.getTextValue(client)));
		assertThat(actualMemoryUsage.getEnvironment(), equalTo(typeConverter.getTextValue(environment)));
		assertThat(actualMemoryUsage.getSysDatetime(),
			equalTo(typeConverter.getDateValue("15.03.2015 10:09:46", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"))));
		assertThat(actualMemoryUsage.getTotalHeap(), equalTo(typeConverter.getNumericValue("12800")));
		assertThat(actualMemoryUsage.getFreeHeap(), equalTo(typeConverter.getNumericValue("4000")));
	}

}
