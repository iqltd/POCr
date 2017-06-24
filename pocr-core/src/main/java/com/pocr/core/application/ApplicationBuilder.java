package com.pocr.core.application;

import com.pocr.core.artifact.Artifact;
import com.pocr.core.artifact.ArtifactBuilder;
import com.pocr.core.exception.PocrException;
import com.pocr.core.mvn.PomBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.pocr.core.constants.MavenConstants.Pom;


public class ApplicationBuilder {

	private final String applicationName;
    private final PomBuilder pomBuilder;
    private final List<Artifact> artifacts = new ArrayList<>();
    private final List<ArtifactBuilder> specificBuilders = new ArrayList<>();

	public ApplicationBuilder(final String applicationName) {
		if (applicationName == null || applicationName.isEmpty()) {
			throw new PocrException("Cannot build an application without a name");
		}
		this.applicationName = applicationName;
		this.pomBuilder = new PomBuilder(applicationName);
        addSpecificBuilder(pomBuilder);
	}

	public void addArtifact(final Artifact artifact) {
		artifacts.add(artifact);
	}

	protected String getNamespace() {
		return Pom.GROUP_ID + applicationName.toLowerCase();
	}

	protected PomBuilder getPomBuilder() {
		return pomBuilder;
	}

	/**
	 * Allows subclasses to request the addition of a specific artifact
	 * before the application is built (see build() method)
	 */
	protected final void addSpecificBuilder(ArtifactBuilder builder) {
		specificBuilders.add(builder);
	}

	public final Application build() {
		for (ArtifactBuilder builder: specificBuilders) {
			artifacts.add(builder.getArtifact());
		}
		return new Application(applicationName, artifacts);
	}

}
