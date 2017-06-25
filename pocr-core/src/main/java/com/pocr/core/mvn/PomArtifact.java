package com.pocr.core.mvn;

import com.pocr.core.artifact.Artifact;
import com.pocr.core.constants.MavenConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;

import static com.pocr.core.constants.MavenConstants.Pom;

public class PomArtifact implements Artifact<PomWriter> {

    private final PomBuilder builder = new PomBuilder();

    private final String artifactName;

    public PomArtifact(final String artifactName) {
        this.artifactName = artifactName;
    }

    public PomBuilder getBuilder() {
        return builder;
    }

    @Override
    public String getPath() {
        return Pom.FILE_NAME;
    }

    @Override
    public PomWriter getArtifactWriter() {
        Model model = builder.getModel();
        initModel(model);
        model.getBuild().setFinalName(artifactName);
        model.getBuild().addPlugin(getCompilerPlugin());
        return new PomWriter(model);
    }

    private void initModel(Model model) {
        model.setGroupId(Pom.GROUP_ID);
        model.setArtifactId(StringUtils.isEmpty(artifactName) ? Pom.DEFAULT_ARTIFACT_NAME
                : artifactName);
        model.setVersion(Pom.VERSION);
        model.setModelVersion(Pom.MODEL_VERSION);
    }

    private Plugin getCompilerPlugin() {
        return PluginBuilder.getInstance()
                .setGroupId(MavenConstants.Compiler.GROUP_ID)
                .setArtifactId(MavenConstants.Compiler.ARTIFACT_ID)
                .setVersion(MavenConstants.Compiler.VERSION)
                .addConfigurationProperty(MavenConstants.Compiler.SOURCE, MavenConstants.Compiler.JAVA_SRC_VERSION)
                .addConfigurationProperty(MavenConstants.Compiler.TARGET, MavenConstants.Compiler.JAVA_TARGET_VERSION)
                .build();
    }
}
