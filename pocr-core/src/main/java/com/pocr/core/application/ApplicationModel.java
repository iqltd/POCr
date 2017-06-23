package com.pocr.core.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class ApplicationModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String name;

	private final List<Generator> artifacts;

	ApplicationModel(final String applicationName) {
		name = applicationName;
		artifacts = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	void addArtifact(final Generator artifactGenerator) {
		artifacts.add(artifactGenerator);
	}

	public List<Generator> getArtifacts() {
		final List<Generator> artifactsCopy = new ArrayList<Generator>();
		artifactsCopy.addAll(artifacts);
		return artifactsCopy;
	}
}
