package com.pocr.core.application;

import org.apache.maven.model.Model;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationBuilderTest {


	@Test
	public void testNominal() {
		final ApplicationBuilder builder = new ApplicationBuilder("test");

		final ApplicationModel model = builder.getModel();

		Assert.assertTrue(model != null);

		final Model pom = builder.getPomBuilder().getPomModel();

		Assert.assertTrue(pom != null);
	}
}
