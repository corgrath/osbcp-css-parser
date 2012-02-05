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
 * An exception that is thrown when the CSS parser finds a character it shouldn't have.
 * 
 * @author <a href="mailto:christoffer@christoffer.me">Christoffer Pettersson</a>
 */

public class IncorrectFormatException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new IncorrectFormatExeption with an error message;
	 * 
	 * @param message Error message describing the problem.
	 */

	public IncorrectFormatException(final String message) {
		super(message);
	}

}
