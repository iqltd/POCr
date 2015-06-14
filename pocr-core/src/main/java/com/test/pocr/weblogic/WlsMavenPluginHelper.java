package com.test.pocr.weblogic;

import org.apache.maven.model.Plugin;

import com.test.pocr.mvn.PluginBuilder;
import com.test.pocr.util.FileUtil;

public class WlsMavenPluginHelper {

	private WlsMavenPluginHelper() {
	}

	private static final String GROUP_ID = "com.oracle.weblogic";
	private static final String ARTIFACT_ID = "wls-maven-plugin";
	private static final String VERSION = "12.1.3.0";

	private static final String CONFIGURATION_FILE = "wls-maven-plugin.properties";

	public static Plugin getWlsPlugin() {
		return PluginBuilder.getPluginWithConfiguration(GROUP_ID, ARTIFACT_ID,
				VERSION, FileUtil.getConfigurationFromFile(CONFIGURATION_FILE));
	}

}
