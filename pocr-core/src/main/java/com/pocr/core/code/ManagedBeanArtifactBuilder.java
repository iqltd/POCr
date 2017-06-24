package com.pocr.core.code;

import com.pocr.core.artifact.Artifact;
import com.pocr.core.artifact.ArtifactBuilder;
import com.sun.codemodel.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ManagedBeanArtifactBuilder implements ArtifactBuilder {

	private final String packageName;
	private final String className;
	private final Map<String, Class<?>> properties = new HashMap<>();

	public ManagedBeanArtifactBuilder(final String packageName, final String unqualifiedClassName) {
		this.packageName = packageName;
		this.className = unqualifiedClassName;
	}

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

    public Artifact getArtifact() {
		CodeModelFacade code = new CodeModelFacade(getFullyQualifiedName());
        code.addAnnotation(ManagedBean.class).param("name", className.toLowerCase());
        code.addAnnotation(SessionScoped.class);

        for (Map.Entry<String, Class<?>> prop: properties.entrySet()) {
            code.addField(prop.getKey(), prop.getValue());
            code.addGetter(prop.getKey(), prop.getValue());
            code.addSetter(prop.getKey(), prop.getValue());
        }
		return new SourceCodeArtifact(code.getCodeModel());
	}

	private String getFullyQualifiedName() {
		return packageName + "." + className;
	}

}
