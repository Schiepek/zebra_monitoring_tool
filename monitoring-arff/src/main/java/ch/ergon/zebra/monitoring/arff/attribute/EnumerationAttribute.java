/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.arff.attribute;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import ch.ergon.zebra.monitoring.arff.TypeConverter;

public class EnumerationAttribute extends AbstractAttribute {

	private static final Pattern SPLIT_BY_COMMA= Pattern.compile(",");

	private final Set<String> enums;

	public EnumerationAttribute (String name, String enumsText) {
		super(name);
		enums= new HashSet<String>(Arrays.asList(SPLIT_BY_COMMA.split(enumsText)));
	}

	@Override
	public boolean isValid (String value) {
		if (value == null) {
			return false;
		}

		if (isEmpty(value)) {
			return true;
		}

		if (!enums.contains(value)) {
			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue (String value, TypeConverter converter) {
		return (T) converter.getTextValue(value);
	}

}
