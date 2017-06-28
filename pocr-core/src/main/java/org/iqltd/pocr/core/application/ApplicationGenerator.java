package org.iqltd.pocr.core.application;

import java.io.*;
import java.util.List;

import org.iqltd.pocr.core.artifact.Artifact;
import org.iqltd.pocr.core.util.Configuration;
import org.iqltd.pocr.core.util.FileUtil;
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
			File target = new File(outputFolder, artifact.getPath());
			target.getParentFile().mkdirs();
			artifact.getArtifactWriter().writeOnDisk(target);
		}
	}

}
