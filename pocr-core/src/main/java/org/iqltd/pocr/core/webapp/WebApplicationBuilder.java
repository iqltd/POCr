package org.iqltd.pocr.core.webapp;

import org.iqltd.pocr.core.application.ApplicationBuilder;
import org.iqltd.pocr.core.deployment.WildflyMavenPluginHelper;
import org.iqltd.pocr.core.constants.WebappConstants;

public class WebApplicationBuilder extends ApplicationBuilder {

	private final DeploymentDescriptor dd;

	public WebApplicationBuilder(final String name) {
		super(name);
		dd = new DeploymentDescriptor(name);
		addArtifact(dd);

		getPomBuilder().setPackaging(WebappConstants.Maven.PACKAGING);
		getPomBuilder().addBuildPlugin(WildflyMavenPluginHelper.getWlsPlugin());
	}

	protected DeploymentDescriptorBuilder getDdBuilder() {
		return dd.getBuilder();
	}

}
