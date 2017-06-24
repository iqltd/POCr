package com.pocr.core.deployment;

import com.pocr.core.mvn.PluginBuilder;
import com.pocr.core.util.FileUtil;
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
