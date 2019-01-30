/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.elasticsearch;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ElasticsearchConfig {

	private final String client;

	private final String environment;

	private final String clusterName;

	private final String aliasName;

	private final String host;

	private final int port;

	public ElasticsearchConfig (String client, String environment, String clusterName, String aliasName, String host, int port) {
		this.client= client;
		this.environment= environment;
		this.clusterName= clusterName;
		this.aliasName= aliasName;
		this.host= host;
		this.port= port;
	}

	public String getClient () {
		return client;
	}

	public String getEnvironment () {
		return environment;
	}

	public String getClusterName () {
		return clusterName;
	}

	public String getAliasName () {
		return aliasName;
	}

	public String getHost () {
		return host;
	}

	public int getPort () {
		return port;
	}

	@Override
	public String toString () {
		final ToStringBuilder builder= new ToStringBuilder(this);
		builder.append("client", client);
		builder.append("environment", environment);
		builder.append("clusterName", clusterName);
		builder.append("indexName", aliasName);
		builder.append("host", host);
		builder.append("port", port);

		return builder.toString();
	}

}
