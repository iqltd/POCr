package com.test.pocr.facelet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.test.pocr.application.IGenerator;

public class PageGenerator implements IGenerator {

	private final Document model;
	private final String name;

	private static final String FILE_PATH = "src/main/webapp/";
	private static final String EXTENSION = ".xhtml";

	public PageGenerator(final String name, final Document jdomDoc) {
		model = jdomDoc;
		this.name = name;
	}

	public String getRelativePath() {
		return FILE_PATH + name + EXTENSION;
	}

	public void writeInFolder(final File folder) throws IOException {
		// Output as XML
		// create XMLOutputter
		final XMLOutputter xml = new XMLOutputter();
		// we want to format the xml. This is used only for demonstration.
		// pretty formatting adds extra spaces and is generally not required.
		xml.setFormat(Format.getPrettyFormat());
		final File page = new File(folder, getRelativePath());
		final OutputStream out = new FileOutputStream(page);
		xml.output(model, out);

	}

}
