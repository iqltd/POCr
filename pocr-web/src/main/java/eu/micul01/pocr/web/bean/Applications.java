package eu.micul01.pocr.web.bean;

import eu.micul01.pocr.ejb.DbBean;
import eu.micul01.pocr.ejb.PocrBean;
import eu.micul01.pocr.entity.ApplicationEntity;
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
	private DbBean dbBean;

    @EJB
    private PocrBean pocrBean;

	@PostConstruct
	public void init() {
        for (ApplicationEntity app: dbBean.getApplications()) {
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

        dbBean.deleteApplication(apps.get(app));
        apps.remove(app);
    }

    public void deploy(ApplicationEntity entity) throws IOException, ClassNotFoundException {
        LOGGER.info("deploy(). Deploy requested for application " + entity.getName());

        pocrBean.deployApplication(entity);
        deployed.add(entity.getName());
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
