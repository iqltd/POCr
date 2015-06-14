package com.test.pocr.mvn;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import com.test.pocr.application.IGenerator;

public class PomGenerator implements IGenerator {

	private static final String INTRA_PROJECT_PATH = "pom.xml";
	private final Model model;

	public PomGenerator(final Model model) {
		this.model = model;
	}

	public Model getModel() {
		return model;
	}

	public String getRelativePath() {
		return INTRA_PROJECT_PATH;
	}

	public void writeInFolder(final File folder) throws IOException {
		final File fullPath = new File(folder, getRelativePath());
		final Writer w = new PrintWriter(fullPath);
		new MavenXpp3Writer().write(w, model);
	}

}
