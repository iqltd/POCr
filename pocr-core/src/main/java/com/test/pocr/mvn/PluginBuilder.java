package com.test.pocr.mvn;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.maven.model.Plugin;
import org.codehaus.plexus.util.xml.Xpp3Dom;

public class PluginBuilder {

	private static final String CONFIGURATION = "configuration";

	private PluginBuilder() {

	}

	public static Plugin getPlugin(final String groupId,
			final String artifactId, final String version) {
		final Plugin plugin = new Plugin();
		plugin.setGroupId(groupId);
		plugin.setArtifactId(artifactId);
		plugin.setVersion(version);
		return plugin;
	}

	public static Plugin getPluginWithConfiguration(final String groupId,
			final String artifactId, final String version,
			final Map<String, String> configurationMap) {
		final Plugin plugin = getPlugin(groupId, artifactId, version);
		plugin.setConfiguration(getConfigurationDom(configurationMap));
		return plugin;
	}

	private static Xpp3Dom getConfigurationDom(
			final Map<String, String> configuration) {
		final Xpp3Dom wlsConf = new Xpp3Dom(CONFIGURATION);

		for (final Entry<String, String> entry : configuration.entrySet()) {
			wlsConf.addChild(PomUtil.createElement(entry.getKey(),
					entry.getValue()));
		}
		return wlsConf;
	}

}
