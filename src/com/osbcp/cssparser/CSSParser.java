package com.osbcp.cssparser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class CSSParser {

	public static List<Rule> parse(final String css) throws Exception {

		CSSParser parser = new CSSParser();

		List<Rule> rules = new ArrayList<Rule>();

		if (css == null || css.trim().isEmpty()) {
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

	private String selectorName;
	private String propertyName;
	private String valueName;
	private Map<String, String> map;
	private Mode mode;
	private Character previousChar;
	private Mode beforeCommentMode;

	CSSParser() {
		this.selectorName = "";
		this.propertyName = "";
		this.valueName = "";
		this.map = new LinkedHashMap<String, String>();
		this.mode = Mode.INSIDE_SELECTOR;
		this.previousChar = null;
		this.beforeCommentMode = null;
	}

	public void parse(final List<Rule> rules, final Character c, final Character nextC) throws Exception {

		if (c.equals('/') && nextC.equals('*')) {

			beforeCommentMode = mode;
			mode = Mode.INSIDE_COMMENT;
			//			return;

			//		} else if (isChar(c, ' ') || isChar(c, '\n') || isChar(c, '\t') || isChar(c, '\r')) {

			//			return;

		}

		switch (mode) {

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
			default: {
				throw new Exception("Unknown mode '" + mode + "'.");
			}

		}

		previousChar = c;

	}

	private void parseValue(final Character c) {

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

	private void parsePropertyName(final List<Rule> rules, final Character c) {

		if (c.equals(':')) {

			mode = Mode.INSIDE_VALUE;
			return;

		} else if (c.equals('}')) {

			Selector selector = new Selector(selectorName.trim());
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

			mode = Mode.INSIDE_SELECTOR;

		} else {

			propertyName += c;
			return;

		}

	}

	private void parseComment(final Character c) throws Exception {

		if (previousChar == '*' && c == '/') {

			mode = beforeCommentMode;
			return;

		}

	}

	private void parseSelector(final Character c) throws Exception {

		if (c.equals('{')) {

			mode = Mode.INSIDE_PROPERTY_NAME;
			return;

		} else {

			selectorName += c;
			return;

		}

	}

}
