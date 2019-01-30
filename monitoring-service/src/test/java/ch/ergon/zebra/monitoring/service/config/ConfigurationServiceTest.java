/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ch.ergon.zebra.monitoring.data.DatabaseConfig;
import ch.ergon.zebra.monitoring.service.elasticsearch.ElasticsearchConfig;
import ch.ergon.zebra.monitoring.service.service.ImporterConfig;

public class ConfigurationServiceTest {

	private final static String TEST_XML_PATH= "/ch/ergon/zebra/monitoring/service/config/test-import.xml";

	private static ConfigurationService CONFIGURATION_SERVICE;

	@BeforeClass
	public static void initializeConfigurationParser () {
		InputStream inputStream= ClassLoader.class.getResourceAsStream(TEST_XML_PATH);
		CONFIGURATION_SERVICE= new ConfigurationService(new BufferedInputStream(inputStream));
	}

	@Test
	public void testGetDatabaseConfig () {
		DatabaseConfig databaseConfig= CONFIGURATION_SERVICE.getDatabaseConfig();
		assertThat(databaseConfig.getJdbcUrl(), is("testJdbcUrl"));
		assertThat(databaseConfig.getUsername(), is("testUsername"));
		assertThat(databaseConfig.getPassword(), is("testPassword"));
	}

	@Test
	public void testGetElasticsearchConfig () {
		ElasticsearchConfig elasticsearchConfig= CONFIGURATION_SERVICE.getElasticsearchConfig();
		assertThat(elasticsearchConfig.getClient(), is("testClient"));
		assertThat(elasticsearchConfig.getEnvironment(), is("testEnvironment"));
		assertThat(elasticsearchConfig.getClusterName(), is("testCluster"));
		assertThat(elasticsearchConfig.getAliasName(), is("testIndex"));
		assertThat(elasticsearchConfig.getHost(), is("111.222.333.444"));
		assertThat(elasticsearchConfig.getPort(), is(1234));
	}

	@Test
	public void testGetImporterConfigurations () {
		List<ImporterConfig> importerConfigurations= CONFIGURATION_SERVICE.getImporterConfigs();
		assertThat(importerConfigurations.size(), is(2));

		ImporterConfig testConfigurationA= importerConfigurations.get(0);
		assertThat(testConfigurationA.getClient(), is("testClient"));
		assertThat(testConfigurationA.getEnvironment(), is("testEnvironment"));
		assertThat(testConfigurationA.getName(), is("testImporterA"));
		assertThat(testConfigurationA.getPendingDirectory(), is(Paths.get("/etc/monitoring/pending")));
		assertThat(testConfigurationA.getDoneDirectory(), is(Paths.get("/etc/monitoring/done")));
		assertThat(testConfigurationA.getFailedDirectory(), is(Paths.get("/etc/monitoring/failed")));

		ImporterConfig testConfigurationB= importerConfigurations.get(1);
		assertThat(testConfigurationB.getClient(), is("testClient"));
		assertThat(testConfigurationB.getEnvironment(), is("testEnvironment"));
		assertThat(testConfigurationB.getName(), is("testImporterB"));
		assertThat(testConfigurationB.getPendingDirectory(), is(Paths.get("/etc/monitoring/pending")));
		assertThat(testConfigurationB.getDoneDirectory(), is(Paths.get("/etc/monitoring/done")));
		assertThat(testConfigurationB.getFailedDirectory(), is(Paths.get("/etc/monitoring/failed")));
	}

	@Test(expected= IllegalStateException.class)
	public void testWrongXmlPath () {
		final String testXmlPath= "/ch/ergon/zebra/monitoring/importer/wrongPath.xml";
		InputStream inputStream= ClassLoader.class.getResourceAsStream(testXmlPath);
		CONFIGURATION_SERVICE= new ConfigurationService(new BufferedInputStream(inputStream));
	}

}
