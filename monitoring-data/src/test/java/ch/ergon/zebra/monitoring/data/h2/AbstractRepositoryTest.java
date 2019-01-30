/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.h2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;

import ch.ergon.zebra.monitoring.data.ConnectionFactory;
import ch.ergon.zebra.monitoring.data.DatabaseConfig;

import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.H2Templates;

public abstract class AbstractRepositoryTest {

	private static final String USERNAME= "";

	private static final String PASSWORD= "";

	private static final String JDBC_DRIVER= "org.h2.Driver";

	private static final String JDBC_URL= "jdbc:h2:mem:test";

	private static final String FLYWAY_H2_MIGRATION_LOCATION= "classpath:/db/migration/h2";

	private final Configuration templates= new Configuration(new H2Templates());

	private H2ConnectionFactory factory;

	private Connection keepAliveConnection;

	@Before
	public final void setUp () throws SQLException {
		final DatabaseConfig config= new DatabaseConfig(JDBC_URL, JDBC_DRIVER, USERNAME, PASSWORD);
		factory= new H2ConnectionFactory(config);

		keepAliveConnection= factory.getConnection();

		flywayMigrate();

		doSetUp();
	}

	@After
	public final void tearDown () throws SQLException {
		keepAliveConnection.close();
	}

	protected abstract void doSetUp ();

	protected final Timestamp addDays (Date date, int counter) {
		Calendar cal= Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, counter);
		return new Timestamp(cal.getTime().getTime());
	}

	protected final ConnectionFactory getConnectionFactory () {
		return factory;
	}

	protected final Configuration getTemplates () {
		return templates;
	}

	private void flywayMigrate () {
		Flyway flyway= new Flyway();
		flyway.setDataSource(JDBC_URL, USERNAME, PASSWORD);
		flyway.setLocations(FLYWAY_H2_MIGRATION_LOCATION);
		flyway.migrate();
	}

}
