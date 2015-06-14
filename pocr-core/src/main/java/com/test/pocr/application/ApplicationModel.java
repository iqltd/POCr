package com.test.pocr.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class ApplicationModel implements Serializable {

	private static final long serialVersionUID = 9173277311569679954L;

	public ApplicationModel(final String name) {
		this.name = name;
		artifacts = new ArrayList<IGenerator>();
	}

	private final String name;

	private final List<IGenerator> artifacts;

	public String getName() {
		return name;
	}

	public void addArtifact(final IGenerator artifactGenerator) {
		artifacts.add(artifactGenerator);
	}

	public boolean removeArtifact(final IGenerator artifactGenerator) {
		return artifacts.remove(artifactGenerator);
	}

	public List<IGenerator> getArtifacts() {
		final List<IGenerator> artifactsCopy = new ArrayList<IGenerator>();
		artifactsCopy.addAll(artifacts);
		return artifactsCopy;
	}
}
