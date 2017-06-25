package com.pocr.core.facelet;


import com.pocr.core.artifact.Artifact;
import com.pocr.core.dto.FieldDto;
import com.pocr.core.util.JdomUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.jdom2.*;

import static com.pocr.core.constants.JsfConstants.Path.EXTENSION;
import static com.pocr.core.constants.JsfConstants.Path.PAGES_BASEDIR;
import static com.pocr.core.constants.JsfConstants.Xhtml.*;

public class FaceletArtifact implements Artifact<DocumentWriter> {

    private final PageBuilder builder = new PageBuilder();

    private final String name;

    public FaceletArtifact(final String name) {
        this.name = name;
    }

    PageBuilder getBuilder() {
        return builder;
    }

    @Override
    public String getPath() {
        return PAGES_BASEDIR + name + EXTENSION;
    }

    @Override
    public DocumentWriter getArtifactWriter() {
        Document document = createDocument();
        return new DocumentWriter(document);
    }

    private Document createDocument() {
        Namespace ns = JdomUtil.getNamespace(NamespaceEnum.FACELETS);
        Element composition = createComposition(ns);
        composition.addContent(createTitleParam(ns));
        composition.addContent(createDefine(ns));

        return new Document(composition, JdomUtil.getDoctype());
    }

    private Element createComposition(Namespace faceletsNs) {
        final Element composition = new Element(COMPOSITION_ELEM, faceletsNs);
        composition.setAttribute(new Attribute(TEMPLATE_ELEM, TEMPLATE_LOCATION));
        return composition;
    }

    private Element createTitleParam(Namespace faceletsNs) {
        final Element param = new Element(PARAM_ELEM, faceletsNs);
        param.setAttribute(NAME_ATTR, PAGE_TITLE_ATTR);
        param.setAttribute(VALUE_ATTR, name);
        return param;
    }

    private Element createDefine(Namespace faceletsNs) {
        final Element define = new Element(DEFINE_ELEM, faceletsNs);
        for (Pair<String, FieldDto> field: builder.getFields()) {
            define.addContent(InputTextHelper.getInput(field.getLeft(), field.getRight()));
        }
        define.setAttribute(NAME_ATTR, DEFINE_NAME);
        return define;
    }
}
