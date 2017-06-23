package com.pocr.core.application;

import java.io.*;
import java.util.List;

import com.pocr.core.util.Configuration;
import com.pocr.core.util.FileUtil;
import org.codehaus.plexus.util.FileUtils;

public class ApplicationGenerator {

	private static final String SKELETON_TAR = "skeleton.tar";

	private final ApplicationModel model;
	private final File outputFolder;

	public ApplicationGenerator(final ApplicationModel model) {
		this.model = model;
		outputFolder = new File(Configuration.OUTPUT_DIRECTORY.value + model.getName());
	}

	public File generateApplication() throws IOException {
		copyFolderTree();
		writeArtifacts(model.getArtifacts());
		return outputFolder;
	}

	private void copyFolderTree() throws IOException {
		outputFolder.mkdir();
		FileUtils.cleanDirectory(outputFolder);

		InputStream is = getClass().getClassLoader().getResourceAsStream(SKELETON_TAR);
		FileUtil.unTar(is, outputFolder);
	}

	private void writeArtifacts(final List<Generator> artifacts)
			throws IOException {

		for (final Generator artifact : artifacts) {
			artifact.writeInFolder(outputFolder);
		}
	}

}
