/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */
package ch.ergon.zebra.monitoring.service.version;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VersionServiceTest {

	@Test
	public void testVersion () {
		// Arrange
		final String versionFile= "ch/ergon/zebra/monitoring/service/version/test-version.properties";
		final VersionService versionService= new VersionService(versionFile);

		// Act
		final Version version= versionService.getVersion();

		// Assert
		assertThat(version.getVersion(), is("1.5.4"));
		assertThat(version.getTimestamp(), is("20150204-19:25:30"));
	}

}
