package com.test.pocr.code;

import java.io.File;
import java.io.IOException;

import com.test.pocr.application.IGenerator;

public class ManagedBeanGenerator implements IGenerator {

	private final BeanModel model;
	private final static String INTRA_PROJECT_PATH = "src/main/java/";

	public ManagedBeanGenerator(final BeanModel model) {
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
