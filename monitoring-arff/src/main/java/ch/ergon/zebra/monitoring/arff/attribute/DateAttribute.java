/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.arff.attribute;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ergon.zebra.monitoring.arff.TypeConverter;

public class DateAttribute extends AbstractAttribute {

	private static final Logger LOG= LoggerFactory.getLogger(DateAttribute.class);

	private final DateFormat dateFormat;

	public DateAttribute (String name, String dateFormat, Locale locale) {
		super(name);
		this.dateFormat= new SimpleDateFormat(dateFormat, locale);
	}

	@Override
	public boolean isValid (String value) {
		if (value == null) {
			return false;
		}
		if (isEmpty(value)) {
			return true;
		}

		try {
			dateFormat.parse(value);
		} catch (ParseException e) {
			LOG.error("Failed to parse the following value: " + value);
			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue (String value, TypeConverter converter) {
		return (T) converter.getDateValue(value, dateFormat);
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

}
