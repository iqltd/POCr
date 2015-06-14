package com.test.pocr.code;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;

public class BeanModel {

	private final JCodeModel cm;
	private final JDefinedClass bean;

	private final List<String> fields;

	private static final String GET_PREFIX = "get";
	private static final String SET_PREFIX = "set";

	public BeanModel(final String qualifiedName)
			throws JClassAlreadyExistsException {
		cm = new JCodeModel();
		bean = cm._class(qualifiedName);
		fields = new ArrayList<String>();
	}

	protected JCodeModel getCodeModel() {
		return cm;
	}

	protected JAnnotationUse addAnnotation(
			final Class<? extends Annotation> annotation) {
		return bean.annotate(annotation);
	}

	protected void addField(final String name, final Class<?> type) {
		bean.field(JMod.PRIVATE, type, name);
		fields.add(name);
	}

	protected List<String> getListOfFields() {
		final List<String> fields = new ArrayList<String>();
		fields.addAll(bean.fields().keySet());
		return fields;
	}

	protected void addGetter(final String name, final Class<?> type) {
		final String capitalizedName = WordUtils.capitalize(name);
		final JMethod getter = bean.method(JMod.PUBLIC, type, GET_PREFIX
				+ capitalizedName);
		getter.body()._return(JExpr.ref(name));
	}

	protected void addSetter(final String name, final Class<?> type) {
		final String capitalizedName = WordUtils.capitalize(name);
		final JMethod setter = bean.method(JMod.PUBLIC, void.class, SET_PREFIX
				+ capitalizedName);
		setter.param(type, name);
		setter.body().assign(JExpr._this().ref(name), JExpr.ref(name));
	}

}
