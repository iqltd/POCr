package com.pocr.core.webapp;

import com.pocr.core.artifact.Artifact;
import com.pocr.core.artifact.ArtifactWriter;
import org.jcp.xmlns.xml.ns.javaee.WebAppType;

import static com.pocr.core.constants.WebappConstants.*;

public class DeploymentDescriptor implements Artifact {

    private final WebAppType model;

    public DeploymentDescriptor(WebAppType model) {
        this.model = model;
    }

    @Override
    public String getPath() {
        return Path.DD;
    }

    @Override
    public ArtifactWriter getGenerator() {
        return new DeploymentDescriptorWriter(model);
    }
}
