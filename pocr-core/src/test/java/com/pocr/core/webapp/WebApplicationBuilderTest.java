package com.pocr.core.webapp;

import org.junit.Assert;
import org.junit.Test;

public class WebApplicationBuilderTest {

	@Test
	public void instantiate_withValidName_success() {
		final WebApplicationBuilder builder = new WebApplicationBuilder("test");

        Assert.assertTrue(builder.getModel() != null);
	}

    @Test
    public void instantiate_withValidName_ddGeneratorAddedByDefault() {
        final WebApplicationBuilder builder = new WebApplicationBuilder("test");

        Assert.assertTrue(builder.getModel() != null);
        Assert.assertEquals(2, builder.getModel().getArtifacts().size());
        Assert.assertTrue(builder.getModel().getArtifacts().get(1) instanceof DeploymentDescriptorGenerator);
    }

}
