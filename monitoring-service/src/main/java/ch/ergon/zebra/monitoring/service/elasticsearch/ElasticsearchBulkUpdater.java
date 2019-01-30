/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.elasticsearch;

import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticsearchBulkUpdater {

	private static final Logger LOG= LoggerFactory.getLogger(ElasticsearchBulkUpdater.class);

	private static final int BULK_ACTIONS= 5000;

	private static final int BYTE_SIZE_IN_MB= 50;

	private static final int FLUSH_INTERVAL_IN_SECONDS= 30;

	private final BulkProcessor bulkProcessor;

	public ElasticsearchBulkUpdater (Client client) {
		bulkProcessor= BulkProcessor.builder(
			client,
			new BulkProcessor.Listener() {

				@Override
				public void beforeBulk (long executionId,
					BulkRequest request) {
				}

				@Override
				public void afterBulk (long executionId,
					BulkRequest request,
					BulkResponse response) {
				}

				@Override
				public void afterBulk (long executionId, BulkRequest request, Throwable failure) {
					if (failure != null) {
						String message= "Bulk update failed: " + failure.getMessage();
						LOG.info(message);
					}
				}
			})
			.setBulkActions(BULK_ACTIONS)
			.setBulkSize(new ByteSizeValue(BYTE_SIZE_IN_MB, ByteSizeUnit.MB))
			.setFlushInterval(TimeValue.timeValueSeconds(FLUSH_INTERVAL_IN_SECONDS))
			.setConcurrentRequests(1)
			.build();
	}

	public void process (IndexRequest request) {
		bulkProcessor.add(request);
	}

	public void close () {
		bulkProcessor.close();
	}

}
