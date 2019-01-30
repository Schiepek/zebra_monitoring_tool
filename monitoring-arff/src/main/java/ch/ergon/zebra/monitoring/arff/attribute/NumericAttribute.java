/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.arff.attribute;

import org.apache.commons.lang3.math.NumberUtils;

import ch.ergon.zebra.monitoring.arff.TypeConverter;

public class NumericAttribute extends AbstractAttribute {

	public NumericAttribute (String name) {
		super(name);
	}

	@Override
	public boolean isValid (String value) {
		if (value == null) {
			return false;
		}

		if (isEmpty(value)) {
			return true;
		}

		if (!NumberUtils.isNumber(value)) {
			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue (String value, TypeConverter converter) {
		return (T) converter.getNumericValue(value);
	}

}
