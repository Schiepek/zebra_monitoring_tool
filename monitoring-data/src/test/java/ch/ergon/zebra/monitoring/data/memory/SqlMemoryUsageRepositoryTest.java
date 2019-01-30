/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.memory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Test;

import ch.ergon.zebra.monitoring.data.h2.AbstractRepositoryTest;
import ch.ergon.zebra.monitoring.data.querydsl.domain.MemoryUsage;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import com.mysema.query.Tuple;

public class SqlMemoryUsageRepositoryTest extends AbstractRepositoryTest {

	private static final double PRECISION= 0.0001;

	private static final Timestamp STARTDATE= new Timestamp(new DateTime(2000, 1, 1, 0, 0, 0, 0).getMillis());

	private static final String CLIENT= "coop";

	private static final String ENVIRONMENT= "pjazz";

	private static final Long MAX_ID= 100L;

	private SqlMemoryUsageRepository repository;

	@Override
	protected void doSetUp () {
		repository= new SqlMemoryUsageRepository(getTemplates(), getConnectionFactory());
	}

	@Test
	public void testDataExists () throws SQLException {
		// Arrange
		MemoryUsage memoryUsage= createMemoryUsage(CLIENT, ENVIRONMENT, STARTDATE, new BigDecimal(45.68));
		List<MemoryUsage> memoryUsages= Lists.newArrayList(memoryUsage);

		// Act
		repository.save(memoryUsages);

		// Assert
		assertThat(repository.dataExists("wrong_client", "wrong_environment"), is(false));
		assertThat(repository.dataExists(CLIENT, ENVIRONMENT), is(true));
	}

	@Test
	public void testFindById () {
		// Arrange
		MemoryUsage expectedMemoryUsage1= createMemoryUsage("cl_1", "en_1", STARTDATE, new BigDecimal(45.68));
		MemoryUsage expectedMemoryUsage2= createMemoryUsage("cl_2", "en_2", STARTDATE, new BigDecimal(12.1));
		List<MemoryUsage> memoryUsages= Lists.newArrayList(expectedMemoryUsage1, expectedMemoryUsage2);

		// Act
		repository.save(memoryUsages);

		MemoryUsage actualMemoryUsage1= repository.findById(1);
		MemoryUsage actualMemoryUsage2= repository.findById(2);

		// Assert
		assertEqualsMemoryUsage(actualMemoryUsage1, expectedMemoryUsage1);
		assertEqualsMemoryUsage(actualMemoryUsage2, expectedMemoryUsage2);

	}

	@Test
	public void testFindHashes () throws ParseException {
		// Arrange
		ArrayList<MemoryUsage> memoryUsages= new ArrayList<>();

		final Date startDate= new Date(STARTDATE.getTime());
		for (int i= 0; i < MAX_ID; i++) {
			memoryUsages.add(createMemoryUsage(CLIENT, ENVIRONMENT, addDays(startDate, i), new BigDecimal(11)));
		}

		memoryUsages.get(60).setClient("anotherClient");
		memoryUsages.get(65).setEnvironment("anotherEnvironment");

		Timestamp minTimestamp= memoryUsages.get(20).getSysDatetime();
		Timestamp maxTimestamp= memoryUsages.get(79).getSysDatetime();

		// Act
		repository.save(memoryUsages);
		Set<String> hashes= repository.findHashes(CLIENT, ENVIRONMENT, minTimestamp, maxTimestamp);

		// Assert
		assertThat(hashes.size(), is(58));
	}

	@Test
	public void testGetMaxId () {
		// Arrange
		ArrayList<MemoryUsage> memoryUsages= new ArrayList<>();

		for (int i= 0; i < MAX_ID; i++) {
			memoryUsages.add(createMemoryUsage(CLIENT, ENVIRONMENT, STARTDATE, new BigDecimal(11)));
		}

		// Act
		repository.save(memoryUsages);

		// Assert
		assertThat(MAX_ID, is(repository.getMaxId(CLIENT, ENVIRONMENT)));
	}

	@Test
	public void testFindForElasticsearch () {
		// Arrange
		List<MemoryUsage> memoryUsages= new ArrayList<>();

		for (int i= 0; i < MAX_ID; i++) {
			memoryUsages.add(createMemoryUsage(CLIENT, ENVIRONMENT, STARTDATE, new BigDecimal(11)));
		}

		memoryUsages.get(40).setClient("anotherClient");
		memoryUsages.get(45).setEnvironment("anotherEnvironment");

		// Act
		repository.save(memoryUsages);
		List<Tuple> tuples= repository.findForElasticSearch(CLIENT, ENVIRONMENT, 20, 40);

		// Assert
		assertThat(tuples.size(), is(38));
	}

	@Test
	public void testFindForElasticsearchWithPaging () {
		// Arrange
		final long offset= 20;

		List<MemoryUsage> memoryUsages= new ArrayList<>();

		for (int i= 0; i < MAX_ID; i++) {
			memoryUsages.add(createMemoryUsage(CLIENT, ENVIRONMENT, STARTDATE, new BigDecimal(11)));
		}

		List<Tuple> tuples= new ArrayList<>();

		// Act
		repository.save(memoryUsages);
		for (long i= 1; i <= MAX_ID; i+= offset) {
			tuples.addAll(repository.findForElasticSearch(CLIENT, ENVIRONMENT, i, offset));
		}

		// Assert
		assertThat(Long.valueOf(tuples.size()), is(MAX_ID));
	}

	private static void assertEqualsMemoryUsage (MemoryUsage actualMemoryUsage, MemoryUsage expectedMemoryUsage) {
		assertThat(actualMemoryUsage, not(nullValue()));
		assertThat(actualMemoryUsage.getSysDatetime(), is(expectedMemoryUsage.getSysDatetime()));
		assertThat(actualMemoryUsage.getClient(), is(expectedMemoryUsage.getClient()));
		assertThat(actualMemoryUsage.getEnvironment(), is(expectedMemoryUsage.getEnvironment()));
		assertThat(actualMemoryUsage.getFreeHeap(), is(closeTo(expectedMemoryUsage.getFreeHeap(), new BigDecimal(PRECISION))));
	}

	private static MemoryUsage createMemoryUsage (String client, String environment, Timestamp sysDatetime, BigDecimal freeHeap) {
		MemoryUsage MemoryUsage= new MemoryUsage();
		MemoryUsage.setClient(client);
		MemoryUsage.setEnvironment(environment);
		MemoryUsage.setSysDatetime(sysDatetime);
		MemoryUsage.setFreeHeap(freeHeap);;
		MemoryUsage.setHash(Hashing.sha256().hashString(sysDatetime.toString(), Charsets.UTF_8).toString());
		return MemoryUsage;
	}

}
