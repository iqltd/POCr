package com.pocr.core.application;

import com.pocr.core.mvn.MavenConstants;
import com.pocr.core.mvn.PomBuilder;

public class ApplicationBuilder {

	private final ApplicationModel model;
	private final PomBuilder pomBuilder;

	public ApplicationBuilder(final String applicationName) {
		if (applicationName == null || applicationName.isEmpty()) {
			throw new IllegalArgumentException("Cannot build an application without a name");
		}
		model = new ApplicationModel(applicationName);
		pomBuilder = new PomBuilder(applicationName);
        model.addArtifact(pomBuilder.getGenerator());
	}

	public void addArtifact(final Generator artifactGenerator) {
		model.addArtifact(artifactGenerator);
	}

	protected String getNamespace() {
		return MavenConstants.GROUP_ID + model.getName().toLowerCase();
	}

	public ApplicationGenerator getGenerator() {
		return new ApplicationGenerator(model);
	}

	protected ApplicationModel getModel() {
		return model;
	}

	protected PomBuilder getPomBuilder() {
		return pomBuilder;
	}
}
