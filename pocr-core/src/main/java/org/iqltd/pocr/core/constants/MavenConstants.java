package org.iqltd.pocr.core.constants;


public interface MavenConstants {

    interface Pom {
        String VERSION = "1.0-SNAPSHOT";
        String GROUP_ID = "com.test.pocr";
        String DEFAULT_ARTIFACT_NAME = "PocrGeneratedApp";
        String MODEL_VERSION = "4.0.0";
        String FILE_NAME = "pom.xml";
    }

    interface Compiler {
        String GROUP_ID = "org.apache.maven.plugins";
        String ARTIFACT_ID = "maven-compiler-plugin";
        String VERSION = "3.3";
        String SOURCE = "source";
        String TARGET = "target";
        String JAVA_SRC_VERSION = "1.8";
        String JAVA_TARGET_VERSION = "1.8";
    }

    interface Path {
        String SOURCES_BASEDIR = "src/main/";
        String RESOURCES_BASEDIR = "src/resources/";
    }

    interface Goal {
        String CLEAN = "clean";
        String PACKAGE = "package";
        String DEPLOY = "wildfly:deploy";
    }
}
