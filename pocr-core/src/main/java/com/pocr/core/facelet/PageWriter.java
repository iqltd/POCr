package com.pocr.core.facelet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.pocr.core.artifact.ArtifactWriter;

import static com.pocr.core.constants.JsfConstants.Path.*;

public class PageWriter implements ArtifactWriter {

	private final Document model;
	private final String name;

	public PageWriter(final String name, final Document jdomDoc) {
		model = jdomDoc;
		this.name = name;
	}

	public String getRelativePath() {
		return PAGES_BASEDIR + name + EXTENSION;
	}

	public void writeOnDisk(final File folder) throws IOException {
		final XMLOutputter xml = new XMLOutputter();
		xml.setFormat(Format.getPrettyFormat());
		final File page = new File(folder, getRelativePath());
		final OutputStream out = new FileOutputStream(page);
		xml.output(model, out);
	}

}
