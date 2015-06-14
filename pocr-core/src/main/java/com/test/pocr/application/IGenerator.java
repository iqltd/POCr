package com.test.pocr.application;

import java.io.File;
import java.io.IOException;

public interface IGenerator {

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
