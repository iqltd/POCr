package com.pocr.core.application;

import java.io.*;
import java.util.List;

import com.pocr.core.artifact.Artifact;
import com.pocr.core.util.Configuration;
import com.pocr.core.util.FileUtil;
import org.codehaus.plexus.util.FileUtils;

public class ApplicationGenerator {

	private static final String SKELETON_TAR = "skeleton.tar";

	private final Application application;
	private final File outputFolder;

	public ApplicationGenerator(final Application application) {
		this.application = application;
		outputFolder = new File(Configuration.OUTPUT_DIRECTORY.value + application.name);
	}

	public File generateApplication() throws IOException {
		copyFolderTree();
		writeArtifacts(application.artifacts);
		return outputFolder;
	}

	private void copyFolderTree() throws IOException {
		outputFolder.mkdir();
		FileUtils.cleanDirectory(outputFolder);

		InputStream is = getClass().getClassLoader().getResourceAsStream(SKELETON_TAR);
		FileUtil.unTar(is, outputFolder);
	}

	private void writeArtifacts(final List<Artifact> artifacts)
			throws IOException {

		for (final Artifact artifact : artifacts) {
			artifact.getGenerator().writeInFolder(outputFolder);
		}
	}

}
