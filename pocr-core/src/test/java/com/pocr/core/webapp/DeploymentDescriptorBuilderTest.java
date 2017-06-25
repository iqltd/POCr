package com.pocr.core.webapp;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class DeploymentDescriptorBuilderTest {

	private static DeploymentDescriptorBuilder ddBuilder;
	private static String VALID_SERVLET_CLASS = "javax.faces.webapp.FacesServlet";
	private static String VALID_PATTERN = "*.xhtml";

	@BeforeClass
	public static void init() {
		ddBuilder = new DeploymentDescriptorBuilder();
	}

	@Test(expected = IllegalArgumentException.class)
	public void addServletEmptyPatterns() {
		ddBuilder.addServlet(VALID_SERVLET_CLASS, new ArrayList<String>());
	}

	@Test(expected = IllegalArgumentException.class)
	public void addServletInvalidPatterns() {
		final List<String> patterns = new ArrayList<String>();
		patterns.add("<value>");
		ddBuilder.addServlet(VALID_SERVLET_CLASS, patterns);
	}

	@Test
	public void addServletValidPatterns() {
		final List<String> patterns = new ArrayList<String>();
		patterns.add(VALID_PATTERN);
		ddBuilder.addServlet(VALID_SERVLET_CLASS, patterns);
	}
}
