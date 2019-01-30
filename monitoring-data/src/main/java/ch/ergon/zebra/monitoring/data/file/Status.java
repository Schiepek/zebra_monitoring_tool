/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.file;

public enum Status {

	NEW, DONE, ERROR;

	@Override
	public String toString () {
		return name().toLowerCase();
	}

}
