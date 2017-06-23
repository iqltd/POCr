package com.pocr.core.mvn;

import java.util.HashMap;
import java.util.Map;

import com.pocr.core.application.Generator;
import com.pocr.core.deployment.WildflyMavenPluginHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;

import static com.pocr.core.mvn.MavenConstants.*;

public class PomBuilder {

	private final Model model;

	public PomBuilder(final String artifactName) {
		model = new Model();
		model.setGroupId(GROUP_ID);
		model.setArtifactId(StringUtils.isEmpty(artifactName) ? DEFAULT_ARTIFACT_NAME
				: artifactName);
		model.setVersion(VERSION);

		final Build build = new Build();
		final Map<String, String> configurationMap = new HashMap<String, String>();
		configurationMap.put("source", "1.8");
		configurationMap.put("target", "1.8");
		build.addPlugin(PluginBuilder.getPluginWithConfiguration(
				"org.apache.maven.plugins", "maven-compiler-plugin", "3.3",
				configurationMap));
		build.setFinalName(artifactName);
		model.setBuild(build);


		addBuildPlugin(WildflyMavenPluginHelper.getWlsPlugin());
		getPomModel().setModelVersion(MODEL_VERSION);
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
