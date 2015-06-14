package com.test.pocr.application;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.plexus.util.FileUtils;

public class ApplicationGenerator {

	public static final String PATH_PREFIX = "./target/";
	public static final String FOLDER_TREE = "./folderTree/";

	private final ApplicationModel model;
	private final File outputFolder;

	public ApplicationGenerator(final ApplicationModel model) {
		this.model = model;
		outputFolder = new File(PATH_PREFIX + model.getName());
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
		final File folderTree = new File(getClass().getClassLoader()
				.getResource(FOLDER_TREE).getFile());
		FileUtils.copyDirectoryStructure(folderTree, outputFolder);
	}

	private void writeArtifacts(final List<IGenerator> artifacts)
			throws IOException {

		for (final IGenerator artifact : artifacts) {
			artifact.writeInFolder(outputFolder);
		}
	}

}
