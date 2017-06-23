package eu.micul01.pocr.web.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import eu.micul01.pocr.ejb.PocrBean;
import eu.micul01.pocr.entity.ApplicationEntity;
import eu.micul01.pocr.entity.FormEntity;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ManagedBean(name = "appBean")
@ViewScoped
public class ApplicationBean implements Serializable {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ApplicationBean.class);

	private static final long serialVersionUID = 1L;
	private ApplicationEntity model;
	private boolean deployed;

	private PocrBean pocrBean;

	@ManagedProperty(value = "#{appCollectionBean}")
	private AppCollectionBean appCollection;

	@PostConstruct
	public void init() {
		reset();
	}

	public void reset() {
		model = new ApplicationEntity();
		model.setForms(new ArrayList<>());
	}

	public String submit() {
		LOGGER.info("submit(). Application submitted: " + model.getName());

		appCollection.addApp(model);

		return "home?faces-redirect=true";
	}

	public void deploy() throws IOException, ClassNotFoundException {
		LOGGER.info("deploy(). Deploy requested for application "
				+ model.getName());

		pocrBean.deployApplication(model);

		setDeployed(true);

	}

	public void openApplication(String rootContext) {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String url = req.getRequestURL().toString();
		url=(url.substring(0, url.length() - req.getRequestURI().length() + 1));
		url=url+rootContext;

		RequestContext.getCurrentInstance().execute("window.open('"+url+"','_blank')");
	}

	public void run() {
		LOGGER.info("run(). Run requested for application " + model.getName());
		openApplication(model.getName());
	}

	public void addForm(final FormEntity form) {
		LOGGER.info("addForm(). Form added: " + form.getName());
		LOGGER.info("addForm(). Fields: " + form.getFields());

		model.getForms().add(form);
	}

	public boolean isDeployed() {
		return deployed;
	}

	public void setDeployed(final boolean deployed) {
		this.deployed = deployed;
	}

	public ApplicationEntity getModel() {
		return model;
	}

	public void setModel(final ApplicationEntity model) {
		this.model = model;
	}

	public String getName() {
		return model.getName();
	}

	public void setName(final String appName) {
		model.setName(appName);
	}

	public List<FormEntity> getForms() {
		return model.getForms();
	}

	public AppCollectionBean getAppCollection() {
		return appCollection;
	}

	public void setAppCollection(final AppCollectionBean appCollection) {
		this.appCollection = appCollection;
	}

	public String getDescription() {
		return model.getDescription();
	}

	public void setDescription(final String description) {
		model.setDescription(description);
	}

	public PocrBean getPocrBean() {
		return pocrBean;
	}

	public void setPocrBean(final PocrBean pocrBean) {
		this.pocrBean = pocrBean;
	}

}
