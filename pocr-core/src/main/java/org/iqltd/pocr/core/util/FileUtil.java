package org.iqltd.pocr.core.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.iqltd.pocr.core.exception.PocrException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FileUtil {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(FileUtil.class);

	private FileUtil() {
		throw new AssertionError();
	}

	public static Map<String, String> getConfigurationFromFile(
			final String configurationFile) {
		final Properties conf = loadPropertiesFromFile(configurationFile);
		return convertToMap(conf);
	}

	public static Properties loadPropertiesFromFile(final String fileName) {
		final Properties prop = new Properties();
		InputStream input = null;

		input = FileUtil.class.getClassLoader().getResourceAsStream(fileName);

		if (input == null) {
			throw new PocrException("Sorry, unable to find " + fileName);
		}

		try {
			// load a properties file from class path, inside static method
			prop.load(input);

		} catch (final IOException ex) {
			throw new PocrException(
					"Error occured on reading file " + fileName, ex);
		} finally {
			try {
				input.close();
			} catch (final IOException e) {
				LOGGER.warn("Error occured on closing file " + fileName, e);
			}
		}
		return prop;
	}

	private static Map<String, String> convertToMap(final Properties properties) {
		final Map<String, String> map = new HashMap<String, String>();
		for (final String name : properties.stringPropertyNames()) {
			map.put(name, properties.getProperty(name));
		}
		return map;
	}

    public static void unTar(InputStream inputStream, File directory) throws IOException {
        TarArchiveInputStream in = new TarArchiveInputStream(inputStream);
        TarArchiveEntry entry = in.getNextTarEntry();
        while (entry != null) {
            if (entry.isDirectory()) {
                entry = in.getNextTarEntry();
                continue;
            }
            File file = new File(directory, entry.getName());
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            IOUtils.copy(in, out);
            out.close();
            entry = in.getNextTarEntry();
        }
        in.close();
    }
}
