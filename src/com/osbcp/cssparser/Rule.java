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
import java.util.Iterator;
import java.util.List;

/**
 * Represents a CSS rule.
 * 
 * @author <a href="mailto:christoffer@christoffer.me">Christoffer Pettersson</a>
 */

public final class Rule {

	private List<Selector> selectors;
	private List<PropertyValue> propertyValues;

	/**
	 * Creates a rule with a single selector.
	 * 
	 * @param selector A selector that the rule should initial with.
	 */

	public Rule(final Selector selector) {
		this();
		this.selectors.add(selector);
	}

	/**
	 * Creates an empty rule.
	 */

	public Rule() {
		this(new ArrayList<Selector>());
	}

	/**
	 * Creates a new rule based on a list of selectors.
	 * 
	 * @param selectors A list of selectors that the rule should initial with.
	 */

	public Rule(final List<Selector> selectors) {
		this.selectors = selectors;
		this.propertyValues = new ArrayList<PropertyValue>();
	}

	@Override
	public String toString() {

		StringBuilder out = new StringBuilder();

		//			for (String selectorString : selectors) {
		//
		//				out.append(selectorString + ",");
		//		out.append(implode(selectors) + " {\n");
		//
		//			}

		out.append(implode(selectors) + " {\n");
		for (PropertyValue propertyValue : propertyValues) {
			out.append("\t" + propertyValue + ";\n");
		}
		out.append("}\n");

		return out.toString();
	}

	/**
	 * Adds a property value to the rule.
	 * 
	 * @param propertyValue The property value that should be attached.
	 */

	public void addPropertyValue(final PropertyValue propertyValue) {
		propertyValues.add(propertyValue);
	}

	/**
	 * Returns a list of all property values attached to the rule.
	 * 
	 * @return A list of all property values attached to the rule.
	 */

	public List<PropertyValue> getPropertyValues() {
		return propertyValues;
	}

	/**
	 * Returns a list of all selectors attached to the rule.
	 * 
	 * @return A list of all selectors attached to the rule.
	 */

	public List<Selector> getSelectors() {
		return selectors;
	}

	/**
	 * Adds a list of selectors to the existing list of selectors.
	 * 
	 * @param selectors A list of selectors that should be appended.
	 */

	public void addSelectors(final List<Selector> selectors) {
		this.selectors.addAll(selectors);
	}

	//
	//	@Override
	//	public boolean equals(final Object object) {
	//
	//		if (object instanceof Rule) {
	//
	//			Rule target = (Rule) object;
	//
	//			return target.name.equalsIgnoreCase(name);
	//
	//		}
	//
	//		return false;
	//
	//	}
	//
	//	@Override
	//	public int hashCode() {
	//		return toString().hashCode();
	//	}

	/**
	 * Implodes the list of selectors into a pretty String.
	 * 
	 * @param values A list of selectors.
	 * @return A fancy String.
	 */

	private String implode(final List<Selector> values) {

		StringBuilder sb = new StringBuilder();

		Iterator<Selector> iterator = values.iterator();

		while (iterator.hasNext()) {

			Selector selector = iterator.next();

			sb.append(selector.toString());

			if (iterator.hasNext()) {
				sb.append(", ");
			}

		}

		return sb.toString();

	}

	/**
	 * Removes a property value from the rule.
	 * 
	 * @param propertyValue The property value that should be removed.
	 */

	public void removePropertyValue(final PropertyValue propertyValue) {
		propertyValues.remove(propertyValue);
	}

	/**
	 * Adds a selector to the rule.
	 * 
	 * @param selector The selector that should be attached to the rule.
	 */

	public void addSelector(final Selector selector) {
		selectors.add(selector);
	}

	/**
	 * Removes a selector from the rule.
	 * 
	 * @param selector The selector that should be removed from the rule.
	 */

	public void removeSelector(final Selector selector) {
		selectors.remove(selector);
	}

}
