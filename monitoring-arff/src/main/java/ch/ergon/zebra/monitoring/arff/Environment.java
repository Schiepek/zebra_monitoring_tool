/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */
package ch.ergon.zebra.monitoring.arff;

import java.util.Locale;

public class Environment {

	private final Locale locale;

	public Environment () {
		this(Locale.ENGLISH);
	}

	public Environment (Locale locale) {
		this.locale= locale;
	}

	public Locale getLocale () {
		return locale;
	}

}
