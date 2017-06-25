package com.pocr.core.code;

import com.pocr.core.artifact.Artifact;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Map;

import static com.pocr.core.constants.MavenConstants.Path.*;

public class ManagedBeanArtifact implements Artifact<SourceCodeWriter> {

	private final BeanBuilder builder = new BeanBuilder();
	private final String packageName;
	private final String className;

	public ManagedBeanArtifact(String packageName, String className) {
		this.packageName = packageName;
		this.className = className;
	}

	public BeanBuilder getBuilder() {
		return builder;
	}

	@Override
	public String getPath() {
		return SOURCES_BASEDIR;
	}

	@Override
	public SourceCodeWriter getArtifactWriter() {
		CodeModelFacade code = new CodeModelFacade(getFullyQualifiedName());
		code.addAnnotation(ManagedBean.class).param("name", className.toLowerCase());
		code.addAnnotation(SessionScoped.class);

		for (Map.Entry<String, Class<?>> prop: builder.getProperties().entrySet()) {
			code.addField(prop.getKey(), prop.getValue());
			code.addGetter(prop.getKey(), prop.getValue());
			code.addSetter(prop.getKey(), prop.getValue());
		}
		return new SourceCodeWriter(code.getCodeModel());
	}

	private String getFullyQualifiedName() {
		return packageName + "." + className;
	}
}
