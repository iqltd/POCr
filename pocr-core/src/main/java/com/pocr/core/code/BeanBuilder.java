package com.pocr.core.code;

import com.sun.codemodel.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BeanBuilder {

	private final Map<String, Class<?>> properties = new HashMap<>();

	public void addProperty(final String name, final Class<?> type) {
		validatePropertyName(name);
		properties.put(name, type);
	}

	private void validatePropertyName(final String name) {
		if (!JJavaName.isJavaIdentifier(name)) {
			throw new IllegalArgumentException("The property name is not a java identifier.");
		}
		if (properties.get(name) != null) {
			throw new IllegalArgumentException("The property is already defined.");
		}
	}

    Map<String, Class<?>> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

}
