package com.pocr.core.facelet;

import com.pocr.core.code.BeanBuilder;
import com.pocr.core.code.ManagedBeanArtifact;
import com.pocr.core.dto.FieldDto;
import com.pocr.core.dto.FormDto;
import com.pocr.core.mvn.DependencyBuilder;
import com.pocr.core.webapp.WebApplicationBuilder;
import org.apache.maven.model.Dependency;

import static com.pocr.core.constants.JsfConstants.DD;
import static com.pocr.core.constants.JsfConstants.Maven;

public class JsfApplicationBuilder extends WebApplicationBuilder {

	private String indexPage;

	public JsfApplicationBuilder(final String name) {
		super(name);
		addFacesServlet();
		addFacesApiDependency();
	}

	private void addFacesApiDependency() {
		final Dependency dependency = DependencyBuilder.getInstance()
				.setGroupId(Maven.GROUP_ID)
				.setArtifactId(Maven.ARTIFACT_ID)
				.setVersion(Maven.VERSION)
				.setScope(Maven.SCOPE)
				.build();
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
		FaceletArtifact artifact = new FaceletArtifact(form.getFormName());

		final PageBuilder builder = artifact.getBuilder();
		for (final FieldDto field : form.getFields()) {
			final String beanName = form.getFormName().toLowerCase();
			builder.addComponent(beanName, field);
		}

		addArtifact(artifact);

		if (indexPage == null) {
			indexPage = DD.PATTERN.replace("*", form.getFormName());
			getDdBuilder().addWelcomePage(indexPage);
		}
	}

	private void addManagedBean(final FormDto form) {
		ManagedBeanArtifact artifact = new ManagedBeanArtifact(getNamespace(), form.getFormName());

		final BeanBuilder builder = artifact.getBuilder();
		for (final FieldDto field : form.getFields()) {
			builder.addProperty(field.getName(), field.getType());
		}

		addArtifact(artifact);
	}
}
