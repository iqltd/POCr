package org.iqltd.pocr.core.webapp;

import org.iqltd.pocr.core.application.Application;
import org.iqltd.pocr.core.application.ApplicationBuilder;
import org.junit.Assert;
import org.junit.Test;

public class WebApplicationBuilderTest {

    private static final String VALID_NAME = "testName";

    @Test
	public void build_nominal() {
		ApplicationBuilder builder = new WebApplicationBuilder(VALID_NAME);
        Application application = builder.build();

        Assert.assertNotNull(application);
        Assert.assertEquals(VALID_NAME, application.name);
	}

    @Test
    public void build_nominal_ddGeneratorAddedByDefault() {
        ApplicationBuilder builder = new WebApplicationBuilder(VALID_NAME);
        Application application = builder.build();

        Assert.assertNotNull(application);
        Assert.assertNotNull(application.artifacts);
        Assert.assertEquals(2, application.artifacts.size());
        Assert.assertTrue(application.artifacts.get(1) instanceof DeploymentDescriptor);
    }

}
