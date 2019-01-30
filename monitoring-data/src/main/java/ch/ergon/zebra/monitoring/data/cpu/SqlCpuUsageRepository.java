/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.cpu;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import ch.ergon.zebra.monitoring.data.AbstractSqlRepository;
import ch.ergon.zebra.monitoring.data.ConnectionFactory;
import ch.ergon.zebra.monitoring.data.Statement;
import ch.ergon.zebra.monitoring.data.querydsl.domain.CpuUsage;
import ch.ergon.zebra.monitoring.data.querydsl.metadata.QCpuUsage;

import com.mysema.query.Tuple;
import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.types.QTuple;

public class SqlCpuUsageRepository extends AbstractSqlRepository implements CpuUsageRepository {

	public final QCpuUsage qCpuUsage= new QCpuUsage("CPU_USAGE", getDbScheme(), "CPU_USAGE");

	public SqlCpuUsageRepository (Configuration configuration, ConnectionFactory factory) {
		super(configuration, factory);
	}

	@Override
	public CpuUsage findById (long id) {
		final Connection connection= getConnection();
		try {
			final Statement<CpuUsage> statement= ()
				-> createQuery(connection)
					.from(qCpuUsage)
					.where(qCpuUsage.id.eq(BigInteger.valueOf(id)))
					.singleResult(qCpuUsage);
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
				.from(qCpuUsage)
				.where(qCpuUsage.client.eq(client).
					and(qCpuUsage.environment.eq(environment)))
				.where(qCpuUsage.sysDatetime.between(minTimestamp, maxTimestamp))
				.list(qCpuUsage.hash);

			// Convert to a set in order to improve lookups
			return new HashSet<String>(result);
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	@Override
	public void save (List<CpuUsage> cpuUsages) {
		final Connection connection= getConnection();
		try {
			final Statement<Void> statement= createInsertStatement(cpuUsages, connection);
			transaction(statement, connection);
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

	private Statement<Void> createInsertStatement (List<CpuUsage> cpuUsages, Connection connection) {
		return new Statement<Void>() {

			@Override
			public Void execute () {
				final SQLInsertClause clause= createInsertClause(qCpuUsage, connection);
				for (final CpuUsage entity: cpuUsages) {
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
				.from(qCpuUsage)
				.where(qCpuUsage.id.in(
					new SQLSubQuery()
						.from(qCpuUsage)
						.where(qCpuUsage.id.between(offset, offset + limit - 1))
						.list(qCpuUsage.id)))
				.where(qCpuUsage.client.eq(client))
				.where(qCpuUsage.environment.eq(environment))
				.orderBy(qCpuUsage.id.asc())
				.list(
					new QTuple(
						qCpuUsage.id,
						qCpuUsage.sysDatetime,
						qCpuUsage.cpuUsage));
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
				.from(qCpuUsage)
				.where(qCpuUsage.client.eq(client)
					.and(qCpuUsage.environment.eq(environment)))
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
				.from(qCpuUsage)
				.where(qCpuUsage.client.eq(client)
					.and(qCpuUsage.environment.eq(environment)))
				.singleResult(qCpuUsage.id.max());
			return (maxId != null) ? maxId.longValue() : null;
		} finally {
			DbUtils.closeQuietly(connection);
		}
	}

}
