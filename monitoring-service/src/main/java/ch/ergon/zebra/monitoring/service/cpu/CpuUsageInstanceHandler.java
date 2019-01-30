/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.cpu;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ergon.zebra.monitoring.arff.Instance;
import ch.ergon.zebra.monitoring.data.measurement.MeasurementRepository;
import ch.ergon.zebra.monitoring.data.querydsl.domain.CpuUsage;
import ch.ergon.zebra.monitoring.service.arff.AbstractInstanceHandler;
import ch.ergon.zebra.monitoring.service.mapping.ActionMappings;
import ch.ergon.zebra.monitoring.service.service.ServiceContext;

public class CpuUsageInstanceHandler extends AbstractInstanceHandler<CpuUsage> {

	private static final Logger LOG= LoggerFactory.getLogger(CpuUsageInstanceHandler.class);

	public CpuUsageInstanceHandler (ServiceContext<CpuUsage> serviceContext, ActionMappings<CpuUsage> actionMappings) {
		super(serviceContext, actionMappings);
	}

	@Override
	protected CpuUsage createEntity (Instance instance) {
		return new CpuUsage();
	}

	@Override
	protected List<CpuUsage> filterEntities (List<CpuUsage> entities) {
		if (entities.isEmpty()) {
			return new ArrayList<>();
		}

		final Timestamp minTimestamp= getMinTimestamp(entities);
		final Timestamp maxTimestamp= getMaxTimestamp(entities);

		final MeasurementRepository<CpuUsage> repository= getRepository();
		final Set<String> hashes= repository.findHashes(getClient(), getEnvironment(), minTimestamp, maxTimestamp);

		final List<CpuUsage> result= new ArrayList<>();
		for (CpuUsage entity: entities) {
			if (hashes.contains(entity.getHash())) {
				// Skipping the entry since hash already exists on the database
				writeLogMessage(entity);
			} else {
				result.add(entity);
			}
		}

		return result;
	}

	private static void writeLogMessage (CpuUsage entity) {
		if (LOG.isInfoEnabled()) {
			final String message= new StringBuilder()
				.append("Skipping duplicate: ")
				.append("client=").append(entity.getClient())
				.append(", environment=").append(entity.getEnvironment())
				.append(", sysDatetime=").append(entity.getSysDatetime())
				.append(", hash=").append(entity.getHash())
				.toString();

			LOG.info(message);
		}
	}

	private static Timestamp getMinTimestamp (List<CpuUsage> entities) {
		Timestamp result= null;
		for (CpuUsage entity: entities) {
			final Timestamp timestamp= entity.getSysDatetime();
			if (result == null || timestamp.before(result)) {
				result= timestamp;
			}
		}

		return result;
	}

	private static Timestamp getMaxTimestamp (List<CpuUsage> entities) {
		Timestamp result= null;
		for (CpuUsage entity: entities) {
			final Timestamp timestamp= entity.getSysDatetime();
			if (result == null || timestamp.after(result)) {
				result= timestamp;
			}
		}

		return result;
	}

}
