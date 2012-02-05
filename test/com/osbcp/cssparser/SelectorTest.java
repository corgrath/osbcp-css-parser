package com.osbcp.cssparser;

import junit.framework.Assert;

import org.junit.Test;

public final class SelectorTest {

	@Test
	public void testHashCode() {

		Selector selector = new Selector("div");

		Assert.assertEquals(selector.toString().hashCode(), selector.hashCode());

	}

	@Test
	public void testEqualsNull() {

		Selector selector1 = new Selector("div1");

		Assert.assertFalse(selector1.equals(null));

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
