package eu.micul01.pocr.web.bean;

import eu.micul01.pocr.ejb.DbBean;
import eu.micul01.pocr.ejb.PocrBean;
import eu.micul01.pocr.entity.ApplicationEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "appCollectionBean")
@SessionScoped
public class AppCollectionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private final List<ApplicationBean> apps = new ArrayList<ApplicationBean>();

	@EJB
	private DbBean dbBean;

	@EJB
	private PocrBean pocrBean;

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
		appBean.setPocrBean(pocrBean);
		appBean.setAppCollection(this);
		apps.add(appBean);
	}

}
