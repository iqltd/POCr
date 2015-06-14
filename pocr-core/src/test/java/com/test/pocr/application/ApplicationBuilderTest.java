package com.test.pocr.application;

import org.apache.maven.model.Model;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationBuilderTest {

	public ApplicationBuilderTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testNominal() {
		final ApplicationBuilder builder = new ApplicationBuilder("test");

		final ApplicationModel model = builder.getApplicationModel();

		Assert.assertTrue(model != null);

		final Model pom = builder.getPomBuilder().getPomModel();

		Assert.assertTrue(pom != null);
	}
}
