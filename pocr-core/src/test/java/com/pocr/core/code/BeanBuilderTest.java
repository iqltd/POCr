package com.pocr.core.code;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BeanBuilderTest {

	private BeanBuilder builder;

	@Before
	public void init() {
		builder = new BeanBuilder();
	}

	@Test
	public void addProperty_valid_success() {
		final String fieldName = "field1";
		builder.addProperty(fieldName, int.class);

		Assert.assertEquals(1, builder.getProperties().size());
		Assert.assertTrue(builder.getProperties().containsKey(fieldName));
	}

	@Test(expected = NullPointerException.class)
	public void addNullProperty() {
		builder.addProperty(null, String.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addEmptyNameProperty() {
		builder.addProperty("", String.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addIllegalProperty() {
		builder.addProperty("goto", String.class);
	}

}
