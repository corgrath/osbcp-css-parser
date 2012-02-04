package com.osbcp.cssparser;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public final class CSSReaderTest {

	@Test
	public void testNull() throws Exception {
		new CSSParser().parse(null);
	}

	@Test
	public void testEmpty() throws Exception {
		new CSSParser().parse("   ");
		new CSSParser().parse(" \n  ");
		new CSSParser().parse(" \n \t ");
		new CSSParser().parse(" \n \t \r ");
	}

	@Test
	public void testComments() throws Exception {
		new CSSParser().parse("  /* this should be ok */  ");
		new CSSParser().parse(" /** JavaDoc comment **/  ");
		new CSSParser().parse(" /** comment with \n new line **/  ");
		new CSSParser().parse(" /* double **/ /** comment */ ");
	}

	@Test
	public void testBasicSingle() throws Exception {

		List<Rule> rules = new CSSParser().parse("div { width: 100px; }");

		Assert.assertEquals(1, rules.size());

		Rule rule = rules.get(0);

		Assert.assertEquals("div", rule.getSelectors().get(0).toString());
		Assert.assertEquals("width", rule.getValues().get(0).getProperty());
		Assert.assertEquals("100px", rule.getValues().get(0).getValue());

	}

	//	@Test
	//	public void testBasicMultiple() throws Exception {
	//
	//		List<Rule> rules = new CSSReader().parse("div { width: 100px; } /* a comment */ beta { height: 200px; }");
	//
	//		Assert.assertEquals(2, rules.size());
	//
	//		Rule rule = rules.get(0);
	//		Assert.assertEquals("div", rule.getSelectors().get(0).toString());
	//		Assert.assertEquals("width", rule.getValues().get(0).getProperty());
	//		Assert.assertEquals("100px", rule.getValues().get(0).getValue());
	//
	//		Rule rule2 = rules.get(1);
	//		Assert.assertEquals("beta", rule2.getSelectors().get(0).toString());
	//		Assert.assertEquals("height", rule2.getValues().get(0).getProperty());
	//		Assert.assertEquals("200px", rule2.getValues().get(0).getValue());
	//
	//	}

	@Test
	public void testBasicMultipleValues() throws Exception {

		List<Rule> rules = new CSSParser().parse("div { width: 100px; -mozilla-opacity: 345; } /* a comment */ beta { height: 200px; display: blocked; }");

		Assert.assertEquals(2, rules.size());

		Rule rule = rules.get(0);
		Assert.assertEquals("div", rule.getSelectors().get(0).toString());
		Assert.assertEquals("width", rule.getValues().get(0).getProperty());
		Assert.assertEquals("100px", rule.getValues().get(0).getValue());
		Assert.assertEquals("-mozilla-opacity", rule.getValues().get(1).getProperty());
		Assert.assertEquals("345", rule.getValues().get(1).getValue());

		rule = rules.get(1);
		Assert.assertEquals("beta", rule.getSelectors().get(0).toString());
		Assert.assertEquals("height", rule.getValues().get(0).getProperty());
		Assert.assertEquals("200px", rule.getValues().get(0).getValue());
		Assert.assertEquals("display", rule.getValues().get(1).getProperty());
		Assert.assertEquals("blocked", rule.getValues().get(1).getValue());

	}

}
