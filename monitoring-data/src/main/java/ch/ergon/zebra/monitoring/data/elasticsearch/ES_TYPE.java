/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.elasticsearch;

public enum ES_TYPE {

	PROFILING("profiling"),

	CPU_USAGE("cpu_usage"),

	MEMORY_USAGE("memory_usage");

	private final String esKey;

	private ES_TYPE (String esKey) {
		this.esKey= esKey;
	}

	public String getEsKey () {
		return esKey;
	}

	@Override
	public String toString () {
		return esKey;
	}

}
