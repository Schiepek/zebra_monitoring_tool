/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.data.profiling;

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
import ch.ergon.zebra.monitoring.data.querydsl.domain.Profiling;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import com.mysema.query.Tuple;

public class SqlUseCaseProfilerRepositoryTest extends AbstractRepositoryTest {

	private static final double PRECISION= 0.0001;

	private static final Timestamp STARTDATE= new Timestamp(new DateTime(2000, 1, 1, 0, 0, 0, 0).getMillis());

	private static final String CLIENT= "coop";

	private static final String ENVIRONMENT= "pjazz";

	private static final Long MAX_ID= 100L;

	private SqlUseCaseProfilerRepository repository;

	@Override
	protected void doSetUp () {
		repository= new SqlUseCaseProfilerRepository(getTemplates(), getConnectionFactory());
	}

	@Test
	public void testDataExists () throws SQLException {
		// Arrange
		Profiling profiling= createProfiling(CLIENT, ENVIRONMENT, STARTDATE, "kst_1.1", new BigDecimal(45.68));
		List<Profiling> profilings= Lists.newArrayList(profiling);

		// Act
		repository.save(profilings);

		// Assert
		assertThat(repository.dataExists("wrong_client", "wrong_environment"), is(false));
		assertThat(repository.dataExists(CLIENT, ENVIRONMENT), is(true));
	}

	@Test
	public void testFindById () {
		// Arrange
		Profiling expectedProfiling1= createProfiling("cl_1", "en_1", STARTDATE, "kst_1.1", new BigDecimal(45.68));
		Profiling expectedProfiling2= createProfiling("cl_2", "en_2", STARTDATE, "kst_1.2", new BigDecimal(12.1));
		List<Profiling> profilings= Lists.newArrayList(expectedProfiling1, expectedProfiling2);

		// Act
		repository.save(profilings);

		Profiling actualProfiling1= repository.findById(1);
		Profiling actualProfiling2= repository.findById(2);

		// Assert
		assertEqualsProfiling(actualProfiling1, expectedProfiling1);
		assertEqualsProfiling(actualProfiling2, expectedProfiling2);

	}

	@Test
	public void testFindHashes () throws ParseException {
		// Arrange
		ArrayList<Profiling> profilings= new ArrayList<>();

		final Date startDate= new Date(STARTDATE.getTime());
		for (int i= 0; i < MAX_ID; i++) {
			profilings.add(createProfiling(CLIENT, ENVIRONMENT, addDays(startDate, i), "kst", new BigDecimal(11)));
		}

		profilings.get(60).setClient("anotherClient");
		profilings.get(65).setEnvironment("anotherEnvironment");

		Timestamp minTimestamp= profilings.get(20).getSysDatetime();
		Timestamp maxTimestamp= profilings.get(79).getSysDatetime();

		// Act
		repository.save(profilings);
		Set<String> hashes= repository.findHashes(CLIENT, ENVIRONMENT, minTimestamp, maxTimestamp);

		// Assert
		assertThat(hashes.size(), is(58));
	}

	@Test
	public void testGetMaxId () {
		// Arrange
		ArrayList<Profiling> profilings= new ArrayList<>();

		for (int i= 0; i < MAX_ID; i++) {
			profilings.add(createProfiling(CLIENT, ENVIRONMENT, STARTDATE, "kst", new BigDecimal(11)));
		}

		// Act
		repository.save(profilings);

		// Assert
		assertThat(MAX_ID, is(repository.getMaxId(CLIENT, ENVIRONMENT)));
	}

	@Test
	public void testFindForElasticsearch () {
		// Arrange
		List<Profiling> profilings= new ArrayList<>();

		for (int i= 0; i < MAX_ID; i++) {
			profilings.add(createProfiling(CLIENT, ENVIRONMENT, STARTDATE, "kst", new BigDecimal(11)));
		}

		profilings.get(40).setClient("anotherClient");
		profilings.get(45).setEnvironment("anotherEnvironment");

		// Act
		repository.save(profilings);
		List<Tuple> tuples= repository.findForElasticSearch(CLIENT, ENVIRONMENT, 20, 40);

		// Assert
		assertThat(tuples.size(), is(38));
	}

	@Test
	public void testFindForElasticsearchWithPaging () {
		// Arrange
		final long offset= 20;

		List<Profiling> profilings= new ArrayList<>();

		for (int i= 0; i < MAX_ID; i++) {
			profilings.add(createProfiling(CLIENT, ENVIRONMENT, STARTDATE, "kst", new BigDecimal(11)));
		}

		List<Tuple> tuples= new ArrayList<>();

		// Act
		repository.save(profilings);
		for (long i= 1; i <= MAX_ID; i+= offset) {
			tuples.addAll(repository.findForElasticSearch(CLIENT, ENVIRONMENT, i, offset));
		}

		// Assert
		assertThat(Long.valueOf(tuples.size()), is(MAX_ID));
	}

	private static void assertEqualsProfiling (Profiling actualProfiling, Profiling expectedProfiling) {
		assertThat(actualProfiling, not(nullValue()));
		assertThat(actualProfiling.getSysDatetime(), is(expectedProfiling.getSysDatetime()));
		assertThat(actualProfiling.getClient(), is(expectedProfiling.getClient()));
		assertThat(actualProfiling.getEnvironment(), is(expectedProfiling.getEnvironment()));
		assertThat(actualProfiling.getFocusKstNummer(), is(expectedProfiling.getFocusKstNummer()));
		assertThat(actualProfiling.getTotalFetchTimeMs(), is(closeTo(expectedProfiling.getTotalFetchTimeMs(), new BigDecimal(PRECISION))));
	}

	private static Profiling createProfiling (String client, String environment, Timestamp sysDatetime, String focusKstNummer,
		BigDecimal totalFetchTimeMs) {
		Profiling profiling= new Profiling();
		profiling.setClient(client);
		profiling.setEnvironment(environment);
		profiling.setSysDatetime(sysDatetime);
		profiling.setFocusKstNummer(focusKstNummer);
		profiling.setTotalFetchTimeMs(totalFetchTimeMs);
		profiling.setHash(Hashing.sha256().hashString(sysDatetime.toString(), Charsets.UTF_8).toString());
		return profiling;
	}

}
