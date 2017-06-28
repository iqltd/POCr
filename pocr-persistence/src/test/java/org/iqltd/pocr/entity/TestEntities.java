package org.iqltd.pocr.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestEntities {

	private static EntityManager EM;
	private static EntityManagerFactory EMF;

	@BeforeClass
	public static void setUp() {
		EMF = Persistence.createEntityManagerFactory("pocr_test-pu");
		EM = EMF.createEntityManager();
	}

	@AfterClass
	public static void tearDown() {
		EM.close();
		EMF.close();
	}

	@Test
	public void test() {
		// Get a new transaction
		final EntityTransaction trx = EM.getTransaction();

		final TypeEntity type = new TypeEntity();
		type.setName("Type");
		type.setClassName("Type.class");

		final ApplicationEntity application = new ApplicationEntity();
		application.setName("app");
		application.setDescription("desc");

		final FormEntity form = new FormEntity();
		form.setName("form");
		form.setDescription("desc");
		form.setApplication(application);

		final FieldEntity field = new FieldEntity();
		field.setName("field");
		field.setDescription("desc");
		field.setRequired(true);
		field.setType(type);
		field.setForm(form);

		try {
			// Start the transaction
			trx.begin();
			// Persist the object in the DB
			EM.persist(type);
			EM.persist(application);
			EM.persist(form);
			EM.persist(field);
			// Commit and end the transaction
			trx.commit();
		} catch (final RuntimeException e) {
			if (trx != null && trx.isActive()) {
				trx.rollback();
			}
			throw e;
		}

	}
}
