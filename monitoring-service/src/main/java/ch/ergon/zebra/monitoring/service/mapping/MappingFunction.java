/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.mapping;


@FunctionalInterface
public interface MappingFunction<T, Attribute, Instance> {

	void accept (T entity, Attribute attribte, Instance instance);

}
