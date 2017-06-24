package com.pocr.core.facelet;

import com.pocr.core.application.Application;
import com.pocr.core.application.ApplicationBuilder;
import com.pocr.core.webapp.WebApplicationBuilder;
import org.junit.Assert;
import org.junit.Test;

public class JsfApplicationBuilderTest {

    private static final String VALID_NAME = "testName";

    @Test
    public void build_nominal() {
        ApplicationBuilder builder = new WebApplicationBuilder(VALID_NAME);
        Application application = builder.build();

        Assert.assertNotNull(application);
        Assert.assertEquals(VALID_NAME, application.name);
    }

//    @Test
//    public void instantiate_withValidName_pomGeneratorAddedByDefault() {
//        final JsfApplicationBuilder builder = new JsfApplicationBuilder("test");
//
//        Assert.assertTrue(builder.getModel() != null);
//        Assert.assertEquals(2, builder.getModel().getArtifacts().size());
//        Assert.assertTrue(builder.getModel().getArtifacts().get(1) instanceof DeploymentDescriptorWriter);
//    }
//
//    @Test
//    public void addForm_withValidName_addsPageAndBackingBean() {
//        final JsfApplicationBuilder builder = new JsfApplicationBuilder("test");
//
//        builder.addForm(new FormDto("testForm"));
//
//        Assert.assertTrue(builder.getModel() != null);
//        Assert.assertEquals(4, builder.getModel().getArtifacts().size());
//        boolean pageFound = false;
//        boolean mbFound = false;
//
//        for (ArtifactWriter gen: builder.getModel().getArtifacts()) {
//            if (gen instanceof ManagedBeanWriter) mbFound = true;
//            if (gen instanceof PageWriter) pageFound = true;
//        }
//        Assert.assertTrue(pageFound && mbFound);
//    }

}
