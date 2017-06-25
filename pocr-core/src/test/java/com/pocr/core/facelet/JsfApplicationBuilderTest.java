package com.pocr.core.facelet;

import com.pocr.core.application.Application;
import com.pocr.core.application.ApplicationBuilder;
import com.pocr.core.artifact.Artifact;
import com.pocr.core.artifact.ArtifactWriter;
import com.pocr.core.code.SourceCodeArtifact;
import com.pocr.core.dto.FormDto;
import com.pocr.core.webapp.DeploymentDescriptor;
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
            if (artifact instanceof SourceCodeArtifact) mbFound = true;
            if (artifact instanceof PageArtifact) pageFound = true;
        }
        Assert.assertTrue("Missing page artifact", pageFound);
        Assert.assertTrue("Missing managed bean artifact", mbFound);
    }

}
