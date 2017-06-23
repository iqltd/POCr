package eu.micul01.pocr.web.bean;


import eu.micul01.pocr.ejb.DbBean;
import eu.micul01.pocr.entity.TypeEntity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "fieldTypesBean")
@ApplicationScoped
public class FieldTypesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TypeEntity> types;

	@EJB
	private DbBean dbBean;

	@PostConstruct
	public void populateTypes() {
		types = dbBean.getFieldTypes();
	}

	public List<TypeEntity> getTypes() {
		return types;
	}

}
