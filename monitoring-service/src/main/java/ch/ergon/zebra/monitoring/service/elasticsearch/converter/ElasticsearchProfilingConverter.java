/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.elasticsearch.converter;

import java.sql.Timestamp;

import org.elasticsearch.common.xcontent.XContentBuilder;

import ch.ergon.zebra.monitoring.data.elasticsearch.ES_TYPE;
import ch.ergon.zebra.monitoring.data.querydsl.metadata.QProfiling;

import com.mysema.query.Tuple;

public class ElasticsearchProfilingConverter extends AbstractElasticsearchConverter {

	private static final ES_TYPE TYPE= ES_TYPE.PROFILING;

	private final QProfiling qProfiling= new QProfiling(TYPE.toString().toUpperCase());

	@Override
	public String getEsKey () {
		return TYPE.getEsKey();
	}

	@Override
	protected void addFields (XContentBuilder sourceBuilder, Tuple tuple) {
		addField(sourceBuilder, "id", tuple.get(qProfiling.id));
		addField(sourceBuilder, "focusDate", tuple.get(qProfiling.focusDate));
		addField(sourceBuilder, "focusKstNummer", tuple.get(qProfiling.focusKstNummer));
		addField(sourceBuilder, "serverExecutionTimeMs", tuple.get(qProfiling.serverExecutionTimeMs));
		addField(sourceBuilder, "totalTimeDuration", tuple.get(qProfiling.totalTimeDuration));
		addField(sourceBuilder, "latencyDuration", tuple.get(qProfiling.latencyDuration));
		addField(sourceBuilder, "pageLoadDuration", tuple.get(qProfiling.pageLoadDuration));
		addField(sourceBuilder, "userAgent", tuple.get(qProfiling.userAgent));
		addField(sourceBuilder, "loginName", tuple.get(qProfiling.loginName));
		addField(sourceBuilder, "usecaseId", tuple.get(qProfiling.usecaseId));
		addField(sourceBuilder, "zebraVersion", tuple.get(qProfiling.zebraVersion));
		addField(sourceBuilder, "activeSessions1", tuple.get(qProfiling.activeSessions1));
		addField(sourceBuilder, "sysDatetime", getSysDatetime(tuple));
		addField(sourceBuilder, "totalExecutionTimeMs", tuple.get(qProfiling.totalExecutionTimeMs));
		addField(sourceBuilder, "totalFetchTimeMs", tuple.get(qProfiling.totalFetchTimeMs));
		addField(sourceBuilder, "totalDatabaseTimeMs", tuple.get(qProfiling.totalDatabaseTimeMs));
	}

	@Override
	protected Timestamp getSysDatetime (Tuple tuple) {
		return tuple.get(qProfiling.sysDatetime);
	}

	@Override
	protected String getId (Tuple tuple) {
		return tuple.get(qProfiling.id).toString();
	}

}
