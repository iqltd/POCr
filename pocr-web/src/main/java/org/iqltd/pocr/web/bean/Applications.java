package org.iqltd.pocr.web.bean;

import org.iqltd.pocr.core.Pocr;
import org.iqltd.pocr.core.dto.FormDto;
import org.iqltd.pocr.ejb.RepositoryBean;
import org.iqltd.pocr.entity.ApplicationEntity;
import org.iqltd.pocr.entity.FormEntity;
import org.iqltd.pocr.web.util.EntityToDto;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


@ManagedBean(name = "applications")
@SessionScoped
public class Applications implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(Applications.class);
	private static final long serialVersionUID = 1L;

	private final Map<String, ApplicationEntity> apps = new HashMap<>();
	private final Set<String> deployed = new HashSet<>();

	@EJB
	private RepositoryBean repository;

    private Pocr pocr = new Pocr();

	@PostConstruct
	public void init() {
        for (ApplicationEntity app: repository.getApplications()) {
            apps.put(app.getName(), app);
        }
	}

    public List<ApplicationEntity> getAppList() {
        return new ArrayList<>(apps.values());
    }

    public void addApp(ApplicationEntity entity) {
        apps.put(entity.getName(), entity);
    }

    public void deleteApp(String app) {
        LOGGER.info("delete(). Application to be deleted: " + app);

        repository.deleteApplication(apps.get(app));
        apps.remove(app);
    }

    public void deploy(ApplicationEntity entity) throws IOException, ClassNotFoundException {
        String appName = entity.getName();
        LOGGER.info("deploy(). Deploy requested for application " + appName);
        List<FormDto> forms = new ArrayList<>();
        for (FormEntity formEntity: entity.getForms()) {
            forms.add(EntityToDto.toFormDto(formEntity));
        }

        pocr.generateAndDeployApplication(appName, forms.get(0));
        deployed.add(appName);
    }

    public void run(ApplicationEntity entity) {
        LOGGER.info("run(). Run requested for application " + entity.getName());
        openApplication(entity.getName());
    }

    public boolean isDeployed(String app) {
        return deployed.contains(app);
    }

    private void openApplication(String rootContext) {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getRequestURL().toString();
        url=(url.substring(0, url.length() - req.getRequestURI().length() + 1));
        url=url+rootContext;

        RequestContext.getCurrentInstance().execute("window.open('"+url+"','_blank')");
    }

}
