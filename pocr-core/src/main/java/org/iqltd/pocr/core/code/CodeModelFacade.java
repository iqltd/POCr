package org.iqltd.pocr.core.code;

import org.iqltd.pocr.core.exception.PocrException;
import com.sun.codemodel.*;
import org.apache.commons.lang3.text.WordUtils;

import java.lang.annotation.Annotation;

class CodeModelFacade {

    private final JCodeModel codeModel;
    private final JDefinedClass bean;

    CodeModelFacade(String fullyQualifiedName) {
        this.codeModel = new JCodeModel();
        this.bean = getJDefinedClass(codeModel, fullyQualifiedName);
    }

    private JDefinedClass getJDefinedClass(JCodeModel codeModel, String fullyQualifiedName) {
        try {
            return codeModel._class(fullyQualifiedName);
        } catch (final JClassAlreadyExistsException e) {
            throw new PocrException("An error occurred at managed bean creation", e);
        }
    }

    JAnnotationUse addAnnotation(final Class<? extends Annotation> annotation) {
        return bean.annotate(annotation);
    }

    void addField(String name, Class<?> type) {
        bean.field(JMod.PRIVATE, type, name);
    }

    void addGetter(String propertyName, Class<?> type) {
        final JMethod getter = bean.method(JMod.PUBLIC, type, "get" + WordUtils.capitalize(propertyName));
        getter.body()._return(JExpr.ref(propertyName));
    }

    void addSetter(String propertyName, Class<?> type) {
        final JMethod setter = bean.method(JMod.PUBLIC, void.class, "set" + WordUtils.capitalize(propertyName));
        setter.param(type, propertyName);
        setter.body().assign(JExpr._this().ref(propertyName), JExpr.ref(propertyName));
    }

    JCodeModel getCodeModel() {
        return codeModel;
    }
}
