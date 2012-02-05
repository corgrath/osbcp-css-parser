package com.osbcp.cssparser;

import java.util.List;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public final class CSSReaderTest {

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
	public void testBasicMultipleValues() throws Exception {

		List<Rule> rules = CSSParser.parse("div { width: 100px; -mozilla-opacity: 345; } /* a comment */ beta { height: 200px; display: blocked; } table td { }");

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

		rule = rules.get(2);
		Assert.assertEquals("table td", rule.getSelectors().get(0).toString());

	}

	@Test
	public void testFileBasic() throws Exception {

		String contents = IOUtils.toString(this.getClass().getResourceAsStream("css.css"));

		List<Rule> rules = CSSParser.parse(contents);

		for (Rule rule : rules) {
			System.out.println(rule);
		}

	}
}
