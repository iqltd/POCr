package com.pocr.core.facelet;

import com.pocr.core.application.Generator;
import com.pocr.core.constants.JsfConstants;
import com.pocr.core.dto.FormDto;
import org.apache.maven.model.Dependency;

import com.pocr.core.code.ManagedBeanBuilder;
import com.pocr.core.dto.FieldDto;
import com.pocr.core.util.DependencyUtil;
import com.pocr.core.webapp.WebApplicationBuilder;

import static com.pocr.core.constants.JsfConstants.*;

public class JsfApplicationBuilder extends WebApplicationBuilder {

	private String indexPage;

	public JsfApplicationBuilder(final String name) {
		super(name);
		addFacesServlet();
		addFacesApiDependency();
	}

	private void addFacesApiDependency() {
		final Dependency dependency = DependencyUtil.getScopedDependency(
				Maven.GROUP_ID, Maven.ARTIFACT_ID, Maven.VERSION, Maven.SCOPE);
		getPomBuilder().addDependency(dependency);
	}

	private void addFacesServlet() {
		getDdBuilder().addServlet(DD.FACES_SERVLET, DD.PATTERN);
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

		final Generator generator = facesPageBuilder.getGenerator();
		addArtifact(generator);

		if (indexPage == null) {
			indexPage = DD.PATTERN.replace("*", form.getFormName());
			getDdBuilder().addWelcomePage(indexPage);
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
}
