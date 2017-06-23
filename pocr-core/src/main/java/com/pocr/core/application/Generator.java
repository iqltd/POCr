package com.pocr.core.application;

import java.io.File;
import java.io.IOException;

public interface Generator {

	/**
	 * Provides the relative file path where this artifact will be written
	 *
	 * @return
	 */
	String getRelativePath();

	/**
	 * Performs the file generation on the disk.
	 *
	 * @param folder
	 * @throws IOException
	 */
	void writeInFolder(File folder) throws IOException;

}
