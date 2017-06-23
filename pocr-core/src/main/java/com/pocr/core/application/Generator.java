package com.pocr.core.application;

import java.io.File;
import java.io.IOException;

public interface Generator {

	/**
	 * @return the relative file path where this artifact will be written
	 */
	String getRelativePath();

	/**
	 * Performs the file generation on the disk.
	 *
	 * @param folder the directory where the artifact will be written on disk
	 */
	void writeInFolder(File folder) throws IOException;

}
