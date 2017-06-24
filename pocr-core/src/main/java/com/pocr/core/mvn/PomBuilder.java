package com.pocr.core.mvn;

import com.pocr.core.application.Generator;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;

import static com.pocr.core.constants.MavenConstants.Pom;
import static com.pocr.core.constants.MavenConstants.Compiler;

public class PomBuilder {

	private final Model model = new Model();

	public PomBuilder(final String artifactName) {
		initModel(artifactName);
		model.setBuild(getBuild(artifactName));
		model.getBuild().addPlugin(getCompilerPlugin());
	}

	private void initModel(String artifactName) {
		model.setGroupId(Pom.GROUP_ID);
		model.setArtifactId(StringUtils.isEmpty(artifactName) ? Pom.DEFAULT_ARTIFACT_NAME
				: artifactName);
		model.setVersion(Pom.VERSION);
		model.setModelVersion(Pom.MODEL_VERSION);
	}

	private Build getBuild(String artifactName) {
		final Build build = new Build();
		build.setFinalName(artifactName);
		return build;
	}


	private Plugin getCompilerPlugin() {
		return PluginBuilder.getInstance()
				.setGroupId(Compiler.GROUP_ID)
				.setArtifactId(Compiler.ARTIFACT_ID)
				.setVersion(Compiler.VERSION)
				.addConfigurationProperty(Compiler.SOURCE, Compiler.JAVA_SRC_VERSION)
				.addConfigurationProperty(Compiler.TARGET, Compiler.JAVA_TARGET_VERSION)
				.build();
	}

	public Generator getGenerator() {
		return new PomGenerator(model);
	}

	public Model getPomModel() {
		return model;
	}

	public void setPackaging(final String packaging) {
		model.setPackaging(packaging);
	}

	public void addDependency(final Dependency dependency) {
		model.addDependency(dependency);
	}

	public void addBuildPlugin(final Plugin plugin) {
		if (model.getBuild() == null) {
			model.setBuild(new Build());
		}
		model.getBuild().addPlugin(plugin);
	}

}
