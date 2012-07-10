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
 * Different states to aid the CSS parser.
 * 
 * @author <a href="mailto:christoffer@christoffer.me">Christoffer Pettersson</a>
 */

enum State {

	/**
	 * Inside a selector
	 */
	INSIDE_SELECTOR,

	/**
	 * Inside a comment.
	 */
	INSIDE_COMMENT,

	/**
	 * Inside a property value.
	 */
	INSIDE_PROPERTY_NAME,

	/**
	 * Inside value.
	 */
	INSIDE_VALUE,

	/**
	 * Inside value and also inside (
	 */
	INSIDE_VALUE_ROUND_BRACKET;

}