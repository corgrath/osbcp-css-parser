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

import junit.framework.Assert;

import org.junit.Test;

@SuppressWarnings("deprecation")
public final class SelectorTest {

	@Test
	public void testHashCode() {

		Selector selector = new Selector("div");

		Assert.assertEquals(selector.toString().hashCode(), selector.hashCode());

	}

	@Test
	public void testEqualsNull() {

		Selector selector1 = new Selector("div1");
		Selector selector2 = null;

		Assert.assertFalse(selector1.equals(selector2));

	}

	@Test
	public void testEquals() {

		Selector selector1a = new Selector("div1");
		Selector selector1b = new Selector("div1");
		Selector selector2 = new Selector("div2");

		Assert.assertFalse(selector1a.equals(selector2));
		Assert.assertTrue(selector1a.equals(selector1b));

	}

}
