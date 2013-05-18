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
