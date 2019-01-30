/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.arff.attribute;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractAttribute implements Attribute {

	private final String name;

	public AbstractAttribute (String name) {
		this.name= name;
	}

	@Override
	public String getName () {
		return name;
	}

	protected boolean isEmpty (String value) {
		return StringUtils.equals(value, "?") || StringUtils.isBlank(value);
	}

}
