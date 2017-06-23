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
        String REQUIRED_ATTR = "required";
        String LABEL_ATTR = "label";
        String VALUE_ATTR = "value";
    }

}
