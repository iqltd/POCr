package com.pocr.core.constants;



public interface JsfConstants {

    interface DD {
        String FACES_SERVLET = "javax.faces.webapp.FacesServlet";
        String PATTERN = "*.xhtml";
    }

    interface Maven {
        String GROUP_ID = "javax.faces";
        String ARTIFACT_ID = "jsf-api";
        String VERSION = "2.1";
        String SCOPE = "provided";
    }

    interface Path extends WebappConstants.Path {
        String PAGES_BASEDIR = SOURCES_BASEDIR + "webapp/";
        String EXTENSION = ".xhtml";
    }

    interface Xhtml {
        String OUTPUT_LABEL_ELEM = "outputLabel";
        String INPUT_TEXT_ELEM = "inputText";
        String COMPOSITION_ELEM = "composition";
        String DEFINE_ELEM = "define";
        String PARAM_ELEM = "param";
        String TEMPLATE_ELEM = "template";

        String REQUIRED_ATTR = "required";
        String LABEL_ATTR = "label";
        String NAME_ATTR = "name";
        String VALUE_ATTR = "value";
        String PAGE_TITLE_ATTR = "pageTitle";

        String TEMPLATE_LOCATION = "template.xhtml";
        String DEFINE_NAME = "actualForm";
    }

}
