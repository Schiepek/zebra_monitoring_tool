/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data;

import com.google.common.base.Preconditions;

public class DatabaseConfig {

	private final String jdbcUrl;

	private final String jdbcDriver;

	private final String username;

	private final String password;

	public DatabaseConfig (String jdbcUrl, String jdbcDriver, String username, String password) {
		this.jdbcUrl= Preconditions.checkNotNull(jdbcUrl, "Field \"jdbcUrl\" can not be null");
		this.jdbcDriver= Preconditions.checkNotNull(jdbcDriver, "Field \"jdbcDriver\" can not be null");
		this.username= Preconditions.checkNotNull(username, "Field \"username\" can not be null");;
		this.password= Preconditions.checkNotNull(password, "Field \"password\" can not be null");;
	}

	public String getJdbcUrl () {
		return jdbcUrl;
	}

	public String getJdbcDriver () {
		return jdbcDriver;
	}

	public String getUsername () {
		return username;
	}

	public String getPassword () {
		return password;
	}

}
