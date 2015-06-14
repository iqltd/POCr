package com.test.pocr.application;

import com.test.pocr.mvn.PomBuilder;
import com.test.pocr.weblogic.WlsMavenPluginHelper;

public class ApplicationBuilder {

	private final String NAME_SPACE = "com.pocr.generated";
	private final ApplicationModel model;

	private static final String MODEL_VERSION = "4.0.0";

	private final PomBuilder pomBuilder;

	/**
	 * @param name
	 *            the name of the application to be built
	 */
	public ApplicationBuilder(final String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException(
					"Cannot build a nameless application");
		}
		model = new ApplicationModel(name);
		pomBuilder = new PomBuilder(name);

		pomBuilder.addBuildPlugin(WlsMavenPluginHelper.getWlsPlugin());
		pomBuilder.getPomModel().setModelVersion(MODEL_VERSION);
	}

	/**
	 * Adds an artifact generator to the application built
	 *
	 * @param artifactGenerator
	 */
	public void addArtifact(final IGenerator artifactGenerator) {
		model.addArtifact(artifactGenerator);
	}

	/**
	 * Provides access to the pom builder for the subclasses
	 */
	protected PomBuilder getPomBuilder() {
		return pomBuilder;
	}

	/**
	 * Provides a namespace to be used by subclasses for building components of
	 * the application
	 */
	protected String getNamespace() {
		return NAME_SPACE + model.getName().toLowerCase();
	}

	/**
	 * Creates an application generator object
	 *
	 */
	public ApplicationGenerator getGenerator() {
		addSpecificArtifacts();
		return new ApplicationGenerator(model);
	}

	/**
	 * Used to add specific artifacts to the model just before creating the
	 * generator. Don't forget to invoke the parent method first, like in the
	 * example below:
	 *
	 * <pre>
	 * super.addSpecificArtifact();
	 * addArtifact(specificArtifactName);
	 * </pre>
	 */
	protected void addSpecificArtifacts() {
		model.addArtifact(pomBuilder.getGenerator());
	}

	ApplicationModel getApplicationModel() {
		return model;
	}

}
