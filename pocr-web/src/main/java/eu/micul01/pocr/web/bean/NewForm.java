package eu.micul01.pocr.web.bean;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import eu.micul01.pocr.ejb.DbBean;
import eu.micul01.pocr.entity.FieldEntity;
import eu.micul01.pocr.entity.FormEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "newForm")
@ViewScoped
public class NewForm implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(NewForm.class);
	private static final long serialVersionUID = 1L;

	private boolean formDialogVisible;
	private FormEntity form;

	@EJB
	private DbBean dbBean;

	@ManagedProperty("#{newApp}")
	private NewApplication newApp;

	@PostConstruct
	public void init() {
		reset();
	}

	public void reset() {
		form = new FormEntity();
		form.setFields(new ArrayList<FieldEntity>());
		LOGGER.warn("reset(). Form reset.");
	}

	public String getFormName() {
		return form.getName();
	}

	public void setFormName(final String formName) {
		LOGGER.info("Form name set to: " + formName);
		form.setName(formName);
	}

	public List<FieldEntity> getFields() {
		return form.getFields();
	}

	public void addField(final ActionEvent event) {
		final FieldEntity field = new FieldEntity();
		getFields().add(field);
		LOGGER.info("addField(). New field added.");
	}

	public void removeField(final FieldEntity field) {
		getFields().remove(field);
		LOGGER.info("removeField(). Field removed: " + field.getName());
	}

	public void submit() {
		newApp.addForm(form);
		hideFormDialog();
	}

	public void showFormDialog() {
		LOGGER.info("showFormDialog()");
		formDialogVisible = true;
	}

	public void hideFormDialog() {
		LOGGER.info("hideFormDialog()");
		formDialogVisible = false;
	}

	public FormEntity getForm() {
		return form;
	}

	public boolean isFormDialogVisible() {
		return formDialogVisible;
	}

	public void setFormDialogVisible(final boolean formDialogVisible) {
		this.formDialogVisible = formDialogVisible;
	}

	public String getDescription() {
		return form.getDescription();
	}

	public void setDescription(final String description) {
		form.setDescription(description);
	}

	public NewApplication getNewApp() {
		return newApp;
	}

	public void setNewApp(NewApplication newApp) {
		this.newApp = newApp;
	}
}
