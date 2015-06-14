package com.test.pocr.webapp;

import com.test.pocr.application.ApplicationBuilder;

public class WebApplicationBuilder extends ApplicationBuilder {

	public final String DD_PATH = "/WEB-INF/web.xml";
	public final String PACKAGING = "war";

	private final DeploymentDescriptorBuilder ddBuilder;

	public WebApplicationBuilder(final String name) {
		super(name);
		ddBuilder = new DeploymentDescriptorBuilder(name);
		getPomBuilder().setPackaging(PACKAGING);
	}

	protected DeploymentDescriptorBuilder getDdBuilder() {
		return ddBuilder;
	}

	@Override
	protected void addSpecificArtifacts() {
		super.addSpecificArtifacts();
		addArtifact(ddBuilder.getGenerator());
	}

}
