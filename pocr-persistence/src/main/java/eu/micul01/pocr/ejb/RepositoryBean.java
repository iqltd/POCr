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
public class RepositoryBean {

	@PersistenceContext(unitName = "pocr-pu")
	EntityManager entityManager;


	public ApplicationEntity persistApplication(final ApplicationEntity application) {
		return entityManager.merge(application);
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
