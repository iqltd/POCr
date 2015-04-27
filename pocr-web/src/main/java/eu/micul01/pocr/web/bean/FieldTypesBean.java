package eu.micul01.pocr.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "fieldTypesBean")
@ApplicationScoped
public class FieldTypesBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private List<String> types;

	// TODO read them from DB
	@PostConstruct
	public void populateTypes() {
		types = new ArrayList<String>();
		types.add("String");
		types.add("Integer");
		types.add("List of values");
	}

	public List<String> getTypes() {
		return types;
	}

}
