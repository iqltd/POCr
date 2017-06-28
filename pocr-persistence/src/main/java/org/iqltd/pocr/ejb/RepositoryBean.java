package org.iqltd.pocr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.iqltd.pocr.entity.ApplicationEntity;
import org.iqltd.pocr.entity.TypeEntity;

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
