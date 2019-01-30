/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.elasticsearch;

import org.elasticsearch.client.Client;

import ch.ergon.zebra.monitoring.data.ConnectionFactory;
import ch.ergon.zebra.monitoring.data.cpu.CpuUsageRepository;
import ch.ergon.zebra.monitoring.data.cpu.SqlCpuUsageRepository;
import ch.ergon.zebra.monitoring.data.elasticsearch.ES_TYPE;
import ch.ergon.zebra.monitoring.data.elasticsearch.ElasticsearchRepository;
import ch.ergon.zebra.monitoring.data.measurement.MeasurementRepository;
import ch.ergon.zebra.monitoring.data.memory.MemoryUsageRepository;
import ch.ergon.zebra.monitoring.data.memory.SqlMemoryUsageRepository;
import ch.ergon.zebra.monitoring.data.profiling.SqlUseCaseProfilerRepository;
import ch.ergon.zebra.monitoring.data.profiling.UseCaseProfilerRepository;
import ch.ergon.zebra.monitoring.service.elasticsearch.converter.ElasticsearchConverter;
import ch.ergon.zebra.monitoring.service.elasticsearch.converter.ElasticsearchCpuUsageConverter;
import ch.ergon.zebra.monitoring.service.elasticsearch.converter.ElasticsearchMemoryUsageConverter;
import ch.ergon.zebra.monitoring.service.elasticsearch.converter.ElasticsearchProfilingConverter;

import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.OracleTemplates;

public class ElasticsearchService {

	private final ConnectionFactory connectionFactory;

	private final ElasticsearchClientFactory clientFactory;

	private final ElasticsearchConfig elasticsearchConfig;

	private final Configuration databaseTemplates;

	public ElasticsearchService (ConnectionFactory connectionFactory, ElasticsearchClientFactory clientFactory,
		ElasticsearchConfig elasticsearchConfig) {
		this.connectionFactory= connectionFactory;
		this.clientFactory= clientFactory;
		this.elasticsearchConfig= elasticsearchConfig;
		databaseTemplates= new Configuration(new OracleTemplates());
	}

	public void exportToElasticsearch () {
		processProfilingIfNeeded();
		processMemoryIfNeeded();
		processCpuIfNeeded();
	}

	private void processCpuIfNeeded () {
		final CpuUsageRepository repository= new SqlCpuUsageRepository(databaseTemplates, connectionFactory);
		final ElasticsearchConverter converter= new ElasticsearchCpuUsageConverter();

		processMeasurement(repository, converter, ES_TYPE.CPU_USAGE);
	}

	private void processMemoryIfNeeded () {
		final MemoryUsageRepository repository= new SqlMemoryUsageRepository(databaseTemplates, connectionFactory);
		final ElasticsearchConverter converter= new ElasticsearchMemoryUsageConverter();

		processMeasurement(repository, converter, ES_TYPE.MEMORY_USAGE);
	}

	private void processProfilingIfNeeded () {
		final UseCaseProfilerRepository repository= new SqlUseCaseProfilerRepository(databaseTemplates, connectionFactory);
		final ElasticsearchConverter converter= new ElasticsearchProfilingConverter();

		processMeasurement(repository, converter, ES_TYPE.PROFILING);
	}

	private <T> void processMeasurement (MeasurementRepository<T> repository, ElasticsearchConverter converter, ES_TYPE type) {
		if (repository.dataExists(elasticsearchConfig.getClient(), elasticsearchConfig.getEnvironment())) {
			final Client client= clientFactory.getClient(elasticsearchConfig);
			try {
				final ElasticsearchRepository elasticsearchRepository= new ElasticsearchRepository(client);
				final long maxId= elasticsearchRepository.getMaxId(elasticsearchConfig.getAliasName(), type);

				final ElasticsearchFeeder<T> feeder= new ElasticsearchFeeder<T>(type, repository, converter, client, elasticsearchConfig);
				feeder.execute(maxId);
			} finally {
				client.close();
			}
		}
	}

}
