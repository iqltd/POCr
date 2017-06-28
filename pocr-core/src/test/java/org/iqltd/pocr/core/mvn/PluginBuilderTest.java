package org.iqltd.pocr.core.mvn;

import org.apache.maven.model.Plugin;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PluginBuilderTest {

    private static final String GROUP_ID = "testGroupId";
    private static final String ARTIFACT_ID = "testArtifactId";
    private static final String VERSION = "testVersion";
    private static final String CONF_KEY1 = "testConfKey1";
    private static final String CONF_VAL1 = "testConfVal1";

    @Test
    public void build_withoutConfiguration() {
        Plugin plugin = PluginBuilder.getInstance()
                .setGroupId(GROUP_ID)
                .setArtifactId(ARTIFACT_ID)
                .setVersion(VERSION)
                .build();

        Assert.assertEquals(GROUP_ID, plugin.getGroupId());
        Assert.assertEquals(ARTIFACT_ID, plugin.getArtifactId());
        Assert.assertEquals(VERSION, plugin.getVersion());
        Assert.assertNull(plugin.getConfiguration());
    }

    @Test
    public void build_withSetConfiguration() {
        Map<String, String> conf = new HashMap<>();
        conf.put(CONF_KEY1, CONF_VAL1);

        Plugin plugin = PluginBuilder.getInstance()
                .setGroupId(GROUP_ID)
                .setArtifactId(ARTIFACT_ID)
                .setVersion(VERSION)
                .setConfiguration(conf)
                .build();

        Assert.assertEquals(GROUP_ID, plugin.getGroupId());
        Assert.assertEquals(ARTIFACT_ID, plugin.getArtifactId());
        Assert.assertEquals(VERSION, plugin.getVersion());
        Assert.assertNotNull(plugin.getConfiguration());
    }

    @Test
    public void build_withAddConfiguration() {
        Plugin plugin = PluginBuilder.getInstance()
                .setGroupId(GROUP_ID)
                .setArtifactId(ARTIFACT_ID)
                .setVersion(VERSION)
                .addConfigurationProperty(CONF_KEY1, CONF_VAL1)
                .build();

        Assert.assertEquals(GROUP_ID, plugin.getGroupId());
        Assert.assertEquals(ARTIFACT_ID, plugin.getArtifactId());
        Assert.assertEquals(VERSION, plugin.getVersion());
        Assert.assertNotNull(plugin.getConfiguration());
    }

}
