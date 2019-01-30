/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.file;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import ch.ergon.zebra.monitoring.data.AbstractSqlRepository;
import ch.ergon.zebra.monitoring.data.ConnectionFactory;
import ch.ergon.zebra.monitoring.data.Statement;
import ch.ergon.zebra.monitoring.data.querydsl.domain.ImportedFile;
import ch.ergon.zebra.monitoring.data.querydsl.metadata.QImportedFile;

import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.dml.SQLInsertClause;

public class SqlImportedFileRepository extends AbstractSqlRepository implements ImportedFileRepository {

	public final QImportedFile qImportedFile= new QImportedFile("IMPORTED_FILE", getDbScheme(), "IMPORTED_FILE");

	public SqlImportedFileRepository (Configuration configuration, ConnectionFactory factory) {
		super(configuration, factory);
	}

	@Override
	public ImportedFile findById (long id) {
		final Connection connection= getConnection();
		try {
			final Statement<ImportedFile> statement= ()
				-> createQuery(connection)
					.from(qImportedFile)
					.where(qImportedFile.id.eq(BigInteger.valueOf(id)))
					.singleResult(qImportedFile);
			return transaction(statement, connection);
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	@Override
	public void save (List<ImportedFile> importedFiles) {
		final Connection connection= getConnection();
		try {
			final Statement<Void> statement= createInsertStatement(importedFiles, connection);
			transaction(statement, connection);
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	@Override
	public void deleteByFilename (String client, String environment, String filename) {
		final Connection connection= getConnection();
		try {
			final Statement<Long> statement= () ->
				createDeleteClause(qImportedFile, connection)
					.where(qImportedFile.client.eq(client)
						.and(qImportedFile.environment.eq(environment))
						.and(qImportedFile.filename.eq(filename)))
					.execute();
			transaction(statement, connection);
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	@Override
	public List<ImportedFile> findByFilename (String client, String environment, String filename) {
		final Connection connection= getConnection();
		try {
			final Statement<List<ImportedFile>> statement= ()
				-> createQuery(connection)
					.from(qImportedFile)
					.where(qImportedFile.client.eq(client)
						.and(qImportedFile.environment.eq(environment))
						.and(qImportedFile.filename.eq(filename)))
					.list(qImportedFile);
			return transaction(statement, connection);
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	@Override
	public void setError (String client, String environment, String filename, String stacktrace) {
		final Connection connection= getConnection();
		try {
			final Statement<Long> statement= () ->
				createUpdateClause(qImportedFile, connection)
					.where(qImportedFile.client.eq(client)
						.and(qImportedFile.environment.eq(environment))
						.and(qImportedFile.filename.eq(filename)))
					.set(qImportedFile.status, Status.ERROR.toString())
					.set(qImportedFile.stacktrace, stacktrace)
					.execute();
			transaction(statement, connection);
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	@Override
	public void setDone (String client, String environment, String filename) {
		final Connection connection= getConnection();
		try {
			final Statement<Long> statement= () ->
				createUpdateClause(qImportedFile, connection)
					.where(qImportedFile.client.eq(client)
						.and(qImportedFile.environment.eq(environment))
						.and(qImportedFile.filename.eq(filename)))
					.set(qImportedFile.status, Status.DONE.toString())
					.execute();
			transaction(statement, connection);
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	@Override
	public BigInteger save (ImportedFile importedFile) {
		final Connection connection= getConnection();
		try {
			final Statement<BigInteger> statement= () ->
				createInsertClause(qImportedFile, connection)
					.populate(importedFile)
					.executeWithKey(BigInteger.class);
			return transaction(statement, connection);
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	private Statement<Void> createInsertStatement (List<ImportedFile> importedFile, Connection connection) {
		return new Statement<Void>() {

			@Override
			public Void execute () {
				final SQLInsertClause clause= createInsertClause(qImportedFile, connection);
				for (final ImportedFile entity: importedFile) {
					clause.populate(entity).addBatch();
				}
				clause.execute();
				return null;
			}

		};
	}

}
