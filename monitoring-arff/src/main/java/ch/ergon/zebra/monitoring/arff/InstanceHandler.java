/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.arff;

public interface InstanceHandler<T> {

	void preHandle();
	
	void handle (Instance instance);

	void postHandle ();

}
