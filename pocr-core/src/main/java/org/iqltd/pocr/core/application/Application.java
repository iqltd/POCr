package org.iqltd.pocr.core.application;

import org.iqltd.pocr.core.artifact.Artifact;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public final class Application implements Serializable {

	private static final long serialVersionUID = 1L;

	public final String name;

	public final List<Artifact> artifacts;

	Application(String name, List<Artifact> artifacts) {
		this.name = name;
		this.artifacts = Collections.unmodifiableList(artifacts);
	}
}
