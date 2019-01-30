/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */
package ch.ergon.zebra.monitoring.arff.attribute;

import ch.ergon.zebra.monitoring.arff.TypeConverter;

public interface Attribute {

	boolean isValid (String value);

	String getName ();

	<T> T getValue (String value, TypeConverter converter);

}
