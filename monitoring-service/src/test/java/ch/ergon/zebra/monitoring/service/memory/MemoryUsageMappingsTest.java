/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.memory;

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
import ch.ergon.zebra.monitoring.data.querydsl.domain.MemoryUsage;
import ch.ergon.zebra.monitoring.service.arff.OracleTypeConverter;
import ch.ergon.zebra.monitoring.service.mapping.ActionMappings;

public class MemoryUsageMappingsTest {

	@Test
	public void testCreateMemoryUsage () throws IOException, ParseException {

		// Arrange
		TypeConverter typeConverter= new OracleTypeConverter();
		ActionMappings<MemoryUsage> actionMappings= new MemoryUsageActionMappings(typeConverter);
		final Instance instance= new Instance(null);
		MemoryUsage memoryUsage= new MemoryUsage();
		Locale locale= Locale.GERMAN;

		final String dateTimeValueFormat= "dd.MM.yyyy HH:mm:ss";
		final String sysdatetimeValue= "10.02.2012 11:22:42";
		final String totalHeapValue= "5000";
		final String freeHeapValue= "1000";

		addDateAttribute("sysdatetime", dateTimeValueFormat, locale, sysdatetimeValue, instance);
		addNumericAttribute("totalHeap", totalHeapValue, instance);
		addNumericAttribute("freeHeap", freeHeapValue, instance);

		// Act
		instance.getValues().forEach( (pair) -> actionMappings.getMapping(pair.getKey()).accept(memoryUsage, pair.getKey(), instance));

		// Assert
		assertThat(memoryUsage.getSysDatetime(), is(typeConverter.getDateValue(sysdatetimeValue, new SimpleDateFormat(dateTimeValueFormat))));
		assertThat(memoryUsage.getTotalHeap(), is(typeConverter.getNumericValue(totalHeapValue)));
		assertThat(memoryUsage.getFreeHeap(), is(typeConverter.getNumericValue(freeHeapValue)));
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
