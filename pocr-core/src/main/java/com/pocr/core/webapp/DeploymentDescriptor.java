package com.pocr.core.webapp;

import com.pocr.core.artifact.Artifact;
import com.pocr.core.artifact.ArtifactWriter;
import org.jcp.xmlns.xml.ns.javaee.*;

import javax.xml.bind.JAXBElement;
import java.lang.String;
import java.util.List;

import static com.pocr.core.constants.WebappConstants.Path;
import static com.pocr.core.constants.WebappConstants.Schema;

public class DeploymentDescriptor implements Artifact {

    private final DeploymentDescriptorBuilder builder = new DeploymentDescriptorBuilder();

    private final String id;

    public DeploymentDescriptor(final String id) {
        this.id = id;
    }

    public DeploymentDescriptorBuilder getBuilder() {
        return builder;
    }

    @Override
    public String getPath() {
        return Path.DD;
    }

    @Override
    public ArtifactWriter getArtifactWriter() {
        return new DeploymentDescriptorWriter(getWebAppType());
    }

    private WebAppType getWebAppType() {
        WebAppType model = new WebAppType();
        model.setId(id);
        model.setVersion(Schema.VERSION);

        List<JAXBElement<?>> modules = model.getModuleNameOrDescriptionAndDisplayName();
        ObjectFactory factory = new ObjectFactory();

        addWelcomeFiles(modules, factory);
        addServlets(modules, factory);

        return model;
    }

    private void addWelcomeFiles(List<JAXBElement<?>> modules, ObjectFactory factory) {
        WelcomeFileListType welcomeFiles = new WelcomeFileListType();
        for (String page: builder.getWelcomePages()) {
            welcomeFiles.getWelcomeFile().add(page);
        }
        modules.add(factory.createWebAppTypeWelcomeFileList(welcomeFiles));
    }

    private void addServlets(List<JAXBElement<?>> modules, ObjectFactory factory) {
        for (DeploymentDescriptorBuilder.Servlet data: builder.getServlets()) {
            ServletType servlet = factory.createServletType();
            servlet.setServletName(getServletNameType(data.servletName));
            servlet.setServletClass(getFullyQualifiedClassType(data.className));
            modules.add(factory.createWebAppTypeServlet(servlet));

            for (final String pattern : data.patterns) {
                ServletMappingType servletMapping = factory.createServletMappingType();
                servletMapping.setServletName(getServletNameType(data.servletName));
                servletMapping.getUrlPattern().add(getUrlPatternType(pattern));
                modules.add(factory.createWebAppTypeServletMapping(servletMapping));
            }
        }
    }

    private ServletNameType getServletNameType(String servletName) {
        final ServletNameType nameType = new ServletNameType();
        nameType.setValue(servletName);
        return nameType;
    }

    private FullyQualifiedClassType getFullyQualifiedClassType(String className) {
        final FullyQualifiedClassType classType = new FullyQualifiedClassType();
        classType.setValue(className);
        return classType;
    }

    private UrlPatternType getUrlPatternType(String pattern) {
        final UrlPatternType urlPattern = new UrlPatternType();
        urlPattern.setValue(pattern);
        return urlPattern;
    }
}
