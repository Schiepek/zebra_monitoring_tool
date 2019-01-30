/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.profiling;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import ch.ergon.zebra.monitoring.data.AbstractSqlRepository;
import ch.ergon.zebra.monitoring.data.ConnectionFactory;
import ch.ergon.zebra.monitoring.data.Statement;
import ch.ergon.zebra.monitoring.data.querydsl.domain.Profiling;
import ch.ergon.zebra.monitoring.data.querydsl.metadata.QProfiling;

import com.mysema.query.Tuple;
import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.types.QTuple;

public class SqlUseCaseProfilerRepository extends AbstractSqlRepository implements UseCaseProfilerRepository {

	public final QProfiling qProfiling= new QProfiling("PROFILING", getDbScheme(), "PROFILING");

	public SqlUseCaseProfilerRepository (Configuration configuration, ConnectionFactory factory) {
		super(configuration, factory);
	}

	@Override
	public Profiling findById (long id) {
		final Connection connection= getConnection();
		try {
			final Statement<Profiling> statement= ()
				-> createQuery(connection)
					.from(qProfiling)
					.where(qProfiling.id.eq(BigInteger.valueOf(id)))
					.singleResult(qProfiling);
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
				.from(qProfiling)
				.where(qProfiling.client.eq(client)
					.and(qProfiling.environment.eq(environment)
						.and(qProfiling.sysDatetime.between(minTimestamp, maxTimestamp))))
				.list(qProfiling.hash);

			// Convert to a set in order to improve lookups
			return new HashSet<String>(result);
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	@Override
	public void save (List<Profiling> profilings) {
		final Connection connection= getConnection();
		try {
			final Statement<Void> statement= createInsertStatement(profilings, connection);
			transaction(statement, connection);
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	private Statement<Void> createInsertStatement (List<Profiling> profilings, Connection connection) {
		return new Statement<Void>() {

			@Override
			public Void execute () {
				final SQLInsertClause clause= createInsertClause(qProfiling, connection);
				for (final Profiling entity: profilings) {
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
				.from(qProfiling)
				.where(qProfiling.id.in(
					new SQLSubQuery()
						.from(qProfiling)
						.where(qProfiling.id.between(offset, offset + limit - 1))
						.list(qProfiling.id)))
				.where(qProfiling.client.eq(client)
					.and(qProfiling.environment.eq(environment)))
				.orderBy(qProfiling.id.asc())
				.list(
					new QTuple(
						qProfiling.id,
						qProfiling.focusDate,
						qProfiling.focusKstNummer,
						qProfiling.serverExecutionTimeMs,
						qProfiling.totalTimeDuration,
						qProfiling.latencyDuration,
						qProfiling.pageLoadDuration,
						qProfiling.userAgent,
						qProfiling.loginName,
						qProfiling.usecaseId,
						qProfiling.zebraVersion,
						qProfiling.sysDatetime,
						qProfiling.totalExecutionTimeMs,
						qProfiling.totalFetchTimeMs,
						qProfiling.totalDatabaseTimeMs));
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
				.from(qProfiling)
				.where(qProfiling.client.eq(client).
					and(qProfiling.environment.eq(environment)))
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
				.from(qProfiling)
				.where(qProfiling.client.eq(client).
					and(qProfiling.environment.eq(environment)))
				.singleResult(qProfiling.id.max());
			return (maxId != null) ? maxId.longValue() : null;
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

}
