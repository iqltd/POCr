package com.test.pocr.webapp;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.jcp.xmlns.xml.ns.javaee.WebAppType;

import com.test.pocr.application.IGenerator;
import com.test.pocr.exception.PocrException;

public class DeploymentDescriptorGenerator implements IGenerator {

	private final static String INTRA_PROJECT_PATH = "src/main/webapp/WEB-INF/web.xml";

	private final WebAppType model;

	public DeploymentDescriptorGenerator(final WebAppType model) {
		this.model = model;
	}

	public String getRelativePath() {
		return INTRA_PROJECT_PATH;
	}

	/**
	 * @throws PocrException
	 *             on marshalling error
	 */
	public void writeInFolder(final File folder) throws IOException {
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

	private Marshaller getMarshaller() throws JAXBException, PropertyException {
		// create JAXB context and initializing Marshaller
		final JAXBContext jaxbContext = JAXBContext
				.newInstance(WebAppType.class);
		final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
				Boolean.TRUE);
		jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
				"http://java.sun.com/xml/ns/javaee "
						+ "http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd");
		return jaxbMarshaller;
	}

}
