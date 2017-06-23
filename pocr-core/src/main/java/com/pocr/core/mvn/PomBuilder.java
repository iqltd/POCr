package com.pocr.core.mvn;

import java.util.HashMap;
import java.util.Map;

import com.pocr.core.application.Generator;
import com.pocr.core.util.PluginUtil;
import com.sun.org.apache.xalan.internal.xsltc.cmdline.Compile;
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
	}

	private Build getBuild(String artifactName) {
		final Build build = new Build();
		final Map<String, String> configurationMap = new HashMap<String, String>();
		configurationMap.put(Compiler.SOURCE, Compiler.JAVA_SRC_VERSION);
		configurationMap.put(Compiler.TARGET, Compiler.JAVA_TARGET_VERSION);
		build.addPlugin(PluginUtil.getPluginWithConfiguration(
				Compiler.GROUP_ID, Compiler.ARTIFACT_ID, Compiler.VERSION,
				configurationMap));
		build.setFinalName(artifactName);
		return build;
	}

	private void initModel(String artifactName) {
		model.setGroupId(Pom.GROUP_ID);
		model.setArtifactId(StringUtils.isEmpty(artifactName) ? Pom.DEFAULT_ARTIFACT_NAME
				: artifactName);
		model.setVersion(Pom.VERSION);
		model.setModelVersion(Pom.MODEL_VERSION);
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
