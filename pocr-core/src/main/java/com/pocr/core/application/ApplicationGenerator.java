package com.pocr.core.application;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.plexus.util.FileUtils;

public class ApplicationGenerator {

	public static final String FOLDER_TREE = "/tmp/folderTree/";
	public static final String path = "/tmp/pocr/";

	private final ApplicationModel model;
	private final File outputFolder;

	public ApplicationGenerator(final ApplicationModel model) {
		this.model = model;
		outputFolder = new File(path + model.getName());
	}

	public File generateApplication() throws IOException {
		copyFolderTree();
		writeArtifacts(model.getArtifacts());
		return outputFolder;
	}

	private void copyFolderTree() throws IOException {
		outputFolder.mkdir();
		FileUtils.cleanDirectory(outputFolder);

		final File folderTree = new File(FOLDER_TREE);
		FileUtils.copyDirectoryStructure(folderTree, outputFolder);
	}

	private void writeArtifacts(final List<Generator> artifacts)
			throws IOException {

		for (final Generator artifact : artifacts) {
			artifact.writeInFolder(outputFolder);
		}
	}

}
