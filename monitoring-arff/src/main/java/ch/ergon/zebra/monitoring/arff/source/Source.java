/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */
package ch.ergon.zebra.monitoring.arff.source;

import java.io.InputStream;

public interface Source {

	/**
	 * @return Returns a short description of the Source. E.g. The filename for a Path.
	 */
	String getName ();

	/**
	 * @return Returns an InputStream. The caller is responsible for closing the InputStream.
	 */
	InputStream getInputStream ();

}
