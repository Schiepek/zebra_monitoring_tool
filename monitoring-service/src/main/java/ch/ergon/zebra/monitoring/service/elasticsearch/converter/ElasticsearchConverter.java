/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.elasticsearch.converter;

import org.elasticsearch.common.lang3.tuple.Pair;
import org.elasticsearch.common.xcontent.XContentBuilder;

import com.mysema.query.Tuple;

public interface ElasticsearchConverter {

	Pair<String, XContentBuilder> getSource (Tuple tuple);

	String getEsKey ();

	String getIndexName (String aliasName, Tuple tuple);

}
