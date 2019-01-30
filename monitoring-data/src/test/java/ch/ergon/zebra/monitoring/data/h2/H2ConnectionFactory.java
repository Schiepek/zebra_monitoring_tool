/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ergon.zebra.monitoring.data.ConnectionFactory;
import ch.ergon.zebra.monitoring.data.DatabaseConfig;

public class H2ConnectionFactory implements ConnectionFactory {

	private static final Logger LOG= LoggerFactory.getLogger(ConnectionFactory.class);

	private final DatabaseConfig config;

	public H2ConnectionFactory (DatabaseConfig config) {
		this.config= config;
	}

	@Override
	public Connection getConnection () {
		try {
			Class.forName(config.getJdbcDriver());
		} catch (ClassNotFoundException e) {
			String message= "Failed to load jdbc driver " + config.getJdbcDriver();
			LOG.error(message, e);
			throw new IllegalStateException(message, e);
		}
		try {
			return DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword());
		} catch (SQLException e) {
			String message= "Failed to establish connection to H2 Database";
			LOG.error(message, e);
			throw new IllegalStateException(message, e);
		}
	}

	@Override
	public String getDbScheme () {
		return config.getUsername();
	}
}
