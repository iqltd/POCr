package com.pocr.core.artifact;

import java.io.File;
import java.io.IOException;

public interface ArtifactWriter {

	/**
	 * Performs the file generation on the disk.
	 *
	 * @param folder the directory where the artifact will be written on disk
	 */
	void writeOnDisk(File folder) throws IOException;

}
