package org.iqltd.pocr.core.mvn;

import org.apache.maven.model.Dependency;
import org.junit.Assert;
import org.junit.Test;

public class DependencyBuilderTest {

    private static final String GROUP_ID = "testGroupId";
    private static final String ARTIFACT_ID = "testArtifactId";
    private static final String VERSION = "testVersion";
    private static final String SCOPE = "testScope";

    @Test
    public void build_withoutConfiguration() {
        Dependency dependency = DependencyBuilder.getInstance()
                .setGroupId(GROUP_ID)
                .setArtifactId(ARTIFACT_ID)
                .setVersion(VERSION)
                .setScope(SCOPE)
                .build();

        Assert.assertEquals(GROUP_ID, dependency.getGroupId());
        Assert.assertEquals(ARTIFACT_ID, dependency.getArtifactId());
        Assert.assertEquals(VERSION, dependency.getVersion());
        Assert.assertEquals(SCOPE, dependency.getScope());
    }
}
