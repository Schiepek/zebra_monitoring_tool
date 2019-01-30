/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.cpu;

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
import ch.ergon.zebra.monitoring.arff.attribute.NumericAttribute;
import ch.ergon.zebra.monitoring.data.querydsl.domain.CpuUsage;
import ch.ergon.zebra.monitoring.service.arff.OracleTypeConverter;
import ch.ergon.zebra.monitoring.service.mapping.ActionMappings;

public class CpuUsageMappingsTest {

	@Test
	public void testCreateCpuUsage () throws IOException, ParseException {

		// Arrange
		TypeConverter typeConverter= new OracleTypeConverter();
		ActionMappings<CpuUsage> actionMappings= new CpuUsageActionMappings(new OracleTypeConverter());
		final Instance instance= new Instance(null);
		CpuUsage cpuUsage= new CpuUsage();
		Locale locale= Locale.GERMAN;

		final String dateTimeValueFormat= "dd.MM.yyyy HH:mm:ss";
		final String sysdatetimeValue= "18.02.2014 18:54:36";
		final String cpuUsageValue= "25.2";

		addDateAttribute("sysdatetime", dateTimeValueFormat, locale, sysdatetimeValue, instance);
		addNumericAttribute("cpuUsage", cpuUsageValue, instance);

		// Act
		instance.getValues().forEach( (pair) -> actionMappings.getMapping(pair.getKey()).accept(cpuUsage, pair.getKey(), instance));

		// Assert
		assertThat(cpuUsage.getSysDatetime(), is(typeConverter.getDateValue(sysdatetimeValue, new SimpleDateFormat(dateTimeValueFormat))));
		assertThat(cpuUsage.getCpuUsage(), is(typeConverter.getNumericValue(cpuUsageValue)));
	}

	private void addNumericAttribute (String attributeName, String value, Instance instance) {
		final Attribute attribute= new NumericAttribute(attributeName);
		instance.setValue(attribute, value);
	}

	private void addDateAttribute (String attributeName, String dateFormat, Locale locale, String value, Instance instance) {
		final Attribute attribute= new DateAttribute(attributeName, dateFormat, locale);
		instance.setValue(attribute, value);
	}

}
