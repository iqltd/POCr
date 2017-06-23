package com.pocr.core.code;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.codemodel.JClassAlreadyExistsException;

public class ManagedBeanGeneratorTest {

	private ManagedBeanBuilder builder;

	@Before
	public void init() {
		builder = new ManagedBeanBuilder("test.package", "TestBean");
	}

	@Test
	public void addProperty_valid_success() throws JClassAlreadyExistsException {

		final String fieldName = "field1";
		builder.addProperty(fieldName, int.class);

		final BeanModel model = builder.getModel();

		Assert.assertEquals(1, model.getListOfFields().size());
		Assert.assertEquals(fieldName, model.getListOfFields().get(0));

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
