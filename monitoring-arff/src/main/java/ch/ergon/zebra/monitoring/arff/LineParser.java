/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */
package ch.ergon.zebra.monitoring.arff;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/*package*/class LineParser {

	private static final char COMMA= ',';

	private static final char SINGLE_QUOTE_AS_CHAR= '\'';

	private static final String SINGLE_QUOTE_AS_STRING= "'";

	/**
	 * Splits the given <code>line</code> by comma.
	 *
	 * <p>Commas inside of single quotes are ignored</p>
	 * <p>Leading/trailing quotes get removed</p>
	 *
	 * @param line The line to parse.
	 * @return Returns the tokens from the given line or an empty list.
	 */
	public List<String> getTokens (String line) {
		final List<String> result= new ArrayList<>();

		if (StringUtils.isBlank(line)) {
			return result;
		}

		int tokenStart= 0;
		boolean inQuotes= false;
		for (int i= 0; i < line.length(); i++) {
			final char currentCharacter= line.charAt(i);
			if (currentCharacter == SINGLE_QUOTE_AS_CHAR) {
				inQuotes= !inQuotes; // toggle state
			}

			final boolean isLastCharacter= (i == line.length() - 1);
			if (isLastCharacter) {
				final String token= getToken(line, tokenStart);
				result.add(token);
			} else if (currentCharacter == COMMA && !inQuotes) {
				final String token= getToken(line, tokenStart, i);
				result.add(token);
				tokenStart= i + 1;
			}
		}

		return result;
	}

	private static String getToken (String line, int tokenStart, int i) {
		final String token= line.substring(tokenStart, i);

		return getWithoutQuotes(token);
	}

	private static String getToken (String line, int tokenStart) {
		final String token= line.substring(tokenStart);

		return getWithoutQuotes(token);
	}

	private static String getWithoutQuotes (String token) {
		if (StringUtils.startsWith(token, SINGLE_QUOTE_AS_STRING) && StringUtils.endsWith(token, SINGLE_QUOTE_AS_STRING)) {
			return token.substring(1, token.length() - 1);
		}

		return token;
	}

}
