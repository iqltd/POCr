package org.iqltd.pocr.core.facelet;

import org.iqltd.pocr.core.application.Application;
import org.iqltd.pocr.core.application.ApplicationBuilder;
import org.iqltd.pocr.core.artifact.Artifact;
import org.iqltd.pocr.core.code.ManagedBeanArtifact;
import org.iqltd.pocr.core.dto.FormDto;
import org.iqltd.pocr.core.webapp.WebApplicationBuilder;
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

    @Test
    public void addForm_nominal_addsPageAndBackingBean() {
        final JsfApplicationBuilder builder = new JsfApplicationBuilder("test");
        Application application = builder.build();

        builder.addForm(new FormDto("testForm"));

        Assert.assertNotNull(application);
        Assert.assertNotNull(application.artifacts);
        boolean pageFound = false;
        boolean mbFound = false;

        for (Artifact artifact: application.artifacts) {
            if (artifact instanceof ManagedBeanArtifact) mbFound = true;
            if (artifact instanceof FaceletArtifact) pageFound = true;
        }
        Assert.assertTrue("Missing page artifact", pageFound);
        Assert.assertTrue("Missing managed bean artifact", mbFound);
    }

}
