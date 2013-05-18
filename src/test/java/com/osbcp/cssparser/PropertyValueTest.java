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

public final class PropertyValueTest {

	@Test
	public void testGetters() {

		PropertyValue propertyValue = new PropertyValue("width", "100px");

		Assert.assertEquals(propertyValue.getProperty(), "width");
		Assert.assertEquals(propertyValue.getValue(), "100px");

	}

	@Test
	public void testHashCode() {

		PropertyValue propertyValue = new PropertyValue("width", "100px");

		Assert.assertEquals(propertyValue.toString().hashCode(), propertyValue.hashCode());

	}

	@Test
	public void testEquals() {

		PropertyValue selector1a = new PropertyValue("width", "100%");
		PropertyValue selector1b = new PropertyValue("width", "100%");
		PropertyValue selector2 = new PropertyValue("width", "200%");
		PropertyValue selectorNull = null;

		Assert.assertTrue(selector1a.equals(selector1b));
		Assert.assertFalse(selector1a.equals(selector2));
		Assert.assertFalse(selector1a.equals(selectorNull));

	}

}
