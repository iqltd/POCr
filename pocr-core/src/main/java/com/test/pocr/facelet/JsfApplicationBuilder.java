package com.test.pocr.facelet;

import org.apache.maven.model.Dependency;

import com.test.pocr.application.IGenerator;
import com.test.pocr.code.ManagedBeanBuilder;
import com.test.pocr.dto.FieldDto;
import com.test.pocr.dto.FormDto;
import com.test.pocr.mvn.DependencyBuilder;
import com.test.pocr.webapp.WebApplicationBuilder;

public class JsfApplicationBuilder extends WebApplicationBuilder {

	public static final String CLASSES_PATH = "/WEB-INF/classes";
	public static final String FACES_SERVLET = "javax.faces.webapp.FacesServlet";
	private static final String PATTERN = "*.xhtml";

	public static final String GROUP_ID = "javax.faces";
	public static final String ARTIFACT_ID = "jsf-api";
	public static final String VERSION = "2.1";
	public static final String SCOPE = "compile";

	public String indexPage;

	public JsfApplicationBuilder(final String name) {
		super(name);
		getDdBuilder().addServlet(FACES_SERVLET, PATTERN);
		final Dependency dependency = DependencyBuilder.getScopedDependency(
				GROUP_ID, ARTIFACT_ID, VERSION, SCOPE);
		getPomBuilder().addDependency(dependency);
	}

	public void addForm(final FormDto form) {
		addManagedBean(form);
		addPage(form);
	}

	private void addPage(final FormDto form) {
		final PageBuilder facesPageBuilder = new PageBuilder(form.getFormName());

		for (final FieldDto field : form.getFields()) {
			final String beanName = form.getFormName().toLowerCase();
			facesPageBuilder.addComponent(beanName, field);
		}

		final IGenerator generator = facesPageBuilder.getGenerator();
		addArtifact(generator);

		if (indexPage == null) {
			indexPage = PATTERN.replace("*", form.getFormName());
		}

	}

	private void addManagedBean(final FormDto form) {
		final ManagedBeanBuilder builder = new ManagedBeanBuilder(
				getNamespace(), form.getFormName());
		for (final FieldDto field : form.getFields()) {
			builder.addProperty(field.getName(), field.getType());
		}

		addArtifact(builder.getGenerator());

	}

	@Override
	protected void addSpecificArtifacts() {
		super.addSpecificArtifacts();
		getDdBuilder().addWelcomePage(indexPage);
	}
}
