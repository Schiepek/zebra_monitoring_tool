/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.arff.util;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public final class HashUtil {

	private HashUtil () {
		// utility class
	}

	public static String calculateHash (String value) {
		if (StringUtils.isBlank(value)) {
			throw new IllegalStateException("Can not calculate a hash for an empty string");
		}

		return Hashing.sha256().hashString(value, Charsets.UTF_8).toString();
	}

}
