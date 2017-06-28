package org.iqltd.pocr.core.facelet;


import org.iqltd.pocr.core.artifact.Artifact;
import org.iqltd.pocr.core.dto.FieldDto;
import org.iqltd.pocr.core.util.JdomUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.iqltd.pocr.core.constants.JsfConstants;
import org.jdom2.*;

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
        return JsfConstants.Path.PAGES_BASEDIR + name + JsfConstants.Path.EXTENSION;
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
        final Element composition = new Element(JsfConstants.Xhtml.COMPOSITION_ELEM, faceletsNs);
        composition.setAttribute(new Attribute(JsfConstants.Xhtml.TEMPLATE_ELEM, JsfConstants.Xhtml.TEMPLATE_LOCATION));
        return composition;
    }

    private Element createTitleParam(Namespace faceletsNs) {
        final Element param = new Element(JsfConstants.Xhtml.PARAM_ELEM, faceletsNs);
        param.setAttribute(JsfConstants.Xhtml.NAME_ATTR, JsfConstants.Xhtml.PAGE_TITLE_ATTR);
        param.setAttribute(JsfConstants.Xhtml.VALUE_ATTR, name);
        return param;
    }

    private Element createDefine(Namespace faceletsNs) {
        final Element define = new Element(JsfConstants.Xhtml.DEFINE_ELEM, faceletsNs);
        for (Pair<String, FieldDto> field: builder.getFields()) {
            define.addContent(InputTextHelper.getInput(field.getLeft(), field.getRight()));
        }
        define.setAttribute(JsfConstants.Xhtml.NAME_ATTR, JsfConstants.Xhtml.DEFINE_NAME);
        return define;
    }
}
