package com.pocr.core.mvn;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import com.pocr.core.artifact.ArtifactWriter;

import static com.pocr.core.constants.MavenConstants.Pom;

public class PomWriter implements ArtifactWriter {

	private final Model model;

	PomWriter(final Model model) {
		this.model = model;
	}

	public void writeOnDisk(final File target) throws IOException {
		final Writer w = new PrintWriter(target);
		new MavenXpp3Writer().write(w, model);
	}
}
