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

}
