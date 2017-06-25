package com.pocr.core.webapp;

import com.pocr.core.application.ApplicationBuilder;
import com.pocr.core.deployment.WildflyMavenPluginHelper;
import static com.pocr.core.constants.WebappConstants.Maven.*;

public class WebApplicationBuilder extends ApplicationBuilder {

	private final DeploymentDescriptor dd;

	public WebApplicationBuilder(final String name) {
		super(name);
		dd = new DeploymentDescriptor(name);
		addArtifact(dd);

		getPomBuilder().setPackaging(PACKAGING);
		getPomBuilder().addBuildPlugin(WildflyMavenPluginHelper.getWlsPlugin());
	}

	protected DeploymentDescriptorBuilder getDdBuilder() {
		return dd.getBuilder();
	}

}
