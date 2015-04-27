package eu.micul01.pocr.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public final class MessageUtil {

	private MessageUtil() {

	}

	public static void addInfoMessage(final String message) {
		addMessage(message, FacesMessage.SEVERITY_INFO);
	}

	public static void addMessage(final String message, final Severity severity) {
		final FacesMessage facesMessage = new FacesMessage(severity, message,
				null);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}
