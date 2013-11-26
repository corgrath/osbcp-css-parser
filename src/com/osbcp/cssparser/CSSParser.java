/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 */

package com.osbcp.cssparser;

import java.util.ArrayList;
import java.util.List;

import com.osbcp.cssparser.IncorrectFormatException.ErrorCode;

/**
 * Main logic for the CSS parser.
 * 
 * @author <a href="mailto:christoffer@christoffer.me">Christoffer Pettersson</a>
 */

public final class CSSParser {

	/**
	 * Reads CSS as a String and returns back a list of Rules.
	 * 
	 * @param css A String representation of CSS.
	 * @return A list of Rules
	 * @throws Exception If any errors occur.
	 */

	public static List<Rule> parse(final String css) throws Exception {

		CSSParser parser = new CSSParser();

		List<Rule> rules = new ArrayList<Rule>();

		if (css == null || css.trim().length() == 0) {
			return rules;
		}

		for (int i = 0; i < css.length(); i++) {

			char c = css.charAt(i);

			if (i < css.length() - 1) {

				char nextC = css.charAt(i + 1);
				parser.parse(rules, c, nextC);

			} else {

				parser.parse(rules, c, null);

			}

		}

		return rules;
	}

	private final List<String> selectorNames;
	private String selectorName;
	private String propertyName;
	private String valueName;
	//	private Map<String, String> map;
	private final List<PropertyValue> values;
	private State state;
	private Character previousChar;
	private State beforeCommentMode;

	/**
	 * Creates a new parser.
	 */

	private CSSParser() {
		this.selectorName = "";
		this.propertyName = "";
		this.valueName = "";
		//		this.map = new LinkedHashMap<String, String>();
		this.values = new ArrayList<PropertyValue>();
		this.state = State.INSIDE_SELECTOR;
		this.previousChar = null;
		this.beforeCommentMode = null;
		this.selectorNames = new ArrayList<String>();
	}

	/**
	 * Main parse logic.
	 * 
	 * @param rules The list of rules.
	 * @param c The current currency.
	 * @param nextC The next currency (or null).
	 * @throws Exception If any errors occurs.
	 */

	private void parse(final List<Rule> rules, final Character c, final Character nextC) throws Exception {

		// Special case if we find a comment
		if (Chars.SLASH.equals(c) && Chars.STAR.equals(nextC)) {

			// It's possible to find a comment in a comment
			if (state != State.INSIDE_COMMENT) {
				beforeCommentMode = state;
			}

			state = State.INSIDE_COMMENT;
		}

		switch (state) {

			case INSIDE_SELECTOR: {
				parseSelector(c);
				break;
			}
			case INSIDE_COMMENT: {
				parseComment(c);
				break;
			}
			case INSIDE_PROPERTY_NAME: {
				parsePropertyName(rules, c);
				break;
			}
			case INSIDE_VALUE: {
				parseValue(c);
				break;
			}
			case INSIDE_VALUE_ROUND_BRACKET: {
				parseValueInsideRoundBrackets(c);
				break;
			}

		}

		// Save the previous character
		previousChar = c;

	}

	/**
	 * Parse a value.
	 * 
	 * @param c The current character.
	 * @throws IncorrectFormatException If any errors occur.
	 */

	private void parseValue(final Character c) throws IncorrectFormatException {

		// Special case if the value is a data uri, the value can contain a ;
		//		boolean valueHasDataURI = valueName.toLowerCase().indexOf("data:") != -1;

		if (Chars.SEMI_COLON.equals(c)) {

			// Store it in the values map
			PropertyValue pv = new PropertyValue(propertyName.trim(), valueName.trim());
			values.add(pv);
			//			map.put(propertyName.trim(), valueName.trim());
			propertyName = "";
			valueName = "";

			state = State.INSIDE_PROPERTY_NAME;
			return;

		} else if (Chars.ROUND_BRACKET_BEG.equals(c)) {

			valueName += Chars.ROUND_BRACKET_BEG;

			state = State.INSIDE_VALUE_ROUND_BRACKET;
			return;

		} else if (Chars.COLON.equals(c)) {

			throw new IncorrectFormatException(ErrorCode.FOUND_COLON_WHILE_READING_VALUE, "The value '" + valueName.trim() + "' for property '" + propertyName.trim() + "' in the selector '" + selectorName.trim() + "' had a ':' character.");

		} else if (Chars.BRACKET_END.equals(c)) {

			throw new IncorrectFormatException(ErrorCode.FOUND_END_BRACKET_BEFORE_SEMICOLON, "The value '" + valueName.trim() + "' for property '" + propertyName.trim() + "' in the selector '" + selectorName.trim() + "' should end with an ';', not with '}'.");

		} else {

			valueName += c;
			return;

		}

	}

	/**
	 * Parse value inside a round bracket (
	 * 
	 * @param c The current character.
	 * @throws IncorrectFormatException If any error occurs.
	 */

	private void parseValueInsideRoundBrackets(final Character c) throws IncorrectFormatException {

		if (Chars.ROUND_BRACKET_END.equals(c)) {

			valueName += Chars.ROUND_BRACKET_END;
			state = State.INSIDE_VALUE;
			return;

		} else {

			valueName += c;
			return;

		}

	}

	/**
	 * Parse property name.
	 * 
	 * @param rules The list of rules.
	 * @param c The current character.
	 * @throws IncorrectFormatException If any error occurs
	 */

	private void parsePropertyName(final List<Rule> rules, final Character c) throws IncorrectFormatException {

		if (Chars.COLON.equals(c)) {

			state = State.INSIDE_VALUE;
			return;

		} else if (Chars.SEMI_COLON.equals(c)) {

			throw new IncorrectFormatException(ErrorCode.FOUND_SEMICOLON_WHEN_READING_PROPERTY_NAME, "Unexpected character '" + c + "' for property '" + propertyName.trim() + "' in the selector '" + selectorName.trim() + "' should end with an ';', not with '}'.");

		} else if (Chars.BRACKET_END.equals(c)) {

			Rule rule = new Rule();

			/*
			 * Huge logic to create a new rule
			 */

			for (String s : selectorNames) {
				Selector selector = new Selector(s.trim());
				rule.addSelector(selector);
			}
			selectorNames.clear();

			Selector selector = new Selector(selectorName.trim());
			selectorName = "";
			rule.addSelector(selector);

			// Add the property values
			for (PropertyValue pv : values) {
				rule.addPropertyValue(pv);
			}

			//			for (Entry<String, String> entry : map.entrySet()) {
			//
			//				String property = entry.getKey();
			//				String value = entry.getValue();
			//
			//				PropertyValue propertyValue = new PropertyValue(property, value);
			//				rule.addPropertyValue(propertyValue);
			//
			//			}

			//			map.clear();
			values.clear();

			if (!rule.getPropertyValues().isEmpty()) {
				rules.add(rule);
			}

			state = State.INSIDE_SELECTOR;

		} else {

			propertyName += c;
			return;

		}

	}

	/**
	 * Parse a selector.
	 * 
	 * @param c The current character.
	 */

	private void parseComment(final Character c) {

		if (Chars.STAR.equals(previousChar) && Chars.SLASH.equals(c)) {

			state = beforeCommentMode;
			return;

		}

	}

	/**
	 * Parse a selector.
	 * 
	 * @param c The current character.
	 * @throws IncorrectFormatException If an error occurs.
	 */

	private void parseSelector(final Character c) throws IncorrectFormatException {

		if (Chars.BRACKET_BEG.equals(c)) {

			state = State.INSIDE_PROPERTY_NAME;
			return;

		} else if (Chars.COMMA.equals(c)) {

			if (selectorName.trim().length() == 0) {
				throw new IncorrectFormatException(ErrorCode.FOUND_COLON_WHEN_READING_SELECTOR_NAME, "Found an ',' in a selector name without any actual name before it.");
			}

			selectorNames.add(selectorName.trim());
			selectorName = "";

		} else {

			selectorName += c;
			return;

		}

	}

}
