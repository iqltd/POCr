package eu.micul01.pocr.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "applications")
public class ApplicationEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String name;

	private String description;

	@OneToMany(mappedBy = "application", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<FormEntity> forms;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public List<FormEntity> getForms() {
		return forms;
	}

	public void setForms(final List<FormEntity> forms) {
		this.forms = forms;
	}

}
