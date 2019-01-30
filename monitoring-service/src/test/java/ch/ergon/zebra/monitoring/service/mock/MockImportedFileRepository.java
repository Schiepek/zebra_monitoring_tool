/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.mock;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import ch.ergon.zebra.monitoring.data.file.ImportedFileRepository;
import ch.ergon.zebra.monitoring.data.file.Status;
import ch.ergon.zebra.monitoring.data.querydsl.domain.ImportedFile;

public class MockImportedFileRepository implements ImportedFileRepository {

	private final List<ImportedFile> files= new ArrayList<>();

	public List<ImportedFile> getEntities () {
		return files;
	}

	@Override
	public void save (List<ImportedFile> entities) {
	}

	@Override
	public BigInteger save (ImportedFile importedFile) {
		files.add(importedFile);
		return null;
	}

	@Override
	public void setError (String client, String environment, String filename, String stacktrace) {
		List<ImportedFile> importedFiles= findByFilename(client, environment, filename);
		importedFiles.forEach( (file) -> {
			file.setStatus(Status.ERROR.toString());
			file.setStacktrace(stacktrace);
		});
	}

	@Override
	public void setDone (String client, String environment, String filename) {
		List<ImportedFile> importedFiles= findByFilename(client, environment, filename);
		importedFiles.forEach( (file) -> file.setStatus(Status.DONE.toString()));
	}

	@Override
	public ImportedFile findById (long id) {
		return null;
	}

	@Override
	public void deleteByFilename (String client, String environment, String filename) {
		List<ImportedFile> importedFiles= findByFilename(client, environment, filename);
		importedFiles.forEach( (file) -> files.remove(file));
	}

	@Override
	public List<ImportedFile> findByFilename (String client, String environment, String filename) {
		List<ImportedFile> importedFiles= new ArrayList<>();
		for (ImportedFile file: files) {
			if (file.getClient().equals(client) && file.getEnvironment().equals(environment) && file.getFilename().equals(filename)) {
				importedFiles.add(file);

			}
		}
		return importedFiles;
	}

};
