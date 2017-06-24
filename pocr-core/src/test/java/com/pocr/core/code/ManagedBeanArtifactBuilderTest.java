package com.pocr.core.code;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.codemodel.JClassAlreadyExistsException;

public class ManagedBeanArtifactBuilderTest {

	private ManagedBeanArtifactBuilder builder;

	@Before
	public void init() {
		builder = new ManagedBeanArtifactBuilder("test.package", "TestBean");
	}

	@Test
	public void addProperty_valid_success() throws JClassAlreadyExistsException {
		final String fieldName = "field1";
		builder.addProperty(fieldName, int.class);

		Assert.assertEquals(1, builder.getProperties().size());
		Assert.assertTrue(builder.getProperties().containsKey(fieldName));
	}

	@Test(expected = NullPointerException.class)
	public void addNullProperty() throws JClassAlreadyExistsException {
		builder.addProperty(null, String.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addEmptyNameProperty() throws JClassAlreadyExistsException {
		builder.addProperty("", String.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addIllegalProperty() throws JClassAlreadyExistsException {
		builder.addProperty("goto", String.class);
	}

}
