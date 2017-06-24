package com.pocr.core.mvn;


import com.pocr.core.util.JdomUtil;
import org.apache.maven.model.Plugin;

import java.util.HashMap;
import java.util.Map;

public class PluginBuilder {

    private static final String CONFIGURATION = "configuration";

    private String groupId;
    private String artifactId;
    private String version;
    private Map<String, String> configuration;

    public static PluginBuilder getInstance() {
        return new PluginBuilder();
    }

    public PluginBuilder setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public PluginBuilder setArtifactId(String artifactId) {
        this.artifactId = artifactId;
        return this;
    }

    public PluginBuilder setVersion(String version) {
        this.version = version;
        return this;
    }

    public PluginBuilder setConfiguration(Map<String, String> configuration) {
        this.configuration = configuration;
        return this;
    }

    public PluginBuilder addConfigurationProperty(String key, String value) {
        if (configuration == null) {
            configuration = new HashMap<>();
        }
        configuration.put(key, value);
        return this;
    }

    public Plugin build() {
        Plugin plugin = new Plugin();
        plugin.setGroupId(groupId);
        plugin.setArtifactId(artifactId);
        plugin.setVersion(version);
        if (configuration != null) {
            plugin.setConfiguration(JdomUtil.createElement(CONFIGURATION, configuration));
        }
        return plugin;
    }

}
