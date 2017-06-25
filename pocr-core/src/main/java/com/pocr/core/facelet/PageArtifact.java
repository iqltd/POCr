package com.pocr.core.facelet;


import com.pocr.core.artifact.Artifact;
import com.pocr.core.artifact.ArtifactWriter;
import org.jdom2.Document;

import static com.pocr.core.constants.JsfConstants.Path.PAGES_BASEDIR;

public class PageArtifact implements Artifact {

    private final Document model;
    private final String name;

    public PageArtifact(final String nameWithExtension, final Document jdomDoc) {
        model = jdomDoc;
        name = nameWithExtension;
    }

    @Override
    public String getPath() {
        return PAGES_BASEDIR + name;
    }

    @Override
    public ArtifactWriter getArtifactWriter() {
        return new DocumentWriter(model);
    }
}
