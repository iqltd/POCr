package eu.micul01.pocr.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.micul01.pocr.entity.FieldEntity;
import eu.micul01.pocr.entity.FormEntity;

@ManagedBean(name = "formBean")
@ViewScoped
public class FormBean {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(FormBean.class);

	private boolean formDialogVisible;

	private FormEntity model;

	@ManagedProperty(value = "#{appBean}")
	private ApplicationBean applicationBean;

	@PostConstruct
	public void init() {
		reset();
	}

	public void reset() {
		model = new FormEntity();
		model.setFields(new ArrayList<FieldEntity>());
		LOGGER.warn("reset(). Form reset.");
	}

	public String getFormName() {
		return model.getName();
	}

	public void setFormName(final String formName) {
		LOGGER.info("Form name set to: " + formName);
		model.setName(formName);
	}

	public List<FieldEntity> getFields() {
		return model.getFields();
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

	public void addToApp() {
		LOGGER.info("addToApp(). Added form to application.");
		applicationBean.addForm(model);
		reset();
		hideFormDialog();
	}

	public ApplicationBean getApplicationBean() {
		return applicationBean;
	}

	public void setApplicationBean(final ApplicationBean applicationBean) {
		this.applicationBean = applicationBean;
	}

	public void showFormDialog() {
		LOGGER.info("showFormDialog()");
		formDialogVisible = true;
	}

	public void hideFormDialog() {
		LOGGER.info("hideFormDialog()");
		formDialogVisible = false;
	}

	public boolean isFormDialogVisible() {
		return formDialogVisible;
	}

	public void setFormDialogVisible(final boolean formDialogVisible) {
		this.formDialogVisible = formDialogVisible;
	}

	public String getDescription() {
		return model.getDescription();
	}

	public void setDescription(final String description) {
		model.setDescription(description);
	}

}
