package com.test.pocr.mvn;

import org.codehaus.plexus.util.xml.Xpp3Dom;

public final class PomUtil {

	private PomUtil() {
		throw new AssertionError();
	}

	public static Xpp3Dom createElement(final String name, final String value) {
		final Xpp3Dom element = new Xpp3Dom(name);
		element.setValue(value);
		return element;
	}

}
