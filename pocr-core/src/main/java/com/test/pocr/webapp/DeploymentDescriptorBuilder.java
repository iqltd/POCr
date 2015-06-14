package com.test.pocr.webapp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBElement;

import org.jcp.xmlns.xml.ns.javaee.FullyQualifiedClassType;
import org.jcp.xmlns.xml.ns.javaee.ObjectFactory;
import org.jcp.xmlns.xml.ns.javaee.ServletMappingType;
import org.jcp.xmlns.xml.ns.javaee.ServletNameType;
import org.jcp.xmlns.xml.ns.javaee.ServletType;
import org.jcp.xmlns.xml.ns.javaee.UrlPatternType;
import org.jcp.xmlns.xml.ns.javaee.WebAppType;
import org.jcp.xmlns.xml.ns.javaee.WelcomeFileListType;

import com.test.pocr.application.IGenerator;
import com.test.pocr.util.Util;

public class DeploymentDescriptorBuilder {

	private static final String WEB_APP_VERSION = "2.5";

	private final WebAppType model;
	private final ObjectFactory factory;
	private final Set<String> servlets;

	public DeploymentDescriptorBuilder(final String id) {
		model = new WebAppType();
		model.setId(id);
		model.setVersion(WEB_APP_VERSION);
		factory = new ObjectFactory();
		servlets = new HashSet<String>();
	}

	public IGenerator getGenerator() {
		return new DeploymentDescriptorGenerator(model);
	}

	public void addWelcomePage(final String page) {
		final WelcomeFileListType welcomeFiles = new WelcomeFileListType();
		welcomeFiles.getWelcomeFile().add(page);
		getListModules().add(
				factory.createWebAppTypeWelcomeFileList(welcomeFiles));
	}

	public void addServlet(final String qualifiedClassName, final String pattern) {
		final List<String> patterns = new ArrayList<String>();
		patterns.add(pattern);
		addServlet(qualifiedClassName, patterns);
	}

	public void addServlet(final String qualifiedClassName,
			final List<String> patterns) {

		Util.validateQualifiedClassName(qualifiedClassName);
		validatePatterns(patterns);

		final String servletName = getServletName(Util
				.extractClassFromQualifiedName(qualifiedClassName));

		final ServletType servlet = getServlet(qualifiedClassName, servletName);
		getListModules().add(factory.createWebAppTypeServlet(servlet));

		for (final String pattern : patterns) {
			final ServletMappingType servletMapping = getServletMapping(
					pattern, servletName);
			getListModules().add(
					factory.createWebAppTypeServletMapping(servletMapping));
		}
		servlets.add(servletName);
	}

	private String getServletName(final String className) {
		String servletName = className;
		if (servlets.contains(className)) {
			int count = 1;
			while (servlets.contains(className + count)) {
				count++;
			}
			servletName += count;
		}
		return servletName;
	}

	private void validatePatterns(final List<String> patterns) {
		if (patterns.isEmpty()) {
			throw new IllegalArgumentException("Invalid list of patterns");
		}
		// TODO vezi specificatia Servlet 3.1 pentru validare
		for (final String pattern : patterns) {
			if (pattern.contains("<") || pattern.contains(">")
					|| pattern.contains("&gt") || pattern.contains("&lt")) {
				throw new IllegalArgumentException("Url Pattern " + pattern
						+ " invalid");
			}
		}
	}

	private ServletType getServlet(final String className,
			final String servletName) {
		final ServletType servlet = factory.createServletType();

		final ServletNameType nameType = new ServletNameType();
		nameType.setValue(servletName);
		servlet.setServletName(nameType);

		final FullyQualifiedClassType classType = new FullyQualifiedClassType();
		classType.setValue(className);
		servlet.setServletClass(classType);

		return servlet;
	}

	private ServletMappingType getServletMapping(final String pattern,
			final String servletName) {
		final ServletMappingType servletMapping = factory
				.createServletMappingType();

		final ServletNameType nameType = new ServletNameType();
		nameType.setValue(servletName);
		servletMapping.setServletName(nameType);

		final UrlPatternType urlPattern = new UrlPatternType();
		urlPattern.setValue(pattern);
		servletMapping.getUrlPattern().add(urlPattern);

		return servletMapping;
	}

	private List<JAXBElement<?>> getListModules() {
		return model.getModuleNameOrDescriptionAndDisplayName();
	}

}
