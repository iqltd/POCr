import org.iqltd.pocr.entity.TypeEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class CreateSchema {

	public static void main(String ... s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("create_schema");
		EntityManager em = emf.createEntityManager();
		final EntityTransaction trx = em.getTransaction();

		try {
			trx.begin();
			int existingTypes = em.createQuery(
					"Select t from TypeEntity t")
					.getResultList().size();
			if (existingTypes == 0) {
				final TypeEntity text = new TypeEntity();
				text.setName("Text");
				text.setClassName("java.lang.String");

				final TypeEntity decimal = new TypeEntity();
				decimal.setName("Decimal");
				decimal.setClassName("java.lang.Integer");

				em.persist(text);
				em.persist(decimal);
			}

			trx.commit();
		} catch (final RuntimeException e) {
			if (trx != null && trx.isActive()) {
				trx.rollback();
			}
			throw e;
		} finally {
			em.close();
			emf.close();
		}
	}
}
