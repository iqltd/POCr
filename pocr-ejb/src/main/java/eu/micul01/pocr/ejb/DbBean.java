package eu.micul01.pocr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import eu.micul01.pocr.entity.ApplicationEntity;
import eu.micul01.pocr.entity.FieldEntity;
import eu.micul01.pocr.entity.FormEntity;
import eu.micul01.pocr.entity.TypeEntity;

@Stateless
public class DbBean {

	@PersistenceContext(unitName = "pocr-pu")
	EntityManager entityManager;


	public void persistApplication(final ApplicationEntity application) {
		entityManager.persist(application);
		if (application.getForms() == null) {
			return;
		}
		for (final FormEntity form : application.getForms()) {
			form.setApplication(application);
			entityManager.persist(form);
			if (form.getFields() == null) {
				continue;
			}
			for (final FieldEntity field : form.getFields()) {
				field.setForm(form);
				entityManager.persist(field);
			}
		}
	}

	public void deleteApplication(ApplicationEntity entity) {
		entityManager.remove(entityManager.merge(entity));
	}

	public List<ApplicationEntity> getApplications() {
		return entityManager.createQuery(
				"Select a from ApplicationEntity a", ApplicationEntity.class)
				.getResultList();
	}

	public List<TypeEntity> getFieldTypes() {
		return entityManager.createQuery(
				"Select t from TypeEntity t", TypeEntity.class).getResultList();
	}
}
