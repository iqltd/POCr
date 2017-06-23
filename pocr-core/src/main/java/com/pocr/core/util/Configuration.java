package com.pocr.core.util;

import com.pocr.core.exception.PocrException;

import java.util.Map;
import java.util.Properties;

public enum Configuration {
	MAVEN_HOME,
	OUTPUT_DIRECTORY;

	static class Props {
		static final String CONF_FILENAME = "configuration.properties";
		static final Map<String, String> PROPERTIES = FileUtil.getConfigurationFromFile(CONF_FILENAME);
	}

	public final String value;

	Configuration() throws PocrException {
		value = Props.PROPERTIES.get(this.name());
		if (value == null) {
			String message = String.format("Please provide a value for the property %s inside %s",
					this.name(), Props.CONF_FILENAME);
			throw new PocrException(message);
		}
	}
}
