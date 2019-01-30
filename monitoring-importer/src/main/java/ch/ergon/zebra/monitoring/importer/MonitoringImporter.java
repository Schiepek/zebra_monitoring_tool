/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */
package ch.ergon.zebra.monitoring.importer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ergon.zebra.monitoring.data.ConnectionFactory;
import ch.ergon.zebra.monitoring.data.DatabaseConfig;
import ch.ergon.zebra.monitoring.data.HikariConnectionFactory;
import ch.ergon.zebra.monitoring.service.config.ConfigurationService;
import ch.ergon.zebra.monitoring.service.service.ImporterConfig;
import ch.ergon.zebra.monitoring.service.version.Version;
import ch.ergon.zebra.monitoring.service.version.VersionService;

public class MonitoringImporter {

	private static final Logger LOG= LoggerFactory.getLogger(MonitoringImporter.class);

	private static final String OPTION_CONFIG= "config";

	public static void main (String[] args) {
		final Options options= getOptions();

		try {
			final CommandLineParser parser= new BasicParser();
			final CommandLine line= parser.parse(options, args);

			if (line.hasOption(OPTION_CONFIG)) {
				logVersion();

				final InputStream inputStream= getInputStream(line.getOptionValue(OPTION_CONFIG));
				final ConfigurationService configurationService= new ConfigurationService(inputStream);
				final DatabaseConfig databaseConfig= configurationService.getDatabaseConfig();
				final List<ImporterConfig> importerConfigs= configurationService.getImporterConfigs();
				final ConnectionFactory connectionFactory= new HikariConnectionFactory(databaseConfig);

				importerConfigs.forEach( (config) -> ImporterFactory.createImporter(config, connectionFactory).execute());

				LOG.info("Goodbye");
			} else if (line.hasOption("version")) {
				logVersion();
			} else {
				printHelp(options);
			}
		} catch (ParseException e) {
			printHelp(options);
		} catch (Exception e) {
			LOG.error("Importer failed unexpectedly", e);
		}
	}

	private static void logVersion () {
		final VersionService versionService= new VersionService();
		final Version version= versionService.getVersion();

		LOG.info("Version={}, Timestamp={}", version.getVersion(), version.getTimestamp());
	}

	private static InputStream getInputStream (final String configPath) {
		try {
			final InputStream inputStream= new FileInputStream(configPath);
			return new BufferedInputStream(inputStream);
		} catch (FileNotFoundException e) {
			String message= "Failed to load configuration: " + configPath;
			LOG.error(message, e);
			throw new IllegalArgumentException(message, e);
		}
	}

	private static Options getOptions () {
		final Option version= new Option("version", "print the version information and exit");

		@SuppressWarnings("static-access")
		Option config= OptionBuilder.withArgName("path")
			.hasArg()
			.withDescription("path of the config xml file")
			.create("config");

		final Options options= new Options();
		options.addOption(version);
		options.addOption(config);

		return options;
	}

	private static void printHelp (final Options options) {
		final HelpFormatter formatter= new HelpFormatter();
		formatter.printHelp("MonitoringImporter", options);
	}

}
