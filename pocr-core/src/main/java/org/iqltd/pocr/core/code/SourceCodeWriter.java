package org.iqltd.pocr.core.code;

import java.io.File;
import java.io.IOException;

import org.iqltd.pocr.core.artifact.ArtifactWriter;
import com.sun.codemodel.JCodeModel;

public class SourceCodeWriter implements ArtifactWriter {

	private final JCodeModel model;

	SourceCodeWriter(final JCodeModel model) {
		this.model = model;
	}

	public void writeOnDisk(final File target) throws IOException {
		model.build(target);
	}

}
