package com.pocr.core.artifact;

import java.io.File;
import java.io.IOException;

public interface ArtifactWriter {

	/**
	 * Performs the file generation on the disk.
	 *
	 * @param target the file in which the artifact will be written on disk
	 */
	void writeOnDisk(File target) throws IOException;

}
