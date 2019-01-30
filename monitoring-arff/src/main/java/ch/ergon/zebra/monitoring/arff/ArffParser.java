/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */
package ch.ergon.zebra.monitoring.arff;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ergon.zebra.monitoring.arff.attribute.Attribute;
import ch.ergon.zebra.monitoring.arff.attribute.DateAttribute;
import ch.ergon.zebra.monitoring.arff.attribute.EnumerationAttribute;
import ch.ergon.zebra.monitoring.arff.attribute.NumericAttribute;
import ch.ergon.zebra.monitoring.arff.attribute.TextAttribute;
import ch.ergon.zebra.monitoring.arff.source.Source;

public final class ArffParser<T> {

	/** @ATTRIBUTE mandant {0001} **/
	private static final Pattern ENUMERATION= Pattern.compile("@ATTRIBUTE\\s+(\\w*)\\s+\\{(.*)}", Pattern.CASE_INSENSITIVE);

	/** @ATTRIBUTE sessionAgeInMinutes numeric **/
	private static final Pattern NUMERIC= Pattern.compile("@ATTRIBUTE\\s+(\\w*)\\snumeric", Pattern.CASE_INSENSITIVE);

	/** @ATTRIBUTE userAgent string **/
	private static final Pattern STRING= Pattern.compile("@ATTRIBUTE\\s+(\\w*)\\sstring", Pattern.CASE_INSENSITIVE);

	/** @ATTRIBUTE minActionDate date "dd.MM.yyyy" **/
	private static final Pattern DATE= Pattern.compile("@ATTRIBUTE\\s+(\\w*)\\sdate\\s+\"(.*)\"", Pattern.CASE_INSENSITIVE);

	private static final LineParser LINE_PARSER= new LineParser();

	private final Environment environment;

	private final Source source;

	private final List<Attribute> attributes= new ArrayList<>();

	public ArffParser (Source source, Environment environment) {
		this.source= source;
		this.environment= environment;
		initialize();
	}

	/**
	 * Loads the metadata from the file.
	 */
	private void initialize () {
		final InputStream inputStream= source.getInputStream();
		try {
			final BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
			final Stream<String> lines= bufferedReader.lines();
			lines
				.filter(line -> isAttribute(line))
				.forEach(line -> readAttribute(line));
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
	}

	private void readAttribute (String line) {
		final Matcher enumerationMatcher= ENUMERATION.matcher(line);
		if (enumerationMatcher.matches()) {
			final String name= enumerationMatcher.group(1);
			final String enums= enumerationMatcher.group(2);
			attributes.add(new EnumerationAttribute(name, enums));
			return;
		}

		final Matcher numericMatcher= NUMERIC.matcher(line);
		if (numericMatcher.matches()) {
			final String name= numericMatcher.group(1);
			attributes.add(new NumericAttribute(name));
			return;
		}

		final Matcher stringMatcher= STRING.matcher(line);
		if (stringMatcher.matches()) {
			final String name= stringMatcher.group(1);
			attributes.add(new TextAttribute(name));
			return;
		}

		final Matcher dateMatcher= DATE.matcher(line);
		if (dateMatcher.matches()) {
			final String name= dateMatcher.group(1);
			final String dateformat= dateMatcher.group(2);
			attributes.add(new DateAttribute(name, dateformat, environment.getLocale()));
			return;
		}

		throw new IllegalStateException("Parsing of the following attribute definition: " + line);
	}

	public Map<String, Attribute> getAttributes () {
		final Map<String, Attribute> result= new HashMap<>();
		attributes.forEach( (Attribute attribute) -> result.put(attribute.getName(), attribute));

		return result;
	}

	public void forEach (InstanceHandler<T> handler) {
		handler.preHandle();

		final InputStream inputStream= source.getInputStream();
		try {
			final BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
			final Stream<String> lines= bufferedReader.lines();
			lines
				.filter(line -> !isComment(line) && !isAttribute(line) && !isData(line) && !isRelation(line))
				.forEach(line -> processLine(line, handler));
		} finally {
			IOUtils.closeQuietly(inputStream);
		}

		handler.postHandle();
	}

	public void replaceAttribute (Attribute attribute) {
		final String name= attribute.getName();
		for (int i= 0; i < attributes.size(); i++) {
			if (attributes.get(i).getName().equals(name)) {
				attributes.set(i, attribute);
				return;
			}
		}
	}

	private void processLine (String line, InstanceHandler<T> handler) {
		final Instance instance= parseLine(line);
		handler.handle(instance);
	}

	private Instance parseLine (String line) {
		final Instance instance= new Instance(line);
		final List<String> tokens= LINE_PARSER.getTokens(line);

		if (tokens.size() != attributes.size()) {
			final String message= createAttributeErrorMessage(line, attributes.size(), tokens.size());
			throw new ArffParserException(message);
		}

		for (int i= 0; i < tokens.size(); i++) {
			final Attribute attribute= attributes.get(i);
			final String token= tokens.get(i);
			instance.setValue(attribute, token);
		}

		return instance;
	}

	private static boolean isComment (String line) {
		return StringUtils.isBlank(line) || StringUtils.startsWith(line, "%");
	}

	private static boolean isAttribute (String line) {
		return StringUtils.startsWith(line, "@ATTRIBUTE");
	}

	private static boolean isData (String line) {
		return StringUtils.startsWith(line, "@DATA");
	}

	private static boolean isRelation (String line) {
		return StringUtils.startsWith(line, "@RELATION");
	}

	private static String createAttributeErrorMessage (String line, int attributeSize, int tokenSize) {
		final StringBuilder sb= new StringBuilder();
		sb.append("Wrong Number of attributes. expected: ");
		sb.append(attributeSize);
		sb.append(" , actual: ");
		sb.append(tokenSize);
		sb.append("\nLine: ");
		sb.append(line);

		return sb.toString();
	}

}
