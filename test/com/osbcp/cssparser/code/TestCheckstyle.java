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

package com.osbcp.cssparser.code;

import org.junit.Test;

import com.osbcp.junitcheckstyletestwrapper.JUnitCheckstyleTestWrapper;
import com.osbcp.junitpmdtestwrapper.JUnitPMDTestWrapper;

/**
 * Contains the Checkstyle tests
 * 
 * @author <a href="mailto:christoffer@christoffer.me">Christoffer Pettersson</a>
 */

public class TestCheckstyle {

	/**
	 * Tests the SRC-folder
	 * 
	 * @throws Exception If any errors occur
	 */

	@Test
	public void testSrc() throws Exception {
		JUnitCheckstyleTestWrapper.run(this, "./src/", "checkstyle.xml");
	}

	/**
	 * Tests the TEST-folder
	 * 
	 * @throws Exception If any errors occur
	 */

	@Test
	public void testTest() throws Exception {
		JUnitPMDTestWrapper.run(this, "./test/", "pmd.xml");
	}

}