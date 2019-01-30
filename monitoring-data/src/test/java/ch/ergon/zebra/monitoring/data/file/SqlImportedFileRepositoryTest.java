/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.file;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.sql.Timestamp;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import ch.ergon.zebra.monitoring.data.h2.AbstractRepositoryTest;
import ch.ergon.zebra.monitoring.data.querydsl.domain.ImportedFile;

import com.google.common.collect.Lists;

public class SqlImportedFileRepositoryTest extends AbstractRepositoryTest {

	private static final Timestamp STARTDATE= new Timestamp(new DateTime(2000, 1, 1, 0, 0, 0, 0).getMillis());

	private SqlImportedFileRepository repository;

	@Override
	protected void doSetUp () {
		repository= new SqlImportedFileRepository(getTemplates(), getConnectionFactory());
	}

	@Test
	public void testFindById () {
		// Arrange
		ImportedFile expectedImportedFile1= createImportedFile("cl_1", "en_1", STARTDATE, "15_02.zip", Status.DONE);
		ImportedFile expectedImportedFile2= createImportedFile("cl_2", "en_2", STARTDATE, "15_03.zip", Status.ERROR);
		List<ImportedFile> importedFiles= Lists.newArrayList(expectedImportedFile1, expectedImportedFile2);

		// Act
		repository.save(importedFiles);

		ImportedFile actualImportedFile1= repository.findById(1);
		ImportedFile actualImportedFile2= repository.findById(2);

		// Assert
		assertEqualsImportedFile(actualImportedFile1, expectedImportedFile1);
		assertEqualsImportedFile(actualImportedFile2, expectedImportedFile2);

	}

	private static void assertEqualsImportedFile (ImportedFile actualImportedFile, ImportedFile expectedImportedFile) {
		assertThat(actualImportedFile, not(nullValue()));
		assertThat(actualImportedFile.getImportDatetime(), is(expectedImportedFile.getImportDatetime()));
		assertThat(actualImportedFile.getClient(), is(expectedImportedFile.getClient()));
		assertThat(actualImportedFile.getEnvironment(), is(expectedImportedFile.getEnvironment()));
	}

	private static ImportedFile createImportedFile (String client, String environment, Timestamp sysDatetime, String filename, Status status) {
		ImportedFile importedFile= new ImportedFile();
		importedFile.setClient(client);
		importedFile.setEnvironment(environment);
		importedFile.setImportDatetime(sysDatetime);
		importedFile.setFilename(filename);
		importedFile.setStatus(status.toString());
		return importedFile;
	}

}
