package com.pocr.core.facelet;

import java.util.ArrayList;
import java.util.List;

import com.pocr.core.artifact.Artifact;
import com.pocr.core.artifact.ArtifactBuilder;
import com.pocr.core.dto.FieldDto;
import com.pocr.core.util.JdomUtil;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import com.pocr.core.facelet.xhtml.NamespaceEnum;
import org.jdom2.Namespace;

import static com.pocr.core.constants.JsfConstants.Xhtml.*;
import static com.pocr.core.constants.JsfConstants.Path.EXTENSION;


public class FaceletArtifactBuilder implements ArtifactBuilder {

	private final String name;
	private final List<Pair<String, FieldDto>> fields = new ArrayList<>();

	public FaceletArtifactBuilder(final String name) {
		this.name = name;
	}

	public void addComponent(final String beanName, final FieldDto field) {
		fields.add(new ImmutablePair<>(beanName, field));
	}

	public String getName() {
		return name;
	}

	public Artifact getArtifact() {
		Namespace faceletsNs = JdomUtil.getNamespace(NamespaceEnum.FACELETS);
		final Element composition = createComposition(faceletsNs);
		addTitle(faceletsNs, composition);
		addDefine(faceletsNs, composition);

		final Document jdomDoc = new Document(composition, JdomUtil.getDoctype());
		return new PageArtifact(name + EXTENSION, jdomDoc);
	}

	private Element createComposition(Namespace faceletsNs) {
		final Element composition = new Element(COMPOSITION_ELEM, faceletsNs);
		composition.setAttribute(new Attribute(TEMPLATE_ELEM, TEMPLATE_LOCATION));
		return composition;
	}

	private void addTitle(Namespace faceletsNs, Element composition) {
		final Element param = new Element(PARAM_ELEM, faceletsNs);
		param.setAttribute(NAME_ATTR, PAGE_TITLE_ATTR);
		param.setAttribute(VALUE_ATTR, name);
		composition.addContent(param);
	}

	private void addDefine(Namespace faceletsNs, Element composition) {
		final Element define = new Element(DEFINE_ELEM, faceletsNs);
		for (Pair<String, FieldDto> field: fields) {
			define.addContent(InputTextHelper.getInput(field.getLeft(), field.getRight()));
		}
		define.setAttribute(NAME_ATTR, DEFINE_NAME);
		composition.addContent(define);
	}
}
