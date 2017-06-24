package com.pocr.core.mvn;


import org.apache.maven.model.Dependency;

public class DependencyBuilder {

    private String groupId;
    private String artifactId;
    private String version;
    private String scope;

    public static DependencyBuilder getInstance() {
        return new DependencyBuilder();
    }

    public DependencyBuilder setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public DependencyBuilder setArtifactId(String artifactId) {
        this.artifactId = artifactId;
        return this;
    }

    public DependencyBuilder setVersion(String version) {
        this.version = version;
        return this;
    }

    public DependencyBuilder setScope(String scope) {
        this.scope = scope;
        return this;
    }

    public Dependency build() {
        Dependency dependency = new Dependency();
        dependency.setGroupId(groupId);
        dependency.setArtifactId(artifactId);
        dependency.setVersion(version);
        dependency.setScope(scope);
        return dependency;
    }

}
