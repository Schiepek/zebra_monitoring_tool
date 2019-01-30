/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.mapping;

import java.util.Map;

import ch.ergon.zebra.monitoring.arff.Instance;
import ch.ergon.zebra.monitoring.arff.attribute.Attribute;

public interface ActionMappings<T> {

	MappingFunction<T, Attribute, Instance> getMapping (Attribute attribute);

	Map<String, MappingFunction<T, Attribute, Instance>> getMappings ();

}
