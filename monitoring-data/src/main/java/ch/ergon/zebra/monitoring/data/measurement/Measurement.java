/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.measurement;

public interface Measurement {

	String getClient ();

	void setClient (String client);

	String getEnvironment ();

	void setEnvironment (String environment);

	String getHash ();

	void setHash (String hash);

}
