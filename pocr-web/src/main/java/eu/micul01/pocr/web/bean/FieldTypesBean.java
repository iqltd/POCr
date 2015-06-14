package eu.micul01.pocr.web.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import eu.micul01.pocr.ejb.DbBean;
import eu.micul01.pocr.entity.TypeEntity;

@ManagedBean(name = "fieldTypesBean")
@ApplicationScoped
public class FieldTypesBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private List<TypeEntity> types;

	@EJB
	private DbBean dbBean;

	// TODO take them from db
	@PostConstruct
	public void populateTypes() {
		types = dbBean.getFieldTypes();

		// types = new ArrayList<TypeEntity>();
		// final TypeEntity string = new TypeEntity();
		// string.setName("string");
		//
		// final TypeEntity integer = new TypeEntity();
		// integer.setName("integer");
		//
		// types.add(string);
		// types.add(integer);
	}

	public List<TypeEntity> getTypes() {
		return types;
	}

}
