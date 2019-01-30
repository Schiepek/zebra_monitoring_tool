/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.file;

import java.math.BigInteger;
import java.util.List;

import ch.ergon.zebra.monitoring.data.Repository;
import ch.ergon.zebra.monitoring.data.querydsl.domain.ImportedFile;

public interface ImportedFileRepository extends Repository<ImportedFile> {

	List<ImportedFile> findByFilename (String client, String environment, String filename);

	void setError (String client, String environment, String filename, String stacktrace);

	void setDone (String client, String environment, String filename);

	public void deleteByFilename (String client, String environment, String filename);

	BigInteger save (ImportedFile importedFile);

}
