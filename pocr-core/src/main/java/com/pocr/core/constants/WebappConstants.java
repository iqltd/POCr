package com.pocr.core.constants;



public interface WebappConstants {

    interface Maven {
        String PACKAGING = "war";
    }

    interface Schema {
        String VERSION = "2.5";
        String JAVAEE_URI = " http://java.sun.com/xml/ns/javaee ";
        String WEBAPP_URI = " http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd ";
    }

    interface Path  extends MavenConstants.Path {
        String WEB_INF = SOURCES_BASEDIR + "webapp/WEB-INF/";
        String DD = WEB_INF + "web.xml";
        String CLASSES_PATH = WEB_INF + "/classes";
    }

}
