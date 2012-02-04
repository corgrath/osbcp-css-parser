package com.osbcp.cssparser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class CSSParser {

	private String selectorName;
	private String propertyName;
	private String valueName;
	private Map<String, String> map;

	public CSSParser() {
		this.selectorName = "";
		this.propertyName = "";
		this.valueName = "";
		this.map = new LinkedHashMap<String, String>();
	}

	public List<Rule> parse(final String css) throws Exception {

		List<Rule> rules = new ArrayList<Rule>();

		if (css == null || css.trim().isEmpty()) {
			return rules;
		}

		for (int i = 0; i < css.length(); i++) {

			char c = css.charAt(i);

			if (i < css.length() - 1) {

				char nextC = css.charAt(i + 1);
				parse(rules, c, nextC);

			} else {

				parse(rules, c, null);

			}

		}

		return rules;

	}

	private enum Mode {
		VOID,
		INSIDE_COMMENT,
		INSIDE_PROPERTY_NAME,
		INSIDE_VALUE;
	}

	private Mode mode = Mode.VOID;
	private Character previousChar = null;

	public void parse(final List<Rule> rules, final Character c, final Character nextC) throws Exception {

		switch (mode) {

			case VOID: {
				parseVoid(rules, c, nextC);
				break;
			}
			case INSIDE_COMMENT: {
				parseComment(c, nextC);
				break;
			}
			case INSIDE_PROPERTY_NAME: {
				parsePropertyName(rules, c, nextC);
				break;
			}
			case INSIDE_VALUE: {
				parseValue(rules, c, nextC);
				break;
			}
			default: {
				throw new Exception("Unknown mode '" + mode + "'.");
			}

		}

		previousChar = c;

	}

	private void parseValue(final List<Rule> rules, final Character c, final Character nextC) {

		if (c.equals(';')) {

			// Store it in the values map
			map.put(propertyName.trim(), valueName.trim());
			propertyName = "";
			valueName = "";

			mode = Mode.INSIDE_PROPERTY_NAME;
			return;

		} else {

			valueName += c;
			return;

		}

	}

	private void parsePropertyName(final List<Rule> rules, final Character c, final Character nextC) {

		if (c.equals(':')) {

			mode = Mode.INSIDE_VALUE;
			return;

		} else if (c.equals('}')) {

			Selector selector = new Selector(selectorName);
			selectorName = "";

			Rule rule = new Rule(selector);
			rules.add(rule);

			for (Entry<String, String> entry : map.entrySet()) {

				String property = entry.getKey();
				String value = entry.getValue();

				PropertyValue propertyValue = new PropertyValue(property, value);
				rule.addPropertyValue(propertyValue);

			}

			map.clear();

			mode = Mode.VOID;

		} else {

			propertyName += c;
			return;

		}

	}

	private void parseComment(final Character c, final Character nextC) throws Exception {

		if (previousChar == '*' && c == '/') {
			mode = Mode.VOID;
			return;
		}

	}

	private void parseVoid(final List<Rule> rules, final Character c, final Character nextC) throws Exception {

		if (isChar(c, '/') && isChar(nextC, '*')) {

			mode = Mode.INSIDE_COMMENT;
			return;

		} else if (isChar(c, ' ') || isChar(c, '\n') || isChar(c, '\t') || isChar(c, '\r')) {

			return;

		} else if (c.equals('{')) {

			mode = Mode.INSIDE_PROPERTY_NAME;
			return;

		} else {

			selectorName += c;
			return;

		}

	}

	private boolean isChar(final Character c1, final Character c2) {

		if (c1 == null && c2 == null) {
			return true;
		} else if (c1 == null && c2 != null) {
			return false;
		} else if (c1 == null && c2 != null) {
			return false;
		} else {
			return c1 == c2;
		}

	}
}
