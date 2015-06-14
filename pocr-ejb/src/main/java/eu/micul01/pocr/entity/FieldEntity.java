package eu.micul01.pocr.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fields")
public class FieldEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name = "";

	private String description = "";

	private boolean required;

	@ManyToOne
	@JoinColumn(name = "form_id")
	private FormEntity form;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private TypeEntity type;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

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

	public boolean isRequired() {
		return required;
	}

	public void setRequired(final boolean required) {
		this.required = required;
	}

	public FormEntity getForm() {
		return form;
	}

	public void setForm(final FormEntity form) {
		this.form = form;
	}

	public TypeEntity getType() {
		return type;
	}

	public void setType(final TypeEntity type) {
		this.type = type;
	}

}
