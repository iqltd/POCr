package com.pocr.core.application;

import com.pocr.core.exception.PocrException;
import com.pocr.core.mvn.PomGenerator;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationBuilderTest {

	@Test
	public void instantiate_withValidName_success() {
		final ApplicationBuilder builder = new ApplicationBuilder("test");

        Assert.assertTrue(builder.getModel() != null);
        Assert.assertTrue(builder.getPomBuilder() != null);
	}

    @Test
    public void instantiate_withValidName_pomGeneratorAddedByDefault() {
        final ApplicationBuilder builder = new ApplicationBuilder("test");

        Assert.assertTrue(builder.getModel() != null);
        Assert.assertEquals(1, builder.getModel().getArtifacts().size());
        Assert.assertTrue(builder.getModel().getArtifacts().get(0) instanceof PomGenerator);
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
