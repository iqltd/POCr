package com.pocr.core.webapp;

import com.pocr.core.util.Util;

import java.util.*;

public class DeploymentDescriptorBuilder {

	private List<String> welcomePages = new ArrayList<>();
	private List<Servlet> servlets = new ArrayList<>();
	private Set<String> servletNames = new HashSet<>();

	public void addWelcomePage(final String page) {
		welcomePages.add(page);
	}

	public void addServlet(final String qualifiedClassName, final String pattern) {
		final List<String> patterns = new ArrayList<>();
		patterns.add(pattern);
		addServlet(qualifiedClassName, patterns);
	}

	public void addServlet(final String qualifiedClassName, final List<String> patterns) {
		Util.validateQualifiedClassName(qualifiedClassName);
		validatePatterns(patterns);

		final String servletName = getServletName(Util
				.extractClassFromQualifiedName(qualifiedClassName));

		servlets.add(new Servlet(servletName, qualifiedClassName, patterns));
		servletNames.add(servletName);
	}

	private String getServletName(final String className) {
		String servletName = className;
		if (servletNames.contains(className)) {
			int count = 1;
			while (servletNames.contains(className + count)) {
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
		for (final String pattern : patterns) {
			if (pattern.contains("<") || pattern.contains(">")
					|| pattern.contains("&gt") || pattern.contains("&lt")) {
				throw new IllegalArgumentException("Url Pattern " + pattern
						+ " invalid");
			}
		}
	}

	List<String> getWelcomePages() {
		return Collections.unmodifiableList(welcomePages);
	}

	List<Servlet> getServlets() {
		return Collections.unmodifiableList(servlets);
	}

	public static class Servlet {
		public Servlet(String servletName, String className, List<String> patterns) {
			this.servletName = servletName;
			this.className = className;
			this.patterns = patterns;
		}

		final String servletName;
		final String className;
		final List<String> patterns;
	}
}
