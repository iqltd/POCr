package eu.micul01.pocr.web.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "lang")
@SessionScoped
public class Language implements Serializable {

	private static final long serialVersionUID = 1L;

	private String localeCode;

	@PostConstruct
	public void init() {
		localeCode = "en";
	}

	private static Map<String, Object> countries;
	static {
		countries = new LinkedHashMap<>();
		countries.put("English", Locale.ENGLISH);
		countries.put("Română", new Locale("ro", "RO"));
	}

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(final String localeCode) {
		this.localeCode = localeCode;
	}

	public void countryLocaleCodeChanged(final ValueChangeEvent e) {

		final String newLocaleValue = e.getNewValue().toString();

		for (final Map.Entry<String, Object> entry : countries.entrySet()) {
			if (entry.getValue().toString().equals(newLocaleValue)) {
				FacesContext.getCurrentInstance().getViewRoot()
						.setLocale((Locale) entry.getValue());
			}
		}
	}
}
