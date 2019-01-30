/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.arff.attribute;

import ch.ergon.zebra.monitoring.arff.TypeConverter;

public class TextAttribute extends AbstractAttribute {

	public TextAttribute (String name) {
		super(name);
	}

	@Override
	public boolean isValid (String value) {
		return value != null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue (String value, TypeConverter converter) {
		if (value.equals("true") || value.equals("false")) {
			return (T) converter.getBooleanValue(value);
		}
		return (T) converter.getTextValue(value);
	}

}
