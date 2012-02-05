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
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public final class RuleTest {

	@Test
	public void testBasic() {

		Selector selector = new Selector("div");

		Rule rule = new Rule(selector);

		Assert.assertEquals(selector, rule.getSelectors().get(0));

	}

	@Test
	public void testRemoveSelector() {

		Selector selector = new Selector("div");

		Rule rule = new Rule(selector);
		rule.removeSelector(selector);

		Assert.assertEquals(0, rule.getSelectors().size());

	}

	@Test
	public void testRemovePropertyValue() {

		Selector selector = new Selector("div");
		PropertyValue propertyValue = new PropertyValue("width", "100px");
		Rule rule = new Rule(selector);
		rule.addPropertyValue(propertyValue);

		rule.removePropertyValue(propertyValue);

		Assert.assertEquals(0, rule.getPropertyValues().size());

	}

	@Test
	public void testAddSelectors() {

		List<Selector> selectors = new ArrayList<Selector>();
		selectors.add(new Selector("div"));
		selectors.add(new Selector("table"));

		Rule rule = new Rule(selectors);
		rule.addSelectors(selectors);

		Assert.assertEquals(4, rule.getSelectors().size());

	}

}
