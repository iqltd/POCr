package eu.micul01.pocr.web.bean;

import eu.micul01.pocr.ejb.DbBean;
import eu.micul01.pocr.entity.ApplicationEntity;
import eu.micul01.pocr.entity.FormEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "newApp")
@ViewScoped
public class NewApplication implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(NewApplication.class);
	private static final long serialVersionUID = 1L;

	private ApplicationEntity model;

    @EJB
    private DbBean dbBean;

	@ManagedProperty("#{applications}")
    private Applications applications;

	@PostConstruct
	public void init() {
		reset();
    }

	public void reset() {
		model = new ApplicationEntity();
		model.setForms(new ArrayList<>());
	}

	public void addForm(final FormEntity form) {
		LOGGER.info("addForm(). Form added: " + form.getName());
		LOGGER.info("addForm(). Fields: " + form.getFields());

		model.getForms().add(form);
	}

	public String submit() {
        applications.addApp(model);
        dbBean.persistApplication(model);

        return "home.xhtml?faces-redirect=true";
    }

	public ApplicationEntity getModel() {
		return model;
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

	public String getDescription() {
		return model.getDescription();
	}

	public void setDescription(final String description) {
		model.setDescription(description);
	}

    public Applications getApplications() {
        return applications;
    }

    public void setApplications(Applications applications) {
        this.applications = applications;
    }
}
