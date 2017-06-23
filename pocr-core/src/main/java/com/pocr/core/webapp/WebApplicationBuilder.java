package com.pocr.core.webapp;

import com.pocr.core.application.ApplicationBuilder;

public class WebApplicationBuilder extends ApplicationBuilder {

	public final String DD_PATH = "/WEB-INF/web.xml";
	public final String PACKAGING = "war";

	private final DeploymentDescriptorBuilder ddBuilder;

	public WebApplicationBuilder(final String name) {
		super(name);
		ddBuilder = new DeploymentDescriptorBuilder(name);
		addArtifact(ddBuilder.getGenerator());
		getPomBuilder().setPackaging(PACKAGING);
	}

	protected DeploymentDescriptorBuilder getDdBuilder() {
		return ddBuilder;
	}


}
