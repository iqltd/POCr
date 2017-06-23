package com.pocr.core.webapp;

import com.pocr.core.application.ApplicationBuilder;
import com.pocr.core.deployment.WildflyMavenPluginHelper;
import static com.pocr.core.constants.WebappConstants.Maven.*;

public class WebApplicationBuilder extends ApplicationBuilder {

	private final DeploymentDescriptorBuilder ddBuilder;

	public WebApplicationBuilder(final String name) {
		super(name);
		ddBuilder = new DeploymentDescriptorBuilder(name);
		addArtifact(ddBuilder.getGenerator());
		getPomBuilder().setPackaging(PACKAGING);
		getPomBuilder().addBuildPlugin(WildflyMavenPluginHelper.getWlsPlugin());
	}

	protected DeploymentDescriptorBuilder getDdBuilder() {
		return ddBuilder;
	}


}
