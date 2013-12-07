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

/**
 * Represents a property and its value of a CSS rule.
 * 
 * @author <a href="mailto:christoffer@christoffer.me">Christoffer Pettersson</a>
 */

public final class PropertyValue {

	private String property;
	private String value;

	/**
	 * Creates a new PropertyValue based on a property and its value.
	 * 
	 * @param property The CSS property (such as 'width' or 'color').
	 * @param value The value of the property (such as '100px' or 'red').
	 */

	public PropertyValue(final String property, final String value) {
		this.property = property;
		this.value = value;
	}

	@Override
	public String toString() {
		return property + ": " + value;
	}

	@Override
	public boolean equals(final Object object) {

		if (object instanceof PropertyValue) {

			PropertyValue target = (PropertyValue) object;

			return target.property.equalsIgnoreCase(property) && target.value.equalsIgnoreCase(value);

		}

		return false;

	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	/**
	 * Returns the property.
	 * 
	 * @return The property.
	 */

	public String getProperty() {
		return property;
	}

	/**
	 * Returns the value.
	 * 
	 * @return The value.
	 */

	public String getValue() {
		return value;
	}

}
