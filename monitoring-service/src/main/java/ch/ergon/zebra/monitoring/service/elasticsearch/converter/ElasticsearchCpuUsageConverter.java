/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.elasticsearch.converter;

import java.sql.Timestamp;

import org.elasticsearch.common.xcontent.XContentBuilder;

import ch.ergon.zebra.monitoring.data.elasticsearch.ES_TYPE;
import ch.ergon.zebra.monitoring.data.querydsl.metadata.QCpuUsage;

import com.mysema.query.Tuple;

public class ElasticsearchCpuUsageConverter extends AbstractElasticsearchConverter {

	private static final ES_TYPE TYPE= ES_TYPE.CPU_USAGE;

	private final QCpuUsage qCpuUsage= new QCpuUsage(TYPE.toString().toUpperCase());

	@Override
	public String getEsKey () {
		return TYPE.getEsKey();
	}

	@Override
	protected void addFields (XContentBuilder sourceBuilder, Tuple tuple) {
		addField(sourceBuilder, "id", tuple.get(qCpuUsage.id));
		addField(sourceBuilder, "sysDatetime", getSysDatetime(tuple));
		addField(sourceBuilder, "cpuUsage", tuple.get(qCpuUsage.cpuUsage));
	}

	@Override
	protected String getId (Tuple tuple) {
		return tuple.get(qCpuUsage.id).toString();
	}

	@Override
	protected Timestamp getSysDatetime (Tuple tuple) {
		return tuple.get(qCpuUsage.sysDatetime);
	}

}
