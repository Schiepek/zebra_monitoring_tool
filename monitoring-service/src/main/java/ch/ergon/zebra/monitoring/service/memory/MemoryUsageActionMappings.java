/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.memory;

import java.util.HashMap;
import java.util.Map;

import ch.ergon.zebra.monitoring.arff.Instance;
import ch.ergon.zebra.monitoring.arff.TypeConverter;
import ch.ergon.zebra.monitoring.arff.attribute.Attribute;
import ch.ergon.zebra.monitoring.data.querydsl.domain.MemoryUsage;
import ch.ergon.zebra.monitoring.service.mapping.ActionMappings;
import ch.ergon.zebra.monitoring.service.mapping.MappingFunction;

import com.google.common.collect.Maps;

public class MemoryUsageActionMappings implements ActionMappings<MemoryUsage> {

	private final Map<String, MappingFunction<MemoryUsage, Attribute, Instance>> actions= new HashMap<>();

	private final TypeConverter converter;

	public MemoryUsageActionMappings (TypeConverter converter) {
		this.converter= converter;
		initializeActionMappings();
	}

	@Override
	public MappingFunction<MemoryUsage, Attribute, Instance> getMapping (Attribute attribute) {
		return actions.get(attribute.getName());
	}

	@Override
	public Map<String, MappingFunction<MemoryUsage, Attribute, Instance>> getMappings () {
		return Maps.newHashMap(actions);
	}

	private void initializeActionMappings () {
		actions.put("sysdatetime", (memoryUsage, attribute, instance) -> memoryUsage.setSysDatetime(instance.getValue(attribute, converter)));
		actions.put("totalHeap", (memoryUsage, attribute, instance) -> memoryUsage.setTotalHeap(instance.getValue(attribute, converter)));
		actions.put("freeHeap", (memoryUsage, attribute, instance) -> memoryUsage.setFreeHeap(instance.getValue(attribute, converter)));
	}

}
