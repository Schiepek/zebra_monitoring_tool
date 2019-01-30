/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */
package ch.ergon.zebra.monitoring.arff;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ergon.zebra.monitoring.arff.attribute.Attribute;
import ch.ergon.zebra.monitoring.arff.attribute.DateAttribute;
import ch.ergon.zebra.monitoring.arff.attribute.EnumerationAttribute;
import ch.ergon.zebra.monitoring.arff.attribute.NumericAttribute;
import ch.ergon.zebra.monitoring.arff.attribute.TextAttribute;
import ch.ergon.zebra.monitoring.arff.source.PathSource;

import com.google.common.primitives.Doubles;

public class ArffParserTest {

	final static String TEST_FILE= "/ch/ergon/zebra/monitoring/arff/arff_parser_test.log";

	final static String TEST_FILE_WRONG_ATTRIBUTES= "/ch/ergon/zebra/monitoring/arff/arff_parser_test_wrong_attributes.log";

	@Test
	public void testMetadata () throws URISyntaxException, FileNotFoundException {
		// Arrange
		final Environment environment= new Environment(Locale.GERMAN);
		final Path path= Paths.get(ClassLoader.class.getResource(TEST_FILE).toURI());

		// Act
		final ArffParser<String> arffParser= new ArffParser<>(new PathSource(path), environment);

		// Assert
		Map<String, Attribute> attributes= arffParser.getAttributes();
		assertThat(attributes.size(), is(91));
		assertThat(attributes.get("useCaseId").getName(), is("useCaseId"));
		assertThat(attributes.get("useCaseId"), instanceOf(EnumerationAttribute.class));
		assertThat(attributes.get("serverExecutionTimeMs").getName(), is("serverExecutionTimeMs"));
		assertThat(attributes.get("serverExecutionTimeMs"), instanceOf(NumericAttribute.class));
		assertThat(attributes.get("sessionId").getName(), is("sessionId"));
		assertThat(attributes.get("sessionId"), instanceOf(TextAttribute.class));
		assertThat(attributes.get("focusDate").getName(), is("focusDate"));
		assertThat(attributes.get("focusDate"), instanceOf(DateAttribute.class));

	}

	@Test
	public void testParseInstances () throws URISyntaxException, FileNotFoundException {
		// Arrange
		final Environment environment= new Environment(Locale.GERMAN);
		final Path path= Paths.get(ClassLoader.class.getResource(TEST_FILE).toURI());
		final ArffParser<String> arffParser= new ArffParser<>(new PathSource(path), environment);
		final Map<String, Attribute> attributes= arffParser.getAttributes();
		final TypeConverter typeConverter= new MockTypeConverter();
		final MockInstanceHandler handler= new MockInstanceHandler();

		// Act
		arffParser.forEach(handler);

		// Assert
		final Instance instance= handler.getMandatoryUniqueInstance();
		assertThat(instance.getValues().size(), is(91));

		assertThat(instance.getValue(attributes.get("useCaseId"), typeConverter), is(typeConverter.getTextValue("initialPageLoad-pepWochenAnsicht")));
		assertThat(instance.getValue(attributes.get("serverExecutionTimeMs"), typeConverter), is(typeConverter.getNumericValue("834")));
		assertThat(instance.getValue(attributes.get("sessionId"), typeConverter), is(typeConverter.getTextValue("A96230A18A896A94D0AE0A4330EFEA8C")));
		assertThat(instance.getValue(attributes.get("useCaseExpectsNavigationTime"), typeConverter), is(typeConverter.getBooleanValue("true")));
		assertThat(instance.getValue(attributes.get("focusDate"), typeConverter),
			is(typeConverter.getDateValue("18.02.2014", new SimpleDateFormat("dd.MM.yyyy"))));

	}

	@Test(expected= ArffParserException.class)
	public void testParseInstancesWithWrongNumberOfAttributes () throws URISyntaxException, FileNotFoundException {
		// Arrange
		final Environment environment= new Environment(Locale.GERMAN);
		final Path path= Paths.get(ClassLoader.class.getResource(TEST_FILE_WRONG_ATTRIBUTES).toURI());
		final ArffParser<String> arffParser= new ArffParser<>(new PathSource(path), environment);
		final MockInstanceHandler handler= new MockInstanceHandler();

		// Act
		arffParser.forEach(handler);
	}

	private static class MockInstanceHandler implements InstanceHandler<String> {

		private final List<Instance> instances= new ArrayList<>();

		@Override
		public void preHandle () {
		}

		@Override
		public void handle (Instance instance) {
			instances.add(instance);
		}

		@Override
		public void postHandle () {
			return;
		}

		public Instance getMandatoryUniqueInstance () {
			if (instances.size() != 1) {
				throw new IllegalStateException("Expected exactly 1 instace. Got " + instances.size());
			}

			return instances.get(0);
		}

	}

	private static class MockTypeConverter implements TypeConverter {

		private static final Logger LOG= LoggerFactory.getLogger(MockTypeConverter.class);

		@Override
		public Timestamp getDateValue (String value, DateFormat dateFormat) {
			Date date= parseDate(value, dateFormat);
			return new Timestamp(date.getTime());
		}

		@Override
		public String getTextValue (String value) {
			return value;
		}

		@Override
		public Number getNumericValue (String value) {
			Double numericValue= Doubles.tryParse(value);
			if (numericValue != null) {
				return BigDecimal.valueOf(numericValue);
			}
			return new BigInteger(value);
		}

		@Override
		public Byte getBooleanValue (String value) {
			if (value.equals("true")) {
				return 1;
			} else if (value.equals("false")) {
				return 0;
			} else {
				String message= "Boolean value has to be either \"true\" or \"false\", but was " + value;
				LOG.error(message);
				throw new IllegalStateException(message);
			}
		}

		private Date parseDate (String value, DateFormat dateFormat) {
			try {
				return dateFormat.parse(value);
			} catch (ParseException e) {
				String message= "Parse error with value " + value;
				LOG.error(message, e);
				throw new IllegalStateException(e);
			}
		}

	}

}
