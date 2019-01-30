/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.arff;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ergon.zebra.monitoring.arff.TypeConverter;

import com.google.common.primitives.Doubles;

public class OracleTypeConverter implements TypeConverter {

	private static final Logger LOG= LoggerFactory.getLogger(OracleTypeConverter.class);

	@Override
	public Timestamp getDateValue (String value, DateFormat dateFormat) {
		Date date= parseDate(value, dateFormat);
		return new Timestamp(date.getTime());
	}

	@Override
	public String getTextValue (String value) {
		return value;
	}

	@Override
	public Number getNumericValue (String value) {
		Double numericValue= Doubles.tryParse(value);
		if (numericValue != null) {
			return BigDecimal.valueOf(numericValue);
		}
		return new BigInteger(value);
	}

	@Override
	public Byte getBooleanValue (String value) {
		if (value.equals("true")) {
			return 1;
		} else if (value.equals("false")) {
			return 0;
		} else {
			String message= "Boolean value has to be either \"true\" or \"false\", but was " + value;
			LOG.error(message);
			throw new IllegalStateException(message);
		}
	}

	private static Date parseDate (String value, DateFormat dateFormat) {
		try {
			return dateFormat.parse(value);
		} catch (ParseException e) {
			String message= "Parse error with value " + value;
			LOG.error(message, e);
			throw new IllegalStateException(e);
		}
	}

}
