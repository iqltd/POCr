package eu.micul01.pocr.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.micul01.pocr.entity.FormEntity;

@FacesConverter("formConverter")
public class FormConverter implements Converter {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(FormConverter.class);

	private static final String SEPARATOR = ",";
	private static final String END = ".";

	@Override
	public Object getAsObject(final FacesContext context,
			final UIComponent component, final String formAsString) {
		LOGGER.info("getAsObject(). String to be converted: " + formAsString);
		final String[] formSplitted = formAsString.split(SEPARATOR);

		if (formSplitted.length != 4) {
			LOGGER.warn("<>4");
			final FacesMessage msg = new FacesMessage("Eroare de conversie.",
					"Format invalid string formular.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);
		}

		final FormEntity form = new FormEntity();
		form.setId(Integer.parseInt(formSplitted[0]));
		form.setName(formSplitted[1]);
		form.setDescription(formSplitted[2]);
		LOGGER.warn("return ok: " + form.hashCode());
		return form;
	}

	@Override
	public String getAsString(final FacesContext context,
			final UIComponent component, final Object formAsObject) {

		if (formAsObject == null || !(formAsObject instanceof FormEntity)) {
			final FacesMessage msg = new FacesMessage("Eroare de conversie.",
					"Obiectul primit nu este de tipul asteptat.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);
		}

		final FormEntity form = (FormEntity) formAsObject;
		final String formAsString = form.getId() + SEPARATOR + form.getName()
				+ SEPARATOR + form.getDescription() + SEPARATOR + END;

		LOGGER.info("getAsString(). String resulted: " + formAsString);
		return formAsString;
	}
}
