package org.iqltd.pocr.core.application;

import org.iqltd.pocr.core.artifact.Artifact;
import org.iqltd.pocr.core.exception.PocrException;
import org.iqltd.pocr.core.mvn.PomArtifact;
import org.iqltd.pocr.core.mvn.PomBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.iqltd.pocr.core.constants.MavenConstants.Pom;


public class ApplicationBuilder {

	private final String applicationName;
    private final PomArtifact pom;
    private final List<Artifact> artifacts = new ArrayList<>();

	public ApplicationBuilder(final String applicationName) {
		if (applicationName == null || applicationName.isEmpty()) {
			throw new PocrException("Cannot build an application without a name");
		}
		this.applicationName = applicationName;
		this.pom = new PomArtifact(applicationName);
		this.artifacts.add(pom);
	}

	public void addArtifact(final Artifact artifact) {
		artifacts.add(artifact);
	}

	protected String getNamespace() {
		return Pom.GROUP_ID + applicationName.toLowerCase();
	}

	protected PomBuilder getPomBuilder() {
		return pom.getBuilder();
	}

	public final Application build() {
		return new Application(applicationName, artifacts);
	}
}
