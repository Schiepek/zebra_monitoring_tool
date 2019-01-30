/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.elasticsearch.converter;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.elasticsearch.common.lang3.tuple.ImmutablePair;
import org.elasticsearch.common.lang3.tuple.Pair;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysema.query.Tuple;

public abstract class AbstractElasticsearchConverter implements ElasticsearchConverter {

	private static final Logger LOG= LoggerFactory.getLogger(AbstractElasticsearchConverter.class);

	@Override
	public Pair<String, XContentBuilder> getSource (Tuple tuple) {
		try {
			XContentBuilder sourceBuilder= jsonBuilder();
			sourceBuilder.startObject();
			addFields(sourceBuilder, tuple);
			sourceBuilder.endObject();
			return new ImmutablePair<String, XContentBuilder>(getId(tuple), sourceBuilder);
		} catch (IOException e) {
			String message= "Error during initialization of jsonBuilder";
			LOG.error(message);
			throw new IllegalArgumentException(message, e);
		}
	}

	@Override
	public final String getIndexName (String aliasName, Tuple tuple) {
		final Timestamp sysDatetime= getSysDatetime(tuple);
		final LocalDateTime localDateTime= sysDatetime.toLocalDateTime();
		final int year= localDateTime.getYear();

		final String monthValue= getMonthValue(localDateTime.getMonthValue());

		final String indexName= new StringBuilder()
			.append(aliasName)
			.append("_")
			.append(year)
			.append("_")
			.append(monthValue)
			.toString();

		return indexName;
	}

	protected abstract void addFields (XContentBuilder sourceBuilder, Tuple tuple);

	protected abstract String getId (Tuple tuple);

	protected abstract Timestamp getSysDatetime (Tuple tuple);

	protected void addField (XContentBuilder sourceBuilder, String field, Object value) {
		try {
			sourceBuilder.field(field, value);
		} catch (IOException e) {
			String message= "Error during adding field: " + field + " to source builder";
			LOG.error(message, e);
			throw new IllegalArgumentException(message, e);
		}
	}

	protected void addField (XContentBuilder sourceBuilder, String field, Timestamp value) {
		addField(sourceBuilder, field, getDate(value));
	}

	protected Date getDate (Timestamp date) {
		if (date == null) {
			return null;
		}
		return new Date(date.getTime());
	}

	private String getMonthValue (int monthValue) {
		if (monthValue <= 6) {
			return "01_06";
		}

		return "07_12";
	}

}
