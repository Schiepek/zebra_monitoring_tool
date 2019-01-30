/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.mock;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.ergon.zebra.monitoring.data.measurement.MeasurementRepository;

import com.google.common.collect.Lists;
import com.mysema.query.Tuple;

public class MockMeasurementRepository<T> implements MeasurementRepository<T> {

	protected final List<T> entities= Lists.newArrayList();

	public List<T> getEntities () {
		return entities;
	}

	@Override
	public void save (List<T> entities) {
		this.entities.addAll(entities);
	}

	@Override
	public T findById (long id) {
		return null;
	}

	@Override
	public Set<String> findHashes (String client, String environment, Timestamp minTimestamp, Timestamp maxTimestamp) {
		return new HashSet<>();
	}

	@Override
	public List<Tuple> findForElasticSearch (String client, String environment, long offset, long limit) {
		return null;
	}

	@Override
	public boolean dataExists (String client, String environment) {
		return false;
	}

	@Override
	public Long getMaxId (String client, String environment) {
		return 0L;
	}

};
