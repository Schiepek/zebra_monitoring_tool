/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.file;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.junit.Test;

import ch.ergon.zebra.monitoring.arff.TypeConverter;
import ch.ergon.zebra.monitoring.data.file.Status;
import ch.ergon.zebra.monitoring.data.querydsl.domain.ImportedFile;
import ch.ergon.zebra.monitoring.service.arff.OracleTypeConverter;
import ch.ergon.zebra.monitoring.service.mock.MockImportedFileRepository;
import ch.ergon.zebra.monitoring.service.mock.MockMeasurementRepository;
import ch.ergon.zebra.monitoring.service.service.ServiceContext;

public class FileServiceTest {

	// Arrange
	final String filename= "testFilename";

	final String client= "testClient";

	final String environment= "testEnvironment";

	final TypeConverter typeConverter= new OracleTypeConverter();

	final MockMeasurementRepository<Object> repository= new MockMeasurementRepository<>();

	final MockImportedFileRepository fileRepository= new MockImportedFileRepository();

	final ServiceContext<Object> context= new ServiceContext<Object>(client, environment, typeConverter, repository, fileRepository);

	final FileService<Object> fileService= new FileService<Object>(context);

	@Test
	public void testStartFileService () {

		// Act
		fileService.fileImportStart(filename);
		List<ImportedFile> importedFiles= fileRepository.getEntities();

		// Assert
		assertThat(importedFiles.size(), is(1));
		ImportedFile importedFile= importedFiles.get(0);
		assertThat(importedFile.getClient(), is(client));
		assertThat(importedFile.getEnvironment(), is(environment));
		assertThat(importedFile.getStatus(), is(Status.NEW.toString()));
		assertThat(importedFile.getImportDatetime(), notNullValue());
	}

	@Test
	public void testFinishFileService () {

		// Act
		fileService.fileImportStart(filename);
		fileService.fileImportFinish(filename);
		List<ImportedFile> importedFiles= fileRepository.getEntities();

		// Assert
		assertThat(importedFiles.size(), is(1));
		ImportedFile importedFile= importedFiles.get(0);
		assertThat(importedFile.getClient(), is(client));
		assertThat(importedFile.getEnvironment(), is(environment));
		assertThat(importedFile.getStatus(), is(Status.DONE.toString()));
		assertThat(importedFile.getImportDatetime(), notNullValue());
	}

	@Test
	public void testAbortFileService () {

		// Arrange
		String exceptionMessage= "test exception in testAbortFileService()";

		// Act
		fileService.fileImportStart(filename);
		fileService.fileImportAbort(filename, new IllegalStateException(exceptionMessage));
		List<ImportedFile> importedFiles= fileRepository.getEntities();

		// Assert
		assertThat(importedFiles.size(), is(1));
		ImportedFile importedFile= importedFiles.get(0);
		assertThat(importedFile.getClient(), is(client));
		assertThat(importedFile.getEnvironment(), is(environment));
		assertThat(importedFile.getStatus(), is(Status.ERROR.toString()));
		assertThat(importedFile.getImportDatetime(), notNullValue());
		assertThat(importedFile.getStacktrace(), containsString(exceptionMessage));
		assertThat(importedFile.getStacktrace(), containsString("IllegalStateException"));
	}

}
