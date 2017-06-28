package org.iqltd.pocr.core.facelet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import org.iqltd.pocr.core.artifact.ArtifactWriter;

public class DocumentWriter implements ArtifactWriter {

	private final Document model;

	DocumentWriter(final Document jdomDoc) {
		model = jdomDoc;
	}

	public void writeOnDisk(final File target) throws IOException {
		final XMLOutputter xml = new XMLOutputter();
		xml.setFormat(Format.getPrettyFormat());
		final OutputStream out = new FileOutputStream(target);
		xml.output(model, out);
	}
}
