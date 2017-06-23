package com.pocr.core.application;

import com.pocr.core.exception.PocrException;
import com.pocr.core.constants.MavenConstants;
import com.pocr.core.mvn.PomBuilder;

public class ApplicationBuilder {

	private final ApplicationModel model;
	private final PomBuilder pomBuilder;

	public ApplicationBuilder(final String applicationName) {
		if (applicationName == null || applicationName.isEmpty()) {
			throw new PocrException("Cannot build an application without a name");
		}
		model = new ApplicationModel(applicationName);

		pomBuilder = new PomBuilder(applicationName);
        model.addArtifact(pomBuilder.getGenerator());
	}

	public void addArtifact(final Generator artifactGenerator) {
		model.addArtifact(artifactGenerator);
	}

    public ApplicationGenerator getGenerator() {
        return new ApplicationGenerator(model);
    }

	protected String getNamespace() {
		return MavenConstants.Pom.GROUP_ID + model.getName().toLowerCase();
	}

	public ApplicationModel getModel() {
		return model;
	}

	protected PomBuilder getPomBuilder() {
		return pomBuilder;
	}
}
