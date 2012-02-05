package com.osbcp.cssparser;

/**
 * Represents a CSS selector.
 * 
 * @author <a href="mailto:christoffer@christoffer.me">Christoffer Pettersson</a>
 */

public final class Selector {

	private String name;

	/**
	 * Creates a new selector.
	 * 
	 * @param name Selector name.
	 */

	public Selector(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(final Object object) {

		if (object instanceof Selector) {

			Selector target = (Selector) object;

			return target.name.equalsIgnoreCase(name);

		}

		return false;

	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

}
