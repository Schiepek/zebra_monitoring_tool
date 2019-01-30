/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.elasticsearch;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang3.time.StopWatch;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.collect.Sets;
import org.elasticsearch.common.lang3.tuple.Pair;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ergon.zebra.monitoring.data.elasticsearch.ES_TYPE;
import ch.ergon.zebra.monitoring.data.elasticsearch.ElasticsearchRepository;
import ch.ergon.zebra.monitoring.data.measurement.MeasurementRepository;
import ch.ergon.zebra.monitoring.service.elasticsearch.converter.ElasticsearchConverter;
import ch.ergon.zebra.monitoring.service.elasticsearch.util.ElasticsearchTypeMappingUtil;

import com.mysema.query.Tuple;

public class ElasticsearchFeeder<T> {

	private static final Logger LOG= LoggerFactory.getLogger(ElasticsearchFeeder.class);

	private static final long LIMIT= 50000;

	private final MeasurementRepository<T> measurementRepository;

	private final ElasticsearchConverter converter;

	private final Client client;

	private final ElasticsearchConfig elasticsearchConfig;

	private final ElasticsearchRepository elasticsearchRepository;

	private final String aliasName;

	private final Set<String> existingIndexes= Sets.newHashSet();

	private final ES_TYPE type;

	private final NumberFormat numberFormatter;

	public ElasticsearchFeeder (ES_TYPE type, MeasurementRepository<T> measurementRepository, ElasticsearchConverter mapping, Client client,
		ElasticsearchConfig elasticsearchConfig) {
		this.type= type;
		this.measurementRepository= measurementRepository;
		this.converter= mapping;
		this.client= client;
		this.elasticsearchConfig= elasticsearchConfig;

		aliasName= elasticsearchConfig.getAliasName();
		elasticsearchRepository= new ElasticsearchRepository(client);

		numberFormatter= createNumberFormat();
	}

	/**
	 * @param maxEsId Records with id >= maxEsId will be exported to Elasticsearch database.
	 */
	public void execute (long maxEsId) {
		final ElasticsearchBulkUpdater updater= new ElasticsearchBulkUpdater(client);
		try {
			execute(updater, maxEsId);
		} finally {
			updater.close();
		}
	}

	private void execute (ElasticsearchBulkUpdater updater, long maxEsId) {
		final Long maxDbId= measurementRepository.getMaxId(elasticsearchConfig.getClient(), elasticsearchConfig.getEnvironment());
		if (maxDbId == null) {
			LOG.info("Database contains no data: client={}, environment={}",
				elasticsearchConfig.getClient(), elasticsearchConfig.getEnvironment());
		}

		LOG.info("Exporting values to elasticsearch database: maxEsId={}, maxDbId={}",
			numberFormatter.format(maxEsId),
			numberFormatter.format(maxDbId));

		//The next id to insert is the largest id in the elasticsearch db + 1;
		long offset= maxEsId + 1;

		List<Tuple> tuples= null;
		do {
			// Timing
			final StopWatch stopWatch= new StopWatch();
			stopWatch.start();

			// Search elements in oracle database
			tuples= measurementRepository.findForElasticSearch(elasticsearchConfig.getClient(),
				elasticsearchConfig.getEnvironment(), offset, LIMIT);

			// Timing
			final long timeToFindTuples= stopWatch.getTime();

			// Insert in to elasticserach db
			for (Tuple tuple: tuples) {
				insertTuple(tuple, updater);
			}

			// Timing
			final long timeToInsertTuples= stopWatch.getTime() - timeToFindTuples;
			stopWatch.stop();

			logInsertion(tuples.size(), timeToFindTuples, timeToInsertTuples, offset);

			offset+= LIMIT;
		} while (!tuples.isEmpty() || offset <= maxDbId);

	}

	private void insertTuple (Tuple tuple, ElasticsearchBulkUpdater updater) {
		final String indexName= converter.getIndexName(aliasName, tuple);

		if (!existingIndexes.contains(indexName)) {
			// Ensure that the index exists
			elasticsearchRepository.ensureIndexExists(indexName, aliasName);
			existingIndexes.add(indexName);

			// Create/Update the mapping
			ElasticsearchTypeMappingUtil.initializeTypeMapping(client, type, indexName);
		}

		// Insert the value
		Pair<String, XContentBuilder> source= converter.getSource(tuple);
		String type= converter.getEsKey();
		String id= source.getKey();
		XContentBuilder value= source.getValue();

		updater.process(new IndexRequest(indexName, type, id).source(value));
	}

	private void logInsertion (int numberOfTransferedElements, long timeToFindTuples, long timeToInsertTuples, long offset) {
		final StringBuilder sb= new StringBuilder();
		if (numberOfTransferedElements == 0) {
			sb.append("No Elements transfered. Offset: ");
			sb.append(numberFormatter.format(offset));
			sb.append(", find tuples: ");
			sb.append(timeToFindTuples / 1000);
			sb.append(" seconds");
		} else {
			sb.append("Transfer of ");
			sb.append(numberOfTransferedElements);
			sb.append(" elements successfull. Offset: ");
			sb.append(numberFormatter.format(offset));
			sb.append(", find tuples: ");
			sb.append(timeToFindTuples / 1000);
			sb.append(" seconds");
			sb.append(", insert tuples: ");
			sb.append(timeToInsertTuples / 1000);
			sb.append(" seconds");
		}
		LOG.info(sb.toString());
	}

	private static NumberFormat createNumberFormat () {
		final NumberFormat format= NumberFormat.getInstance(new Locale("de", "ch"));

		return format;
	}

}
