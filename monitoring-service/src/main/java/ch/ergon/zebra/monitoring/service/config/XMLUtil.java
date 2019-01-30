/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

public final class XMLUtil {

	private static final Logger LOG= LoggerFactory.getLogger(XMLUtil.class);

	private XMLUtil () {
		// utility class
	}

	public static String getText (Element element, String fieldname) {
		String value= element.getChildText(fieldname);
		checkNotNull(value, fieldname);
		return value;
	}

	public static Integer getNumber (Element element, String fieldname) {
		try {
			String value= element.getChildText(fieldname);
			checkNotNull(value, fieldname);
			return Integer.valueOf(value);
		} catch (NumberFormatException e) {
			LOG.error(fieldname + " is not a correct number", e);
			throw new IllegalStateException(e);
		}
	}

	public static String getAttribute (Element element, String attributename) {
		String value= element.getAttributeValue(attributename);
		checkNotNull(value, attributename);
		return value;
	}

	public static Element getRoot (InputStream inputStream) {
		try {
			Preconditions.checkNotNull(inputStream, "Inputstream can not be null");
			SAXBuilder builder= new SAXBuilder();
			Document document= builder.build(inputStream);
			return document.getRootElement();
		} catch (IOException | JDOMException e) {
			LOG.error("Failed to parse configuration", e);
			throw new IllegalStateException(e);
		}
	}

	public static Element getChild (Element element, String name) {
		Element child= element.getChild(name);
		Preconditions.checkNotNull(child, "There is no xml child element \"" + name + "\"");
		return child;
	}

	public static List<Element> getChildren (Element element, String name) {
		List<Element> children= element.getChildren(name);
		Preconditions.checkNotNull(children, "There are no xml children elements for \"" + name + "\"");
		return children;
	}

	private static void checkNotNull (String value, String fieldname) {
		Preconditions.checkNotNull(value, "'" + fieldname + "' is not defined in xml");
	}

}
