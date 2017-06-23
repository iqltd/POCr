package com.pocr.core.facelet;

import com.pocr.core.application.Generator;
import com.pocr.core.code.ManagedBeanGenerator;
import com.pocr.core.dto.FormDto;
import com.pocr.core.webapp.DeploymentDescriptorGenerator;
import com.pocr.core.webapp.WebApplicationBuilder;
import org.junit.Assert;
import org.junit.Test;

public class JsfApplicationBuilderTest {

	@Test
	public void instantiate_withValidName_success() {
		final JsfApplicationBuilder builder = new JsfApplicationBuilder("test");

        Assert.assertTrue(builder.getModel() != null);
	}

    @Test
    public void instantiate_withValidName_pomGeneratorAddedByDefault() {
        final JsfApplicationBuilder builder = new JsfApplicationBuilder("test");

        Assert.assertTrue(builder.getModel() != null);
        Assert.assertEquals(2, builder.getModel().getArtifacts().size());
        Assert.assertTrue(builder.getModel().getArtifacts().get(1) instanceof DeploymentDescriptorGenerator);
    }

    @Test
    public void addForm_withValidName_addsPageAndBackingBean() {
        final JsfApplicationBuilder builder = new JsfApplicationBuilder("test");

        builder.addForm(new FormDto("testForm"));

        Assert.assertTrue(builder.getModel() != null);
        Assert.assertEquals(4, builder.getModel().getArtifacts().size());
        boolean pageFound = false;
        boolean mbFound = false;

        for (Generator gen: builder.getModel().getArtifacts()) {
            if (gen instanceof ManagedBeanGenerator) mbFound = true;
            if (gen instanceof PageGenerator) pageFound = true;
        }
        Assert.assertTrue(pageFound && mbFound);
    }

}
