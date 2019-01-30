/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.profiling;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ergon.zebra.monitoring.arff.Instance;
import ch.ergon.zebra.monitoring.data.measurement.MeasurementRepository;
import ch.ergon.zebra.monitoring.data.querydsl.domain.Profiling;
import ch.ergon.zebra.monitoring.service.arff.AbstractInstanceHandler;
import ch.ergon.zebra.monitoring.service.mapping.ActionMappings;
import ch.ergon.zebra.monitoring.service.service.ServiceContext;

public class UseCaseProfilerInstanceHandler extends AbstractInstanceHandler<Profiling> {

	private static final Logger LOG= LoggerFactory.getLogger(UseCaseProfilerInstanceHandler.class);

	public UseCaseProfilerInstanceHandler (ServiceContext<Profiling> serviceContext, ActionMappings<Profiling> actionMappings) {
		super(serviceContext, actionMappings);
	}

	@Override
	protected Profiling createEntity (Instance instance) {
		return new Profiling();
	}

	@Override
	protected List<Profiling> filterEntities (List<Profiling> entities) {
		if (entities.isEmpty()) {
			return new ArrayList<>();
		}

		final Timestamp minTimestamp= getMinTimestamp(entities);
		final Timestamp maxTimestamp= getMaxTimestamp(entities);

		final MeasurementRepository<Profiling> repository= getRepository();
		final Set<String> hashes= repository.findHashes(getClient(), getEnvironment(), minTimestamp, maxTimestamp);

		final List<Profiling> result= new ArrayList<>();
		for (Profiling entity: entities) {
			if (hashes.contains(entity.getHash())) {
				// Skipping the entry since hash already exists on the database
				writeLogMessage(entity);
			} else {
				result.add(entity);
			}
		}

		return result;
	}

	private static void writeLogMessage (Profiling entity) {
		if (LOG.isInfoEnabled()) {
			final String message= new StringBuilder()
				.append("Skipping duplicate: ")
				.append("client=").append(entity.getClient())
				.append(", environment=").append(entity.getEnvironment())
				.append(", usecaseId=").append(entity.getUsecaseId())
				.append(", sysDatetime=").append(entity.getSysDatetime())
				.append(", sessionId=").append(entity.getSessionId())
				.append(", requestId=").append(entity.getRequestId())
				.append(", hash=").append(entity.getHash())
				.toString();

			LOG.info(message);
		}
	}

	private static Timestamp getMinTimestamp (List<Profiling> entities) {
		Timestamp result= null;
		for (Profiling entity: entities) {
			final Timestamp timestamp= entity.getSysDatetime();
			if (result == null || timestamp.before(result)) {
				result= timestamp;
			}
		}

		return result;
	}

	private static Timestamp getMaxTimestamp (List<Profiling> entities) {
		Timestamp result= null;
		for (Profiling entity: entities) {
			final Timestamp timestamp= entity.getSysDatetime();
			if (result == null || timestamp.after(result)) {
				result= timestamp;
			}
		}

		return result;
	}

}
