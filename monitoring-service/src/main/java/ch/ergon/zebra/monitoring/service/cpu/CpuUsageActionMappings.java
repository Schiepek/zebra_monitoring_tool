/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.cpu;

import java.util.HashMap;
import java.util.Map;

import ch.ergon.zebra.monitoring.arff.Instance;
import ch.ergon.zebra.monitoring.arff.TypeConverter;
import ch.ergon.zebra.monitoring.arff.attribute.Attribute;
import ch.ergon.zebra.monitoring.data.querydsl.domain.CpuUsage;
import ch.ergon.zebra.monitoring.service.mapping.ActionMappings;
import ch.ergon.zebra.monitoring.service.mapping.MappingFunction;

import com.google.common.collect.Maps;

public class CpuUsageActionMappings implements ActionMappings<CpuUsage> {

	private final Map<String, MappingFunction<CpuUsage, Attribute, Instance>> actions= new HashMap<>();

	private final TypeConverter converter;

	public CpuUsageActionMappings (TypeConverter converter) {
		this.converter= converter;
		initializeActionMappings();
	}

	@Override
	public MappingFunction<CpuUsage, Attribute, Instance> getMapping (Attribute attribute) {
		return actions.get(attribute.getName());
	}

	@Override
	public Map<String, MappingFunction<CpuUsage, Attribute, Instance>> getMappings () {
		return Maps.newHashMap(actions);
	}

	private void initializeActionMappings () {
		actions.put("sysdatetime", (cpuUsage, attribute, instance) -> cpuUsage.setSysDatetime(instance.getValue(attribute, converter)));
		actions.put("cpuUsage", (cpuUsage, attribute, instance) -> cpuUsage.setCpuUsage(instance.getValue(attribute, converter)));
	}

}
