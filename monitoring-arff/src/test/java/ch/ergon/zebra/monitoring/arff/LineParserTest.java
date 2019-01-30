/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.arff;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class LineParserTest {

	@Test
	public void testGetTokensEmptyString () {
		// Arrange
		final LineParser parser= new LineParser();

		// Act
		final List<String> tokens= parser.getTokens("");

		// Assert
		assertThat(tokens.size(), is(0));
	}

	@Test
	public void testGetTokensNull () {
		// Arrange
		final LineParser parser= new LineParser();

		// Act
		final List<String> tokens= parser.getTokens(null);

		// Assert
		assertThat(tokens.size(), is(0));
	}

	@Test
	public void testGetTokens () {
		// Arrange
		final LineParser parser= new LineParser();

		// Act
		final List<String> tokens= parser.getTokens("'A',2,'B',?,'C,D',  E");

		// Assert
		assertThat(tokens.size(), is(6));
		assertThat(tokens.get(0), is("A"));
		assertThat(tokens.get(1), is("2"));
		assertThat(tokens.get(2), is("B"));
		assertThat(tokens.get(3), is("?"));
		assertThat(tokens.get(4), is("C,D"));
		assertThat(tokens.get(5), is("  E"));
	}

}
