/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */
package ch.ergon.zebra.monitoring.arff.source;

import java.io.IOException;

public class ArffSourceException extends RuntimeException {

	public ArffSourceException (String message, IOException e) {
		super(message, e);
	}

	public ArffSourceException (String message) {
		super(message);
	}

}
