package com.pocr.core.code;

import java.io.File;
import java.io.IOException;

import com.pocr.core.application.Generator;

public class ManagedBeanGenerator implements Generator {

	private final BeanModel model;
	private final static String INTRA_PROJECT_PATH = "src/main/java/";

	ManagedBeanGenerator(final BeanModel model) {
		this.model = model;
	}

	public BeanModel getModel() {
		return model;
	}

	public String getRelativePath() {
		return INTRA_PROJECT_PATH;
	}

	public void writeInFolder(final File folder) throws IOException {
		final File fullPath = new File(folder, getRelativePath());
		fullPath.mkdirs();
		model.getCodeModel().build(fullPath);

	}

}
