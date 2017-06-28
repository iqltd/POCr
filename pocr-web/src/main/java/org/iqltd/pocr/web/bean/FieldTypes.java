package org.iqltd.pocr.web.bean;


import org.iqltd.pocr.ejb.RepositoryBean;
import org.iqltd.pocr.entity.TypeEntity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "fieldTypesBean")
@ApplicationScoped
public class FieldTypes implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TypeEntity> types;

	@EJB
	private RepositoryBean repository;

	@PostConstruct
	public void populateTypes() {
		types = repository.getFieldTypes();
	}

	public List<TypeEntity> getTypes() {
		return types;
	}

}
