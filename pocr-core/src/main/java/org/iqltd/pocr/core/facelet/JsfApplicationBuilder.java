package org.iqltd.pocr.core.facelet;

import org.iqltd.pocr.core.code.BeanBuilder;
import org.iqltd.pocr.core.code.ManagedBeanArtifact;
import org.iqltd.pocr.core.dto.FieldDto;
import org.iqltd.pocr.core.dto.FormDto;
import org.iqltd.pocr.core.mvn.DependencyBuilder;
import org.iqltd.pocr.core.webapp.WebApplicationBuilder;
import org.apache.maven.model.Dependency;
import org.iqltd.pocr.core.constants.JsfConstants;

public class JsfApplicationBuilder extends WebApplicationBuilder {

	private String indexPage;

	public JsfApplicationBuilder(final String name) {
		super(name);
		addFacesServlet();
		addFacesApiDependency();
	}

	private void addFacesApiDependency() {
		final Dependency dependency = DependencyBuilder.getInstance()
				.setGroupId(JsfConstants.Maven.GROUP_ID)
				.setArtifactId(JsfConstants.Maven.ARTIFACT_ID)
				.setVersion(JsfConstants.Maven.VERSION)
				.setScope(JsfConstants.Maven.SCOPE)
				.build();
		getPomBuilder().addDependency(dependency);
	}

	private void addFacesServlet() {
		getDdBuilder().addServlet(JsfConstants.DD.FACES_SERVLET, JsfConstants.DD.PATTERN);
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
			indexPage = JsfConstants.DD.PATTERN.replace("*", form.getFormName());
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
