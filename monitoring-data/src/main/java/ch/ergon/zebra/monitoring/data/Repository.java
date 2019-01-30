/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data;

import java.util.List;

public interface Repository<T> {

	T findById (long id);

	void save (List<T> entities);

}
