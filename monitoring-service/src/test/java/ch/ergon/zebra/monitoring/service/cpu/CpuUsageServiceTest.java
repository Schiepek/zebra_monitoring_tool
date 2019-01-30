/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.cpu;

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
import ch.ergon.zebra.monitoring.data.querydsl.domain.CpuUsage;
import ch.ergon.zebra.monitoring.service.arff.OracleTypeConverter;
import ch.ergon.zebra.monitoring.service.mock.MockImportedFileRepository;
import ch.ergon.zebra.monitoring.service.mock.MockMeasurementRepository;
import ch.ergon.zebra.monitoring.service.service.ServiceContext;

public class CpuUsageServiceTest {

	final static String SAMPLE_CPU_LOG= "/ch/ergon/zebra/monitoring/service/cpu/sample_cpu_usage.log";

	@Test
	public void testCpuUsageImport () throws URISyntaxException, IOException {

		// Arrange
		Path samplePath= Paths.get(ClassLoader.class.getResource(SAMPLE_CPU_LOG).toURI());

		String client= "testClient";
		String environment= "testEnvironment";

		TypeConverter typeConverter= new OracleTypeConverter();
		final MockMeasurementRepository<CpuUsage> repository= new MockMeasurementRepository<>();
		final MockImportedFileRepository fileRepository= new MockImportedFileRepository();

		final ServiceContext<CpuUsage> context= new ServiceContext<CpuUsage>(client, environment, typeConverter, repository, fileRepository);

		CpuUsageService service= new CpuUsageService(context);

		// Act
		service.importCpuUsageFile(samplePath);

		// Assert
		List<CpuUsage> actualCpuUsages= repository.getEntities();
		assertThat(actualCpuUsages.size(), is(1));

		CpuUsage actualCpuUsage= actualCpuUsages.get(0);

		assertThat(actualCpuUsage.getClient(), equalTo(typeConverter.getTextValue(client)));
		assertThat(actualCpuUsage.getEnvironment(), equalTo(typeConverter.getTextValue(environment)));
		assertThat(actualCpuUsage.getSysDatetime(),
			equalTo(typeConverter.getDateValue("12.03.2015 10:09:46", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"))));
		assertThat(actualCpuUsage.getCpuUsage(), equalTo(typeConverter.getNumericValue("13.5")));
	}

}
