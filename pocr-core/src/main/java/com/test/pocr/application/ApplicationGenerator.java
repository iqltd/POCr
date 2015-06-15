package com.test.pocr.application;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.plexus.util.FileUtils;

public class ApplicationGenerator {

	public static final String FOLDER_TREE = "/var/pocr/folderTree/";
	public static final String path = "/var/pocr/";

	private final ApplicationModel model;
	private final File outputFolder;

	public ApplicationGenerator(final ApplicationModel model) {
		this.model = model;
		outputFolder = new File(path + model.getName());
	}

	/**
	 * Generates the files of the application on the disk
	 *
	 * @throws IOException
	 */
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

	private void writeArtifacts(final List<IGenerator> artifacts)
			throws IOException {

		for (final IGenerator artifact : artifacts) {
			artifact.writeInFolder(outputFolder);
		}
	}

}
