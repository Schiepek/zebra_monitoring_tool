/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.cpu;

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
import ch.ergon.zebra.monitoring.data.querydsl.domain.CpuUsage;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import com.mysema.query.Tuple;

public class SqlCpuUsageRepositoryTest extends AbstractRepositoryTest {

	private static final double PRECISION= 0.0001;

	private static final Timestamp STARTDATE= new Timestamp(new DateTime(2000, 1, 1, 0, 0, 0, 0).getMillis());

	private static final String CLIENT= "coop";

	private static final String ENVIRONMENT= "pjazz";

	private static final Long MAX_ID= 100L;

	private SqlCpuUsageRepository repository;

	@Override
	protected void doSetUp () {
		repository= new SqlCpuUsageRepository(getTemplates(), getConnectionFactory());
	}

	@Test
	public void testDataExists () throws SQLException {
		// Arrange
		CpuUsage cpuUsage= createCpuUsage(CLIENT, ENVIRONMENT, STARTDATE, new BigDecimal(45.68));
		List<CpuUsage> cpuUsages= Lists.newArrayList(cpuUsage);

		// Act
		repository.save(cpuUsages);

		// Assert
		assertThat(repository.dataExists("wrong_client", "wrong_environment"), is(false));
		assertThat(repository.dataExists(CLIENT, ENVIRONMENT), is(true));
	}

	@Test
	public void testFindById () {
		// Arrange
		CpuUsage expectedCpuUsage1= createCpuUsage("cl_1", "en_1", STARTDATE, new BigDecimal(45.68));
		CpuUsage expectedCpuUsage2= createCpuUsage("cl_2", "en_2", STARTDATE, new BigDecimal(12.1));
		List<CpuUsage> cpuUsages= Lists.newArrayList(expectedCpuUsage1, expectedCpuUsage2);

		// Act
		repository.save(cpuUsages);

		CpuUsage actualCpuUsage1= repository.findById(1);
		CpuUsage actualCpuUsage2= repository.findById(2);

		// Assert
		assertEqualsCpuUsage(actualCpuUsage1, expectedCpuUsage1);
		assertEqualsCpuUsage(actualCpuUsage2, expectedCpuUsage2);

	}

	@Test
	public void testFindHashes () throws ParseException {
		// Arrange
		ArrayList<CpuUsage> cpuUsages= new ArrayList<>();

		final Date startDate= new Date(STARTDATE.getTime());
		for (int i= 0; i < MAX_ID; i++) {
			cpuUsages.add(createCpuUsage(CLIENT, ENVIRONMENT, addDays(startDate, i), new BigDecimal(11)));
		}

		cpuUsages.get(60).setClient("anotherClient");
		cpuUsages.get(65).setEnvironment("anotherEnvironment");

		Timestamp minTimestamp= cpuUsages.get(20).getSysDatetime();
		Timestamp maxTimestamp= cpuUsages.get(79).getSysDatetime();

		// Act
		repository.save(cpuUsages);
		Set<String> hashes= repository.findHashes(CLIENT, ENVIRONMENT, minTimestamp, maxTimestamp);

		// Assert
		assertThat(hashes.size(), is(58));
	}

	@Test
	public void testGetMaxId () {
		// Arrange
		ArrayList<CpuUsage> cpuUsages= new ArrayList<>();

		for (int i= 0; i < MAX_ID; i++) {
			cpuUsages.add(createCpuUsage(CLIENT, ENVIRONMENT, STARTDATE, new BigDecimal(11)));
		}

		// Act
		repository.save(cpuUsages);

		// Assert
		assertThat(MAX_ID, is(repository.getMaxId(CLIENT, ENVIRONMENT)));
	}

	@Test
	public void testFindForElasticsearch () {
		// Arrange
		List<CpuUsage> cpuUsages= new ArrayList<>();

		for (int i= 0; i < MAX_ID; i++) {
			cpuUsages.add(createCpuUsage(CLIENT, ENVIRONMENT, STARTDATE, new BigDecimal(11)));
		}

		cpuUsages.get(40).setClient("anotherClient");
		cpuUsages.get(45).setEnvironment("anotherEnvironment");

		// Act
		repository.save(cpuUsages);
		List<Tuple> tuples= repository.findForElasticSearch(CLIENT, ENVIRONMENT, 20, 40);

		// Assert
		assertThat(tuples.size(), is(38));
	}

	@Test
	public void testFindForElasticsearchWithPaging () {
		// Arrange
		final long offset= 20;

		List<CpuUsage> cpuUsages= new ArrayList<>();

		for (int i= 0; i < MAX_ID; i++) {
			cpuUsages.add(createCpuUsage(CLIENT, ENVIRONMENT, STARTDATE, new BigDecimal(11)));
		}

		List<Tuple> tuples= new ArrayList<>();

		// Act
		repository.save(cpuUsages);
		for (long i= 1; i <= MAX_ID; i+= offset) {
			tuples.addAll(repository.findForElasticSearch(CLIENT, ENVIRONMENT, i, offset));
		}

		// Assert
		assertThat(Long.valueOf(tuples.size()), is(MAX_ID));
	}

	private static void assertEqualsCpuUsage (CpuUsage actualCpuUsage, CpuUsage expectedCpuUsage) {
		assertThat(actualCpuUsage, not(nullValue()));
		assertThat(actualCpuUsage.getSysDatetime(), is(expectedCpuUsage.getSysDatetime()));
		assertThat(actualCpuUsage.getClient(), is(expectedCpuUsage.getClient()));
		assertThat(actualCpuUsage.getEnvironment(), is(expectedCpuUsage.getEnvironment()));
		assertThat(actualCpuUsage.getCpuUsage(), is(closeTo(expectedCpuUsage.getCpuUsage(), new BigDecimal(PRECISION))));
	}

	private static CpuUsage createCpuUsage (String client, String environment, Timestamp sysDatetime, BigDecimal cpuUsageValue) {
		CpuUsage cpuUsage= new CpuUsage();
		cpuUsage.setClient(client);
		cpuUsage.setEnvironment(environment);
		cpuUsage.setSysDatetime(sysDatetime);
		cpuUsage.setCpuUsage(cpuUsageValue);
		cpuUsage.setHash(Hashing.sha256().hashString(sysDatetime.toString(), Charsets.UTF_8).toString());
		return cpuUsage;
	}

}
