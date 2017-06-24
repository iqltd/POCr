package com.pocr.core.mvn;


import com.pocr.core.artifact.Artifact;
import com.pocr.core.artifact.ArtifactWriter;
import org.apache.maven.model.Model;

import static com.pocr.core.constants.MavenConstants.Pom;


public class PomArtifact implements Artifact {

    private final Model model;

    public PomArtifact(Model model) {
        this.model = model;
    }

    @Override
    public String getPath() {
        return Pom.FILE_NAME;
    }

    @Override
    public ArtifactWriter getArtifactWriter() {
        return new PomWriter(model);
    }
}
