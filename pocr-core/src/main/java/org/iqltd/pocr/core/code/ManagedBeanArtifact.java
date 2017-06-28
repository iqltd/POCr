package org.iqltd.pocr.core.code;

import org.iqltd.pocr.core.artifact.Artifact;
import org.iqltd.pocr.core.constants.MavenConstants;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Map;

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
		return MavenConstants.Path.SOURCES_BASEDIR;
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
