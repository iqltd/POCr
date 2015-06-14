package eu.micul01.pocr.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import eu.micul01.pocr.ejb.DbBean;
import eu.micul01.pocr.entity.ApplicationEntity;

@ManagedBean(name = "appCollectionBean")
@SessionScoped
public class AppCollectionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private final List<ApplicationBean> apps = new ArrayList<ApplicationBean>();

	@EJB
	private DbBean dbBean;

	@PostConstruct
	public void init() {
		// TODO Decomenteaza dupa testare
		// final List<ApplicationEntity> appEntities = dbBean.getApplications();
		final List<ApplicationEntity> appEntities = new ArrayList<ApplicationEntity>();
		for (final ApplicationEntity appEntity : appEntities) {
			final ApplicationBean appBean = new ApplicationBean();
			appBean.setModel(appEntity);
		}
	}

	public List<ApplicationBean> getApps() {
		return apps;
	}

	public void addApp(final ApplicationEntity app) {
		final ApplicationBean appBean = new ApplicationBean();
		appBean.setModel(app);
		apps.add(appBean);
	}

}
