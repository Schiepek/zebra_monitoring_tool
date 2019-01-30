/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.file;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;

import ch.ergon.zebra.monitoring.data.file.ImportedFileRepository;
import ch.ergon.zebra.monitoring.data.file.Status;
import ch.ergon.zebra.monitoring.data.querydsl.domain.ImportedFile;
import ch.ergon.zebra.monitoring.service.service.ServiceContext;

public class FileService<T> {

	private final String client;

	private final String environment;

	private final ImportedFileRepository fileRepository;

	public FileService (ServiceContext<T> serviceContext) {
		this.client= serviceContext.getClient();
		this.environment= serviceContext.getEnvironment();
		this.fileRepository= serviceContext.getFileRepository();
	}

	public void fileImportStart (String filename) {
		fileRepository.deleteByFilename(client, environment, filename);
		createImportedFile(filename);
	}

	public void fileImportAbort (String filename, Exception e) {
		fileRepository.setError(client, environment, filename, ExceptionUtils.getStackTrace(e));
	}

	public void fileImportFinish (String filename) {
		fileRepository.setDone(client, environment, filename);
	}

	private void createImportedFile (String filename) {
		final ImportedFile importedFile= new ImportedFile();
		importedFile.setClient(client);
		importedFile.setEnvironment(environment);
		importedFile.setFilename(filename);
		importedFile.setStatus(Status.NEW.toString());
		importedFile.setImportDatetime(new Timestamp(new Date().getTime()));

		fileRepository.save(importedFile);
	}

}
