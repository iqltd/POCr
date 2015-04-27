package eu.micul01.pocr.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import eu.micul01.pocr.web.util.MessageUtil;

@ManagedBean(name = "appBean")
@ViewScoped
public class ApplicationBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String appName;
	private List<FormBean> forms;

	@PostConstruct
	public void init() {
		forms = new ArrayList<FormBean>();
		final FormBean example1 = new FormBean();
		example1.setFormName("ExampleForm1");
		final FormBean example2 = new FormBean();
		example2.setFormName("ExampleForm2");
		forms.add(example1);
		forms.add(example2);
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(final String appName) {
		this.appName = appName;
	}

	public List<FormBean> getForms() {
		return forms;
	}

	public void setForms(final List<FormBean> forms) {
		this.forms = forms;
	}

	public void onReorder() {
		MessageUtil.addInfoMessage("List reordered!");
	}

}
