/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.arff;

import java.text.DateFormat;

public interface TypeConverter {

	Object getDateValue (String value, DateFormat dateFormat);

	Object getTextValue (String value);

	Object getNumericValue (String value);

	Object getBooleanValue (String value);

}
