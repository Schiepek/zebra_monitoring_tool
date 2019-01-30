/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.arff;

import org.apache.commons.lang3.StringUtils;

import ch.ergon.zebra.monitoring.arff.TypeConverter;
import ch.ergon.zebra.monitoring.arff.attribute.AbstractAttribute;

/**
 * The profiling files contain the weekday in the format 'EE'.
 *
 * <p>This can not be parsed by the default DateAttribute.</p>
 */
public class WeekdayAttribute extends AbstractAttribute {

	public WeekdayAttribute (String name) {
		super(name);
	}

	@Override
	public boolean isValid (String value) {
		if (value != null) {
			if (StringUtils.isAlpha(value)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue (String value, TypeConverter converter) {
		return (T) converter.getTextValue(value);
	}

}
