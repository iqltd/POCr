package org.iqltd.pocr.core.webapp;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.iqltd.pocr.core.constants.WebappConstants;
import org.iqltd.pocr.core.exception.PocrException;
import org.jcp.xmlns.xml.ns.javaee.WebAppType;

import org.iqltd.pocr.core.artifact.ArtifactWriter;

public class DeploymentDescriptorWriter implements ArtifactWriter {

	private final WebAppType model;

	public DeploymentDescriptorWriter(final WebAppType model) {
		this.model = model;
	}

	public void writeOnDisk(final File target) throws IOException, PocrException {
		try {
			target.createNewFile();
			getMarshaller().marshal(model, target);
		} catch (final JAXBException e) {
			throw new PocrException(
					"Failure while marshalling the deployment descriptor.", e);
		}
	}

	private Marshaller getMarshaller() throws JAXBException {
		final JAXBContext jaxbContext = JAXBContext
				.newInstance(WebAppType.class);
		final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
				WebappConstants.Schema.JAVAEE_URI + WebappConstants.Schema.WEBAPP_URI);
		return jaxbMarshaller;
	}

}
