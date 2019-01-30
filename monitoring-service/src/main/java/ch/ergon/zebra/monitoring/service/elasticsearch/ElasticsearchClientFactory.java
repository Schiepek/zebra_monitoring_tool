/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public final class ElasticsearchClientFactory {

	private static final String CLUSTER_NAME= "cluster.name";

	@SuppressWarnings("resource")
	public Client getClient (ElasticsearchConfig config) {
		final Settings settings= ImmutableSettings
			.settingsBuilder()
			.put(CLUSTER_NAME, config.getClusterName())
			.build();

		final Client client= new TransportClient(settings)
			.addTransportAddress(new InetSocketTransportAddress(config.getHost(), config.getPort()));

		return client;
	}
}
