package com.osbcp.cssparser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Represents a CSS selector.
 * 
 * @author <a href="mailto:christoffer@christoffer.me">Christoffer Pettersson</a>
 */

public final class Rule {

	private List<Selector> selectors;
	private List<PropertyValue> values;

	public Rule(final Selector selector) {
		this.selectors = new ArrayList<Selector>();
		this.selectors.add(selector);
		this.values = new ArrayList<PropertyValue>();
	}

	public Rule(final List<Selector> selectors) {
		this.selectors = selectors;
		this.values = new ArrayList<PropertyValue>();
	}

	public void addPropertyValue(final PropertyValue propertyValue) {
		values.add(propertyValue);
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
		for (PropertyValue propertyValue : values) {
			out.append("\t" + propertyValue + ";\n");
		}
		out.append("}\n");

		return out.toString();
	}

	public List<PropertyValue> getValues() {
		return values;
	}

	public List<Selector> getSelectors() {
		return selectors;
	}

	public void addSelector(final Set<Selector> selectors) {
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

	public String implode(final List<Selector> values) {

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

	public void removePropertyValue(final PropertyValue propertyValue) {
		values.remove(propertyValue);
	}

}
