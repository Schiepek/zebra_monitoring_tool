/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.arff;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ergon.zebra.monitoring.arff.attribute.Attribute;

public final class Instance {

	private static final Logger LOG= LoggerFactory.getLogger(Instance.class);

	private final Map<String, Pair<Attribute, String>> values= new HashMap<>();

	/** The raw text line that is the source of this instance. */
	private final String line;

	public Instance (String line) {
		this.line= line;
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue (Attribute attribute, TypeConverter converter) {
		final String value= values.get(attribute.getName()).getValue();
		if (value.equals("?") || StringUtils.isBlank(value)) {
			return null;
		} else {
			return (T) attribute.getValue(value, converter);
		}
	}

	public void setValue (Attribute attribute, String value) {
		if (attribute.isValid(value)) {
			values.put(attribute.getName(), Pair.of(attribute, value));
		} else {
			final String message= "Value '" + value + "' for Attribute '" + attribute.getName() + "' is not valid";
			LOG.error(message);

			throw new ArffParserException(message);
		}
	}

	public Collection<Pair<Attribute, String>> getValues () {
		return values.values();
	}

	public Pair<Attribute, String> getValue (String key) {
		return values.get(key);
	}

	/**
	 * @return Returns the raw text line that was the source of this instance.
	 */
	public String getSource () {
		return line;
	}

}
