package org.iqltd.pocr.core.deployment;

import org.iqltd.pocr.core.mvn.PluginBuilder;
import org.iqltd.pocr.core.util.FileUtil;
import org.apache.maven.model.Plugin;

public final class WildflyMavenPluginHelper {

	private static final String GROUP_ID = "org.wildfly.plugins";
	private static final String ARTIFACT_ID = "wildfly-maven-plugin";
	private static final String VERSION = "1.2.0.Alpha5";

	private static final String CONFIGURATION_FILE = "wildfly-maven-plugin.properties";

	public static Plugin getWlsPlugin() {
		return PluginBuilder.getInstance()
				.setGroupId(GROUP_ID)
				.setArtifactId(ARTIFACT_ID)
				.setVersion(VERSION)
				.setConfiguration(FileUtil.getConfigurationFromFile(CONFIGURATION_FILE))
				.build();
	}

}
