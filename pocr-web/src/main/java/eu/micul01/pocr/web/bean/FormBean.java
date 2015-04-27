package eu.micul01.pocr.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "formBean")
@ViewScoped
public class FormBean {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(FormBean.class);

	private String formName;

	private List<FieldBean> fields;;

	@PostConstruct
	public void init() {
		fields = new ArrayList<FieldBean>();
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(final String formName) {
		this.formName = formName;
	}

	public List<FieldBean> getFields() {
		return fields;
	}

	public void setFields(final List<FieldBean> fields) {
		this.fields = fields;
	}

	public void addField(final ActionEvent event) {
		final FieldBean field = new FieldBean();
		fields.add(field);
		LOGGER.warn("Field added: " + field.toString());
	}

	public void removeField(final FieldBean field) {
		fields.remove(field);
		LOGGER.warn("Field removed: " + field);
	}

	public void reset() {
		formName = "";
		for (final FieldBean field : fields) {
			field.initialize();
		}
	}

}
