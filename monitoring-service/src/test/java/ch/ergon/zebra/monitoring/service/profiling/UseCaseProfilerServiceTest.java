/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.profiling;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import ch.ergon.zebra.monitoring.arff.TypeConverter;
import ch.ergon.zebra.monitoring.data.querydsl.domain.Profiling;
import ch.ergon.zebra.monitoring.service.arff.OracleTypeConverter;
import ch.ergon.zebra.monitoring.service.mock.MockImportedFileRepository;
import ch.ergon.zebra.monitoring.service.mock.MockMeasurementRepository;
import ch.ergon.zebra.monitoring.service.service.ServiceContext;

public class UseCaseProfilerServiceTest {

	private final static String PROFILING_LOG_JAZZ_2_2_9= "/ch/ergon/zebra/monitoring/service/profiling/sample_profiling_jazz_2.2.9.log";

	private final static String PROFILING_LOG_JAZZ_2_3_17= "/ch/ergon/zebra/monitoring/service/profiling/sample_profiling_jazz_2.3.17.log";

	private final static String PROFILING_LOG_JAZZ_2_4_14= "/ch/ergon/zebra/monitoring/service/profiling/sample_profiling_jazz_2.4.14.log";

	private final static String PROFILING_LOG_MAPS_1_51_0= "/ch/ergon/zebra/monitoring/service/profiling/sample_profiling_maps_1.51.0.log";

	String client= "testClient";

	String environment= "testEnvironment";

	TypeConverter typeConverter= new OracleTypeConverter();

	final MockMeasurementRepository<Profiling> repository= new MockMeasurementRepository<>();

	final MockImportedFileRepository fileRepository= new MockImportedFileRepository();

	final ServiceContext<Profiling> context= new ServiceContext<Profiling>(client, environment, typeConverter, repository, fileRepository);

	UseCaseProfilerService service= new UseCaseProfilerService(context);

	@Test
	public void testImport_jazz_2_2_9 () throws URISyntaxException, IOException {

		// Arrange
		Path samplePath= Paths.get(ClassLoader.class.getResource(PROFILING_LOG_JAZZ_2_2_9).toURI());

		// Act
		service.importUseCaseProfilerFile(samplePath);

		// Assert
		List<Profiling> actualProfilings= repository.getEntities();
		assertThat(actualProfilings.size(), is(1));

		Profiling actualProfiling= actualProfilings.get(0);

		assertThat(actualProfiling.getClient(), equalTo(typeConverter.getTextValue(client)));
		assertThat(actualProfiling.getEnvironment(), equalTo(typeConverter.getTextValue(environment)));
		assertThat(actualProfiling.getUsecaseId(), equalTo(typeConverter.getTextValue("initialPageLoad-pepWochenAnsicht")));
		assertThat(actualProfiling.getServerExecutionTimeMs(), equalTo(typeConverter.getNumericValue("834")));
		assertThat(actualProfiling.getSysDatetime(),
			equalTo(typeConverter.getDateValue("18.02.2014 18:54:36", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"))));
		assertThat(actualProfiling.getUsecaseExpectsNavTime(), equalTo(typeConverter.getBooleanValue("true")));
	}

	@Test
	public void testImport_jazz_2_3_17 () throws URISyntaxException, IOException {

		// Arrange
		Path samplePath= Paths.get(ClassLoader.class.getResource(PROFILING_LOG_JAZZ_2_3_17).toURI());

		// Act
		service.importUseCaseProfilerFile(samplePath);

		// Assert
		List<Profiling> actualProfilings= repository.getEntities();
		assertThat(actualProfilings.size(), is(1));

		Profiling actualProfiling= actualProfilings.get(0);

		assertThat(actualProfiling.getClient(), equalTo(typeConverter.getTextValue(client)));
		assertThat(actualProfiling.getEnvironment(), equalTo(typeConverter.getTextValue(environment)));
		assertThat(actualProfiling.getNrOfAccKostenstelleToday(), is(new BigDecimal("1.0")));
	}

	@Test
	public void testImport_jazz_2_4_14 () throws URISyntaxException, IOException {

		// Arrange
		Path samplePath= Paths.get(ClassLoader.class.getResource(PROFILING_LOG_JAZZ_2_4_14).toURI());

		// Act
		service.importUseCaseProfilerFile(samplePath);

		// Assert
		List<Profiling> actualProfilings= repository.getEntities();
		assertThat(actualProfilings.size(), is(1));

		Profiling actualProfiling= actualProfilings.get(0);

		assertThat(actualProfiling.getClient(), equalTo(typeConverter.getTextValue(client)));
		assertThat(actualProfiling.getEnvironment(), equalTo(typeConverter.getTextValue(environment)));
		assertThat(actualProfiling.getServerWaitTimeMs(), equalTo(typeConverter.getNumericValue("0")));
		assertThat(actualProfiling.getServerExecNoWaitTimeMs(), equalTo(typeConverter.getNumericValue("48478")));
		assertThat(actualProfiling.getStatementsExecuted(), equalTo(null));
		assertThat(actualProfiling.getTotalExecutionTimeMs(), equalTo(null));
		assertThat(actualProfiling.getTotalFetchTimeMs(), equalTo(new BigDecimal("200.0")));
		assertThat(actualProfiling.getTotalDatabaseTimeMs(), equalTo(new BigDecimal("225.0")));
		assertThat(actualProfiling.getNrOfAccKostenstelleToday(), equalTo(typeConverter.getNumericValue("2")));
	}

	@Test
	public void testImport_maps_1_51_0 () throws URISyntaxException, IOException {

		// Arrange
		Path samplePath= Paths.get(ClassLoader.class.getResource(PROFILING_LOG_MAPS_1_51_0).toURI());

		// Act
		service.importUseCaseProfilerFile(samplePath);

		// Assert
		List<Profiling> actualProfilings= repository.getEntities();
		assertThat(actualProfilings.size(), is(1));

		Profiling actualProfiling= actualProfilings.get(0);

		assertThat(actualProfiling.getClient(), equalTo(typeConverter.getTextValue(client)));
		assertThat(actualProfiling.getEnvironment(), equalTo(typeConverter.getTextValue(environment)));
		assertThat(actualProfiling.getClientIpAddress(), equalTo(typeConverter.getTextValue("10.12.8.140")));
	}

}
