package com.pocr.core.artifact;

public interface Artifact<T extends ArtifactWriter> {

    String getPath();

    T getArtifactWriter();

}
