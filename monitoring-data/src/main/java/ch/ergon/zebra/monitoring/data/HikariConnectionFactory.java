/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */
package ch.ergon.zebra.monitoring.data;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariConnectionFactory implements ConnectionFactory {

	private static final Logger LOG= LoggerFactory.getLogger(HikariConnectionFactory.class);

	private static final int MAXIMUM_POOL_SIZE= 10;

	private static final int CONNECTION_TIMEOUT_IN_MS= 5000;

	private final HikariDataSource dataSource;

	public HikariConnectionFactory (DatabaseConfig configuration) {
		final HikariConfig hikariConfig= new HikariConfig();
		hikariConfig.setJdbcUrl(configuration.getJdbcUrl());
		hikariConfig.setDriverClassName(configuration.getJdbcDriver());
		hikariConfig.setUsername(configuration.getUsername());
		hikariConfig.setPassword(configuration.getPassword());
		hikariConfig.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
		hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_MS);
		dataSource= new HikariDataSource(hikariConfig);
	}

	@Override
	public Connection getConnection () {
		try {
			Connection connection= dataSource.getConnection();
			setAutoCommit(connection, false);
			return connection;
		} catch (SQLException e) {
			String message= "Failed to get connection";
			LOG.error(message, e);
			throw new IllegalStateException(message, e);
		}
	}

	@Override
	public String getDbScheme () {
		return dataSource.getUsername();
	}

	private void setAutoCommit (Connection connection, boolean autoCommit) {
		try {
			connection.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			String message= "Failed to set autocommit to: " + autoCommit;
			LOG.error(message, e);
			throw new IllegalStateException(message, e);
		}
	}

}
