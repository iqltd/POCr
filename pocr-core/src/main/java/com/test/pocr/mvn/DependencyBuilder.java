package com.test.pocr.mvn;

import org.apache.maven.model.Dependency;

public class DependencyBuilder {

	private DependencyBuilder() {

	}

	public static Dependency getDependency(final String groupId,
			final String artifactId, final String version) {
		final Dependency dependency = new Dependency();
		dependency.setGroupId(groupId);
		dependency.setArtifactId(artifactId);
		dependency.setVersion(version);
		return dependency;
	}

	public static Dependency getScopedDependency(final String groupId,
			final String artifactId, final String version, final String scope) {
		final Dependency dependency = getDependency(groupId, artifactId,
				version);
		dependency.setScope(scope);
		return dependency;
	}

}
