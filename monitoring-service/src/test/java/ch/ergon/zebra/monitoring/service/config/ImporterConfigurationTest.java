/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.config;

import org.junit.Test;

import ch.ergon.zebra.monitoring.service.service.ImporterConfig;

public class ImporterConfigurationTest {

	@Test(expected= NullPointerException.class)
	public void testPendingDirectoryNull () {
		new ImporterConfig("name", "client", "environment", null, "path/done", null);
	}

	@Test(expected= NullPointerException.class)
	public void testDoneDirectoryNull () {
		new ImporterConfig("name", "client", "environment", "path/pending", null, null);
	}

	@Test(expected= NullPointerException.class)
	public void testFailedDirectoryNull () {
		new ImporterConfig("name", "client", "environment", null, null, "path/failed");
	}

}
