/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.elasticsearch.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.Client;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ergon.zebra.monitoring.data.elasticsearch.ES_TYPE;

import com.google.common.base.Charsets;

public final class ElasticsearchTypeMappingUtil {

	private static final Logger LOG= LoggerFactory.getLogger(ElasticsearchTypeMappingUtil.class);

	private static final String PROFILING_MAPPING_PATH= "/mapping/profiling_mapping.json";

	private static final String CPU_MAPPING_PATH= "/mapping/cpu_mapping.json";

	private static final String MEMORY_MAPPING_PATH= "/mapping/memory_mapping.json";

	private static final Charset CHARSET_UTF_8= Charsets.UTF_8;

	private static final Map<ES_TYPE, String> paths= new HashMap<>();

	// Initialize mapping
	static {
		paths.put(ES_TYPE.PROFILING, PROFILING_MAPPING_PATH);
		paths.put(ES_TYPE.CPU_USAGE, CPU_MAPPING_PATH);
		paths.put(ES_TYPE.MEMORY_USAGE, MEMORY_MAPPING_PATH);
	}

	private ElasticsearchTypeMappingUtil () {
		// utility class
	}

	public static void initializeTypeMapping (Client client, ES_TYPE type, String indexName) {
		final JSONObject json= getJson(paths.get(type));
		assertMappingMatchesType(json, type);

		initializeMapping(client, json, type, indexName);
	}

	private static JSONObject getJson (String path) {
		try {
			final InputStream inputStream= ClassLoader.class.getResourceAsStream(path);
			final InputStreamReader reader= new InputStreamReader(inputStream, CHARSET_UTF_8);

			return (JSONObject) JSONValue.parseWithException(reader);
		} catch (IOException e) {
			String message= "File not found: " + path;
			LOG.error(message, e);
			throw new IllegalArgumentException(message, e);
		} catch (ParseException e) {
			String message= "Invalid json format in file: " + path;
			LOG.error(message, e);
			throw new IllegalArgumentException(message, e);
		}
	}

	private static void assertMappingMatchesType (JSONObject json, ES_TYPE type) {
		if (!json.containsKey(type.getEsKey())) {
			String message= "Typename '" + type.getEsKey() + "' not found in json file";
			LOG.error(message);
			throw new IllegalArgumentException(message);
		}
	}

	private static void initializeMapping (Client client, JSONObject json, ES_TYPE type, String indexName) {
		client.admin().indices()
			.preparePutMapping(indexName)
			.setType(type.getEsKey())
			.setSource(json.toJSONString())
			.execute().actionGet();
	}

}
