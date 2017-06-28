package org.iqltd.pocr.core.application;

import org.iqltd.pocr.core.artifact.Artifact;
import org.iqltd.pocr.core.exception.PocrException;
import org.iqltd.pocr.core.mvn.PomArtifact;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ApplicationBuilderTest {

    private static final String VALID_NAME = "testName";

	@Test
	public void build_nominal() {
		final ApplicationBuilder builder = new ApplicationBuilder(VALID_NAME);
		final Application app = builder.build();

        Assert.assertNotNull(app);
        Assert.assertEquals(VALID_NAME, app.name);
    }

    @Test
    public void build_nominal_pomArtifactAddedByDefault() {
        final ApplicationBuilder builder = new ApplicationBuilder(VALID_NAME);
        final Application app = builder.build();

        Assert.assertNotNull(app);
        Assert.assertNotNull(app.artifacts);
        Assert.assertEquals(1, app.artifacts.size());
        Assert.assertTrue(app.artifacts.get(0) instanceof PomArtifact);
    }

    @Test
    public void addArtifact_nominal() {
        final ApplicationBuilder builder = new ApplicationBuilder(VALID_NAME);
        builder.addArtifact(Mockito.mock(Artifact.class));
        final Application app = builder.build();

        Assert.assertNotNull(app);
        Assert.assertNotNull(app.artifacts);
        Assert.assertEquals(2, app.artifacts.size());
    }

    @Test(expected = PocrException.class)
    public void instantiate_emptyName_fail() {
        new ApplicationBuilder("");
    }

    @Test(expected = PocrException.class)
    public void instantiate_noName_fail() {
        new ApplicationBuilder(null);
    }

}
