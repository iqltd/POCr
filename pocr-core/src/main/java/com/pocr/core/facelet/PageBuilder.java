package com.pocr.core.facelet;

import java.io.Serializable;

import com.pocr.core.artifact.ArtifactWriter;
import com.pocr.core.dto.FieldDto;
import com.pocr.core.util.JdomUtil;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import com.pocr.core.facelet.xhtml.NamespaceEnum;

public class PageBuilder implements Serializable {

	private static final long serialVersionUID = 1;

	private final String name;

	private final Element define = new Element("define",
			JdomUtil.getNamespace(NamespaceEnum.FACELETS));

	public PageBuilder(final String name) {
		this.name = name;
	}

	public void addComponent(final String beanName, final FieldDto field) {
		define.addContent(InputTextHelper.getInput(beanName, field));
	}

	public String getName() {
		return name;
	}

	public ArtifactWriter getGenerator() {
		final Element composition = new Element("composition",
				JdomUtil.getNamespace(NamespaceEnum.FACELETS));
		composition.setAttribute(new Attribute("template", "template.xhtml"));
		final Document jdomDoc = new Document(composition,
				JdomUtil.getDoctype());

		final Element param = new Element("param",
				JdomUtil.getNamespace(NamespaceEnum.FACELETS));
		param.setAttribute("name", "pageTitle");
		param.setAttribute("value", name);
		composition.addContent(param);

		define.setAttribute("name", "actualForm");
		composition.addContent(define);

		return new PageWriter(name, jdomDoc);
	}
}
