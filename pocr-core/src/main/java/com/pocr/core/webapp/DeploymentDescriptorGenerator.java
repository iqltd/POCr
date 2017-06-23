package com.pocr.core.webapp;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.jcp.xmlns.xml.ns.javaee.WebAppType;

import com.pocr.core.application.Generator;
import com.pocr.core.exception.PocrException;

import static com.pocr.core.constants.WebappConstants.*;

public class DeploymentDescriptorGenerator implements Generator {

	private final WebAppType model;

	public DeploymentDescriptorGenerator(final WebAppType model) {
		this.model = model;
	}

	public String getRelativePath() {
		return Path.DD;
	}

	public void writeInFolder(final File folder) throws IOException, PocrException {
		final File fullpath = new File(folder, getRelativePath());
		try {
			fullpath.getParentFile().mkdirs();
			fullpath.createNewFile();
			getMarshaller().marshal(model, fullpath);
		} catch (final JAXBException e) {
			throw new PocrException(
					"Failure while marshalling the deployment descriptor.", e);
		}
	}

	private Marshaller getMarshaller() throws JAXBException {
		final JAXBContext jaxbContext = JAXBContext
				.newInstance(WebAppType.class);
		final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
				Boolean.TRUE);
		jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
				Schema.JAVAEE_URI + Schema.WEBAPP_URI);
		return jaxbMarshaller;
	}

}
