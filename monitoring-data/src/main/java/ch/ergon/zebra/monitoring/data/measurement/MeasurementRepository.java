/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.measurement;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import ch.ergon.zebra.monitoring.data.Repository;

import com.mysema.query.Tuple;

public interface MeasurementRepository<T> extends Repository<T> {

	Set<String> findHashes (String client, String environment, Timestamp minTimestamp, Timestamp maxTimestamp);

	boolean dataExists (String client, String environment);

	/**
	 * @param client The client for which the largest id should be found.
	 * @param environment The environment for which the largest id should be found.
	 * @return Returns the largest id for the given client and environment or null if there is no data.
	 */
	Long getMaxId (String client, String environment);

	List<Tuple> findForElasticSearch (String client, String environment, long offset, long limit);

}
