/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.elasticsearch;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

public class ElasticsearchRepository {

	private final Client client;

	public ElasticsearchRepository (Client client) {
		this.client= client;
	}

	public long getMaxId (String aliasName, ES_TYPE type) {
		final boolean exists= client.admin().indices()
			.prepareAliasesExist(aliasName)
			.execute()
			.actionGet()
			.isExists();

		if (exists) {
			final SearchResponse response= client.prepareSearch(aliasName)
				.setTypes(type.getEsKey())
				.setQuery(QueryBuilders.matchAllQuery())
				.addSort("id", SortOrder.DESC)
				.setSize(1)
				.execute().actionGet();

			if (response.getHits().getHits().length == 1) {
				return Long.valueOf(response.getHits().getAt(0).getId());
			}
		}

		return 0;
	}

	public void ensureIndexExists (String indexName, String aliasName) {
		if (!indexExists(client, indexName)) {
			createIndex(client, indexName);
			updateAlias(client, indexName, aliasName);
		}
	}

	private static boolean indexExists (Client client, String indexName) {
		return client.admin().indices()
			.exists(new IndicesExistsRequest(indexName))
			.actionGet()
			.isExists();
	}

	private static void createIndex (Client client, String indexName) {
		final CreateIndexRequest createIndexRequest= new CreateIndexRequest(indexName);
		client.admin().indices()
			.create(createIndexRequest).actionGet();
	}

	private static void updateAlias (Client client, String indexName, String aliasName) {
		client.admin().indices()
			.prepareAliases()
			.addAlias(indexName, aliasName)
			.execute()
			.actionGet();
	}

}
