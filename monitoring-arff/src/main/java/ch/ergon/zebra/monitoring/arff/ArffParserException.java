/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */
package ch.ergon.zebra.monitoring.arff;

import java.io.IOException;

public class ArffParserException extends RuntimeException {

	public ArffParserException (String message, IOException e) {
		super(message, e);
	}

	public ArffParserException (String message) {
		super(message);
	}

}
