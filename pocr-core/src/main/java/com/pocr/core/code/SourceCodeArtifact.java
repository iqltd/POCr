package com.pocr.core.code;

import com.pocr.core.artifact.Artifact;
import com.pocr.core.artifact.ArtifactWriter;
import com.sun.codemodel.JCodeModel;

import static com.pocr.core.constants.MavenConstants.Path.*;

public class SourceCodeArtifact implements Artifact {

	private final JCodeModel model;

	public SourceCodeArtifact(JCodeModel model) {
		this.model = model;
	}

	@Override
	public String getPath() {
		return SOURCES_BASEDIR;
	}

	@Override
	public ArtifactWriter getArtifactWriter() {
		return new SourceCodeWriter(model);
	}
}
