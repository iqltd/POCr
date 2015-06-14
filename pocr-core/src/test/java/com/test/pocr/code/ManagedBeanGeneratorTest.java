package com.test.pocr.code;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.codemodel.JClassAlreadyExistsException;

public class ManagedBeanGeneratorTest {

	private static String PACKAGE_NAME = "TestBean";
	private static String CLASS_NAME = "TestBean";

	private ManagedBeanBuilder builder;

	@Before
	public void init() {
		builder = new ManagedBeanBuilder(PACKAGE_NAME, CLASS_NAME);
	}

	public void addOkProperty() throws JClassAlreadyExistsException {

		final String fieldName = "camp1";
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
