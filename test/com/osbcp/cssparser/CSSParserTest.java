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

import java.util.List;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public final class CSSParserTest {

	@Test
	public void testNull() throws Exception {
		CSSParser.parse(null);
	}

	@Test
	public void testEmpty() throws Exception {
		CSSParser.parse("   ");
		CSSParser.parse(" \n  ");
		CSSParser.parse(" \n \t ");
		CSSParser.parse(" \n \t \r ");
	}

	@Test
	public void testComments() throws Exception {
		CSSParser.parse("  /* this should be ok */  ");
		CSSParser.parse(" /** JavaDoc comment **/  ");
		CSSParser.parse(" /** comment with \n new line **/  ");
		CSSParser.parse(" /* double **/ /** comment */ ");
	}

	@Test
	public void testBasicSingle() throws Exception {

		List<Rule> rules = CSSParser.parse("div { width: 100px; }");

		Assert.assertEquals(1, rules.size());

		Rule rule = rules.get(0);

		Assert.assertEquals("div", rule.getSelectors().get(0).toString());
		Assert.assertEquals("width", rule.getPropertyValues().get(0).getProperty());
		Assert.assertEquals("100px", rule.getPropertyValues().get(0).getValue());

	}

	@Test
	public void testCommentInsideRule() throws Exception {

		List<Rule> rules = CSSParser.parse("table /* and a comment here */ td { width: 100px; /* should be ignored */ text-decoration: underlined; }");

		Assert.assertEquals(1, rules.size());

		Rule rule = rules.get(0);
		Assert.assertEquals("table  td", rule.getSelectors().get(0).toString());
		Assert.assertEquals("width", rule.getPropertyValues().get(0).getProperty());
		Assert.assertEquals("100px", rule.getPropertyValues().get(0).getValue());
		Assert.assertEquals("text-decoration", rule.getPropertyValues().get(1).getProperty());
		Assert.assertEquals("underlined", rule.getPropertyValues().get(1).getValue());

	}

	@Test
	public void testBase64() throws Exception {

		List<Rule> rules = CSSParser.parse("image { background-image:url(data:image/gif;base64,ABC123/ABC123=ABC123);}");

		Assert.assertEquals(1, rules.size());

		Rule rule = rules.get(0);
		Assert.assertEquals("image", rule.getSelectors().get(0).toString());

		Assert.assertEquals("background-image", rule.getPropertyValues().get(0).getProperty());
		Assert.assertEquals("url(data:image/gif;base64,ABC123/ABC123=ABC123)", rule.getPropertyValues().get(0).getValue());

	}

	@Test
	public void testEmptPropertyValues() throws Exception {

		List<Rule> rules = CSSParser.parse("its-all-empty { /*empty*/ } empty { }");

		Assert.assertEquals(0, rules.size());

	}

	@Test
	public void testBasicMultipleValues() throws Exception {

		List<Rule> rules = CSSParser.parse("div { width: 100px; -mozilla-opacity: 345; } /* a comment */ beta{height:200px;display:blocked;}table td{}");

		Assert.assertEquals(2, rules.size());

		Rule rule = rules.get(0);
		Assert.assertEquals("div", rule.getSelectors().get(0).toString());
		Assert.assertEquals("width", rule.getPropertyValues().get(0).getProperty());
		Assert.assertEquals("100px", rule.getPropertyValues().get(0).getValue());
		Assert.assertEquals("-mozilla-opacity", rule.getPropertyValues().get(1).getProperty());
		Assert.assertEquals("345", rule.getPropertyValues().get(1).getValue());

		rule = rules.get(1);
		Assert.assertEquals("beta", rule.getSelectors().get(0).toString());
		Assert.assertEquals("height", rule.getPropertyValues().get(0).getProperty());
		Assert.assertEquals("200px", rule.getPropertyValues().get(0).getValue());
		Assert.assertEquals("display", rule.getPropertyValues().get(1).getProperty());
		Assert.assertEquals("blocked", rule.getPropertyValues().get(1).getValue());

	}

	@Test
	public void testDuplicatePropertiesSameOrder() throws Exception {

		List<Rule> rules = CSSParser.parse("product-row { background: #ABC123; border: 1px black solid; border: none;background:   url(http://www.domain.com/image.jpg);}");

		Rule rule = rules.get(0);
		Assert.assertEquals("product-row", rule.getSelectors().get(0).toString());

		Assert.assertEquals("background", rule.getPropertyValues().get(0).getProperty());
		Assert.assertEquals("#ABC123", rule.getPropertyValues().get(0).getValue());

		Assert.assertEquals("border", rule.getPropertyValues().get(1).getProperty());
		Assert.assertEquals("1px black solid", rule.getPropertyValues().get(1).getValue());

		Assert.assertEquals("border", rule.getPropertyValues().get(2).getProperty());
		Assert.assertEquals("none", rule.getPropertyValues().get(2).getValue());

		Assert.assertEquals("background", rule.getPropertyValues().get(3).getProperty());
		Assert.assertEquals("url(http://www.domain.com/image.jpg)", rule.getPropertyValues().get(3).getValue());

	}

	@Test
	public void testMultipleSelectors() throws Exception {

		List<Rule> rules = CSSParser.parse("alpha, beta { width: 100px; text-decoration: underlined; } gamma delta { } epsilon, /* some comment */ zeta { height: 34px; } ");

		Assert.assertEquals(2, rules.size());

		/*
		 * Rule 1
		 */

		Rule rule = rules.get(0);
		Assert.assertEquals("alpha", rule.getSelectors().get(0).toString());
		Assert.assertEquals("beta", rule.getSelectors().get(1).toString());

		Assert.assertEquals("width", rule.getPropertyValues().get(0).getProperty());
		Assert.assertEquals("100px", rule.getPropertyValues().get(0).getValue());
		Assert.assertEquals("text-decoration", rule.getPropertyValues().get(1).getProperty());
		Assert.assertEquals("underlined", rule.getPropertyValues().get(1).getValue());

		/*
		 * Rule 2
		 */

		rule = rules.get(1);
		Assert.assertEquals("epsilon", rule.getSelectors().get(0).toString());
		Assert.assertEquals("zeta", rule.getSelectors().get(1).toString());

		Assert.assertEquals("height", rule.getPropertyValues().get(0).getProperty());
		Assert.assertEquals("34px", rule.getPropertyValues().get(0).getValue());

	}

	@Test
	public void testFileBasic() throws Exception {

		String contents = IOUtils.toString(this.getClass().getResourceAsStream("css.css"), "UTF-8");

		List<Rule> rules = CSSParser.parse(contents);

		for (Rule rule : rules) {
			System.out.println(rule);
		}

	}
}
