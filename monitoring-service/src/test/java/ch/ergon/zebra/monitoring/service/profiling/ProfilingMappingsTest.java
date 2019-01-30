/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.profiling;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.Test;

import ch.ergon.zebra.monitoring.arff.Instance;
import ch.ergon.zebra.monitoring.arff.TypeConverter;
import ch.ergon.zebra.monitoring.arff.attribute.Attribute;
import ch.ergon.zebra.monitoring.arff.attribute.DateAttribute;
import ch.ergon.zebra.monitoring.arff.attribute.EnumerationAttribute;
import ch.ergon.zebra.monitoring.arff.attribute.NumericAttribute;
import ch.ergon.zebra.monitoring.arff.attribute.TextAttribute;
import ch.ergon.zebra.monitoring.data.querydsl.domain.Profiling;
import ch.ergon.zebra.monitoring.service.arff.OracleTypeConverter;
import ch.ergon.zebra.monitoring.service.arff.WeekdayAttribute;
import ch.ergon.zebra.monitoring.service.mapping.ActionMappings;

public class ProfilingMappingsTest {

	@Test
	public void testCreateProfiling_jazz_2_2_9 () throws IOException, ParseException {
		//Arrange
		TypeConverter typeConverter= new OracleTypeConverter();
		ActionMappings<Profiling> actionMappings= new ProfilingActionMappings(typeConverter);
		final Instance instance= new Instance(null);
		Profiling profiling= new Profiling();
		Locale locale= Locale.GERMAN;

		final String dateValueFormat= "dd.MM.yyyy";
		final String dateTimeValueFormat= "dd.MM.yyyy HH:mm:ss";

		final String useCaseIdValue= "useCaseIdValue";
		final String serverExecutionTimeMsValue= "523.00";
		final String serverOverheadTimeMsValue= "456.1";
		final String requestIdValue= "234.8";
		final String sessionIdValue= "sessionIdValue";
		final String sessionAgeInMinutesValue= "45.8";
		final String userAgentValue= "userAgentValue";
		final String hostValue= "hostValue";
		final String useCaseExpectsNavigationTimeValue= "true";
		final String mandantValue= "0001";
		final String loginNameValue= "loginNameValue";
		final String focusDateValue= "18.02.2014";
		final String focusDateMinusSystemDateValue= "78.1";
		final String focusKstNummerValue= "54654";
		final String focusKstNrOfMitarbeiterValue= "1.2";
		final String nrOfAccessibleKostenstelleValue= "99.5";
		final String limitPeeNummerValue= "limitPeeNummerValue";
		final String sysdatetimeValue= "18.02.2014 18:54:36";
		final String weekdayValue= "DI";

		addTextAttribute("useCaseId", useCaseIdValue, instance);
		addNumericAttribute("serverExecutionTimeMs", serverExecutionTimeMsValue, instance);
		addNumericAttribute("serverOverheadTimeMs", serverOverheadTimeMsValue, instance);
		addNumericAttribute("requestId", requestIdValue, instance);
		addTextAttribute("sessionId", sessionIdValue, instance);
		addNumericAttribute("sessionAgeInMinutes", sessionAgeInMinutesValue, instance);
		addTextAttribute("userAgent", userAgentValue, instance);
		addTextAttribute("host", hostValue, instance);
		addTextAttribute("useCaseExpectsNavigationTime", useCaseExpectsNavigationTimeValue, instance);
		addEnumerationAttribute("mandant", "0001", mandantValue, instance);
		addTextAttribute("loginName", loginNameValue, instance);
		addDateAttribute("focusDate", dateValueFormat, locale, focusDateValue, instance);
		addNumericAttribute("focusDateMinusSystemDate", focusDateMinusSystemDateValue, instance);
		addTextAttribute("focusKstNummer", focusKstNummerValue, instance);
		addNumericAttribute("focusKstNrOfMitarbeiter", focusKstNrOfMitarbeiterValue, instance);
		addNumericAttribute("nrOfAccessibleKostenstelle", nrOfAccessibleKostenstelleValue, instance);
		addTextAttribute("limitPeeNummer", limitPeeNummerValue, instance);
		addDateAttribute("sysdatetime", dateTimeValueFormat, locale, sysdatetimeValue, instance);
		addWeekdayAttribute("weekday", weekdayValue, instance);

		//Act
		instance.getValues().forEach( (pair) -> actionMappings.getMapping(pair.getKey()).accept(profiling, pair.getKey(), instance));

		//Assert
		assertThat(profiling.getUsecaseId(), is(typeConverter.getTextValue(useCaseIdValue)));
		assertThat(profiling.getServerExecutionTimeMs(), is(typeConverter.getNumericValue(serverExecutionTimeMsValue)));
		assertThat(profiling.getServerOverheadTimeMs(), is(typeConverter.getNumericValue(serverOverheadTimeMsValue)));
		assertThat(profiling.getRequestId(), is(typeConverter.getNumericValue(requestIdValue)));
		assertThat(profiling.getSessionId(), is(typeConverter.getTextValue(sessionIdValue)));
		assertThat(profiling.getSessionAgeInMinutes(), is(typeConverter.getNumericValue(sessionAgeInMinutesValue)));
		assertThat(profiling.getUserAgent(), is(typeConverter.getTextValue(userAgentValue)));
		assertThat(profiling.getHost(), is(typeConverter.getTextValue(hostValue)));
		assertThat(profiling.getUsecaseExpectsNavTime(), is(typeConverter.getBooleanValue(useCaseExpectsNavigationTimeValue)));
		assertThat(profiling.getMandant(), is(typeConverter.getTextValue(mandantValue)));
		assertThat(profiling.getLoginName(), is(typeConverter.getTextValue(loginNameValue)));
		assertThat(profiling.getFocusDate(), is(typeConverter.getDateValue(focusDateValue, new SimpleDateFormat(dateValueFormat))));
		assertThat(profiling.getFocusDateMinusSystemdate(), is(typeConverter.getNumericValue(focusDateMinusSystemDateValue)));
		assertThat(profiling.getFocusKstNummer(), is(typeConverter.getTextValue(focusKstNummerValue)));
		assertThat(profiling.getFocusKstNrOfMitarbeiter(), is(typeConverter.getNumericValue(focusKstNrOfMitarbeiterValue)));
		assertThat(profiling.getNrOfAccKostenstelleToday(), is(typeConverter.getNumericValue(nrOfAccessibleKostenstelleValue)));
		assertThat(profiling.getLimitPeeNummer(), is(typeConverter.getTextValue(limitPeeNummerValue)));
		assertThat(profiling.getSysDatetime(), is(typeConverter.getDateValue(sysdatetimeValue, new SimpleDateFormat(dateTimeValueFormat))));
		assertThat(profiling.getWeekday(), is(typeConverter.getTextValue(weekdayValue)));
	}

	@Test
	public void testCreateProfiling_jazz_2_3_17 () throws IOException, ParseException {
		//Arrange
		TypeConverter typeConverter= new OracleTypeConverter();
		ActionMappings<Profiling> actionMappings= new ProfilingActionMappings(typeConverter);
		final Instance instance= new Instance(null);
		Profiling profiling= new Profiling();

		final String nrOfAccessibleKostenstelleTodayValue= "45.5";

		addNumericAttribute("nrOfAccessibleKostenstelleToday", nrOfAccessibleKostenstelleTodayValue, instance);

		//Act
		instance.getValues().forEach( (pair) -> actionMappings.getMapping(pair.getKey()).accept(profiling, pair.getKey(), instance));

		//Assert
		assertThat(profiling.getNrOfAccKostenstelleToday(), is(typeConverter.getNumericValue(nrOfAccessibleKostenstelleTodayValue)));
	}

	@Test
	public void testCreateProfiling_jazz_2_4_14 () throws IOException, ParseException {
		//Arrange
		TypeConverter typeConverter= new OracleTypeConverter();
		ActionMappings<Profiling> actionMappings= new ProfilingActionMappings(typeConverter);
		final Instance instance= new Instance(null);
		Profiling profiling= new Profiling();

		final String serverWaitTimeMsValue= "984.2";
		final String serverExecutionNoWaitTimeMsValue= "45.0";
		final String statementsExecutedValue= "99.3";
		final String totalExecutionTimeMsValue= "0.5";
		final String totalFetchTimeMsValue= "5.6";
		final String totalDatabaseTimeMsValue= "8.2";
		final String nrOfAccessibleKostenstelleTodayValue= "45.5";

		addNumericAttribute("serverWaitTimeMs", serverWaitTimeMsValue, instance);
		addNumericAttribute("serverExecutionNoWaitTimeMs", serverExecutionNoWaitTimeMsValue, instance);
		addNumericAttribute("statementsExecuted", statementsExecutedValue, instance);
		addNumericAttribute("totalExecutionTimeMs", totalExecutionTimeMsValue, instance);
		addNumericAttribute("totalFetchTimeMs", totalFetchTimeMsValue, instance);
		addNumericAttribute("totalDatabaseTimeMs", totalDatabaseTimeMsValue, instance);
		addNumericAttribute("nrOfAccessibleKostenstelleToday", nrOfAccessibleKostenstelleTodayValue, instance);

		//Act
		instance.getValues().forEach( (pair) -> actionMappings.getMapping(pair.getKey()).accept(profiling, pair.getKey(), instance));

		//Assert
		assertThat(profiling.getServerWaitTimeMs(), is(typeConverter.getNumericValue(serverWaitTimeMsValue)));
		assertThat(profiling.getServerExecNoWaitTimeMs(), is(typeConverter.getNumericValue(serverExecutionNoWaitTimeMsValue)));
		assertThat(profiling.getStatementsExecuted(), is(typeConverter.getNumericValue(statementsExecutedValue)));
		assertThat(profiling.getTotalExecutionTimeMs(), is(typeConverter.getNumericValue(totalExecutionTimeMsValue)));
		assertThat(profiling.getTotalFetchTimeMs(), is(typeConverter.getNumericValue(totalFetchTimeMsValue)));
		assertThat(profiling.getTotalDatabaseTimeMs(), is(typeConverter.getNumericValue(totalDatabaseTimeMsValue)));
		assertThat(profiling.getNrOfAccKostenstelleToday(), is(typeConverter.getNumericValue(nrOfAccessibleKostenstelleTodayValue)));
	}

	@Test
	public void testCreateProfiling_maps_1_51_0 () throws IOException, ParseException {
		//Arrange
		TypeConverter typeConverter= new OracleTypeConverter();
		ActionMappings<Profiling> actionMappings= new ProfilingActionMappings(typeConverter);
		final Instance instance= new Instance(null);
		Profiling profiling= new Profiling();

		final String clientIpAddressValue= "192.168.0.15";

		addTextAttribute("clientIpAddress", clientIpAddressValue, instance);

		//Act
		instance.getValues().forEach( (pair) -> actionMappings.getMapping(pair.getKey()).accept(profiling, pair.getKey(), instance));

		//Assert
		assertThat(profiling.getClientIpAddress(), is(typeConverter.getTextValue(clientIpAddressValue)));

	}

	@Test(expected= NullPointerException.class)
	public void testCreateProfilingWidthNonExistingAttribute () throws IOException, ParseException {

		//Arrange
		TypeConverter typeConverter= new OracleTypeConverter();
		ActionMappings<Profiling> actionMappings= new ProfilingActionMappings(typeConverter);
		final Instance instance= new Instance(null);
		Profiling profiling= new Profiling();

		final String nonExistingValue= "523.00";
		addNumericAttribute("nonExisting", nonExistingValue, instance);

		//Act
		instance.getValues().forEach( (pair) -> actionMappings.getMapping(pair.getKey()).accept(profiling, pair.getKey(), instance));

		//Assert
		assertThat(profiling.getServerExecutionTimeMs(), is(typeConverter.getNumericValue(nonExistingValue)));
	}

	private void addEnumerationAttribute (String attributeName, String enums, String value, Instance instance) {
		final Attribute attribute= new EnumerationAttribute(attributeName, enums);
		instance.setValue(attribute, value);
	}

	private void addNumericAttribute (String attributeName, String value, Instance instance) {
		final Attribute attribute= new NumericAttribute(attributeName);
		instance.setValue(attribute, value);
	}

	private void addTextAttribute (String attributeName, String value, Instance instance) {
		final Attribute attribute= new TextAttribute(attributeName);
		instance.setValue(attribute, value);
	}

	private void addDateAttribute (String attributeName, String dateFormat, Locale locale, String value, Instance instance) {
		final Attribute attribute= new DateAttribute(attributeName, dateFormat, locale);
		instance.setValue(attribute, value);
	}

	private void addWeekdayAttribute (String attributeName, String value, Instance instance) {
		final Attribute attribute= new WeekdayAttribute(attributeName);
		instance.setValue(attribute, value);
	}

}
