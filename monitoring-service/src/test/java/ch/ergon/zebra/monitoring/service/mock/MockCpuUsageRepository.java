/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.mock;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import ch.ergon.zebra.monitoring.data.querydsl.domain.CpuUsage;

public class MockCpuUsageRepository extends MockMeasurementRepository<CpuUsage> {

	@Override
	public Set<String> findHashes (String client, String environment, Timestamp minTimestamp, Timestamp maxTimestamp) {
		final Set<String> result= new HashSet<>();

		for (CpuUsage entity: getEntities()) {
			result.add(entity.getHash());
		}

		return result;
	}

}
