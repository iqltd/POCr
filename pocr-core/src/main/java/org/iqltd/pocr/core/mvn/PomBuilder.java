package org.iqltd.pocr.core.mvn;

import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;

public class PomBuilder {

	private final Model model = new Model();

	Model getModel() {
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
