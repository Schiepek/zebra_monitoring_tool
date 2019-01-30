/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.elasticsearch.converter;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.elasticsearch.common.xcontent.XContentBuilder;

import ch.ergon.zebra.monitoring.data.elasticsearch.ES_TYPE;
import ch.ergon.zebra.monitoring.data.querydsl.metadata.QMemoryUsage;

import com.mysema.query.Tuple;

public class ElasticsearchMemoryUsageConverter extends AbstractElasticsearchConverter {

	private static final ES_TYPE TYPE= ES_TYPE.MEMORY_USAGE;

	private final QMemoryUsage qMemoryUsage= new QMemoryUsage(TYPE.toString().toUpperCase());

	@Override
	public String getEsKey () {
		return TYPE.getEsKey();
	}

	@Override
	protected void addFields (XContentBuilder sourceBuilder, Tuple tuple) {
		BigDecimal freeHeap= tuple.get(qMemoryUsage.freeHeap);
		BigDecimal totalHeap= tuple.get(qMemoryUsage.totalHeap);
		BigDecimal usedHeap= totalHeap.subtract(freeHeap);

		addField(sourceBuilder, "id", tuple.get(qMemoryUsage.id));
		addField(sourceBuilder, "sysDatetime", getSysDatetime(tuple));
		addField(sourceBuilder, "totalHeap", tuple.get(qMemoryUsage.totalHeap));
		addField(sourceBuilder, "usedHeap", usedHeap);
	}

	@Override
	protected Timestamp getSysDatetime (Tuple tuple) {
		return tuple.get(qMemoryUsage.sysDatetime);
	}

	@Override
	protected String getId (Tuple tuple) {
		return tuple.get(qMemoryUsage.id).toString();
	}

}
