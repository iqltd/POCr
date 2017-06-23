package com.pocr.core.mvn;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import com.pocr.core.application.Generator;

import static com.pocr.core.constants.MavenConstants.Pom;

public class PomGenerator implements Generator {

	private final Model model;

	public PomGenerator(final Model model) {
		this.model = model;
	}

	public Model getModel() {
		return model;
	}

	public String getRelativePath() {
		return Pom.FILE_NAME;
	}

	public void writeInFolder(final File folder) throws IOException {
		final File fullPath = new File(folder, getRelativePath());
		final Writer w = new PrintWriter(fullPath);
		new MavenXpp3Writer().write(w, model);
	}

}
