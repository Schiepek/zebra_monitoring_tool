/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;

public abstract class AbstractSqlRepository {

	private static final Logger LOG= LoggerFactory.getLogger(AbstractSqlRepository.class);

	private final Configuration configuration;

	private final ConnectionFactory factory;

	public AbstractSqlRepository (Configuration configuration, ConnectionFactory factory) {
		this.configuration= configuration;
		this.factory= factory;
	}

	protected Connection getConnection () {
		return factory.getConnection();
	}

	protected SQLQuery createQuery (Connection connection) {
		return new SQLQuery(connection, configuration);
	}

	protected SQLInsertClause createInsertClause (RelationalPath<?> path, Connection connection) {
		return new SQLInsertClause(connection, configuration, path);
	}

	protected SQLUpdateClause createUpdateClause (RelationalPath<?> path, Connection connection) {
		return new SQLUpdateClause(connection, configuration, path);
	}

	protected SQLDeleteClause createDeleteClause (RelationalPath<?> path, Connection connection) {
		return new SQLDeleteClause(connection, configuration, path);
	}

	protected <R> R transaction (Statement<R> function, Connection connection) {
		try {
			R result= function.execute();
			connection.commit();
			return result;
		} catch (Exception e) {
			rollbackQuietly(connection);
			throw new IllegalStateException("Transaction failed, rollback executed", e);
		}
	}

	protected <R> List<R> multipleStatementTransaction (List<Statement<R>> functions, Connection connection) {
		List<R> results= new ArrayList<>();
		try {
			for (Statement<R> function: functions) {
				R result= function.execute();
				results.add(result);
			}
			connection.commit();
			return results;
		} catch (Exception e) {
			rollbackQuietly(connection);
			throw new IllegalStateException("Transaction failed, rollback executed", e);
		}
	}

	protected String getDbScheme () {
		return factory.getDbScheme();
	}

	private static void rollbackQuietly (Connection connection) {
		try {
			connection.rollback();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
