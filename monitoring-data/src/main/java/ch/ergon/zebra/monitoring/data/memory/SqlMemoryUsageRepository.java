/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.memory;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import ch.ergon.zebra.monitoring.data.AbstractSqlRepository;
import ch.ergon.zebra.monitoring.data.ConnectionFactory;
import ch.ergon.zebra.monitoring.data.Statement;
import ch.ergon.zebra.monitoring.data.querydsl.domain.MemoryUsage;
import ch.ergon.zebra.monitoring.data.querydsl.metadata.QMemoryUsage;

import com.mysema.query.Tuple;
import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.types.QTuple;

public class SqlMemoryUsageRepository extends AbstractSqlRepository implements MemoryUsageRepository {

	public final QMemoryUsage qMemoryUsage= new QMemoryUsage("MEMORY_USAGE", getDbScheme(), "MEMORY_USAGE");

	public SqlMemoryUsageRepository (Configuration configuration, ConnectionFactory factory) {
		super(configuration, factory);
	}

	@Override
	public MemoryUsage findById (long id) {
		final Connection connection= getConnection();
		try {
			final Statement<MemoryUsage> statement= () -> createQuery(connection)
				.from(qMemoryUsage)
				.where(qMemoryUsage.id.eq(BigInteger.valueOf(id)))
				.singleResult(qMemoryUsage);
			return transaction(statement, connection);
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	@Override
	public HashSet<String> findHashes (String client, String environment, Timestamp minTimestamp, Timestamp maxTimestamp) {
		final Connection connection= getConnection();
		try {
			List<String> result= createQuery(connection)
				.from(qMemoryUsage)
				.where(qMemoryUsage.client.eq(client).
					and(qMemoryUsage.environment.eq(environment)
						.and(qMemoryUsage.sysDatetime.between(minTimestamp, maxTimestamp))))
				.list(qMemoryUsage.hash);

			// Convert to a set in order to improve lookups
			return new HashSet<String>(result);
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	@Override
	public void save (List<MemoryUsage> memoryUsages) {
		final Connection connection= getConnection();
		try {
			final Statement<Void> statement= createInsertFunction(memoryUsages, connection);
			transaction(statement, connection);
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	private Statement<Void> createInsertFunction (List<MemoryUsage> memoryUsages, Connection connection) {
		return new Statement<Void>() {

			@Override
			public Void execute () {
				final SQLInsertClause clause= createInsertClause(qMemoryUsage, connection);
				for (final MemoryUsage entity: memoryUsages) {
					clause.populate(entity).addBatch();
				}
				clause.execute();
				return null;
			}
		};
	}

	@Override
	public List<Tuple> findForElasticSearch (String client, String environment, long offset, long limit) {
		final Connection connection= getConnection();
		try {
			final List<Tuple> result= createQuery(connection)
				.from(qMemoryUsage)
				.where(qMemoryUsage.id.in(
					new SQLSubQuery()
						.from(qMemoryUsage)
						.where(qMemoryUsage.id.between(offset, offset + limit - 1))
						.list(qMemoryUsage.id)))
				.where(qMemoryUsage.client.eq(client))
				.where(qMemoryUsage.environment.eq(environment))
				.orderBy(qMemoryUsage.id.asc())
				.list(
					new QTuple(
						qMemoryUsage.id,
						qMemoryUsage.sysDatetime,
						qMemoryUsage.totalHeap,
						qMemoryUsage.freeHeap));
			return result;
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	@Override
	public boolean dataExists (String client, String environment) {
		final Connection connection= getConnection();
		try {
			return createQuery(connection)
				.from(qMemoryUsage)
				.where(qMemoryUsage.client.eq(client)
					.and(qMemoryUsage.environment.eq(environment)))
				.exists();
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	@Override
	public Long getMaxId (String client, String environment) {
		final Connection connection= getConnection();
		try {
			BigInteger maxId= createQuery(connection)
				.from(qMemoryUsage)
				.where(qMemoryUsage.client.eq(client)
					.and(qMemoryUsage.environment.eq(environment)))
				.singleResult(qMemoryUsage.id.max());
			return (maxId != null) ? maxId.longValue() : null;
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

}
