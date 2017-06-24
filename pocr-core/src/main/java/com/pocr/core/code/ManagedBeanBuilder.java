package com.pocr.core.code;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.pocr.core.application.Generator;
import com.pocr.core.exception.PocrException;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JJavaName;

public class ManagedBeanBuilder {

	private static final String MANAGED_BEAN_NAME = "name";
	private static final char PACKAGE_SEPARATOR = '.';

	private final BeanModel model;

	public ManagedBeanBuilder(final String packageName, final String unqualifiedClassName) {
		try {
			model = new BeanModel(getQualifiedName(packageName, unqualifiedClassName));
			model.addAnnotation(ManagedBean.class).param(MANAGED_BEAN_NAME,
					unqualifiedClassName.toLowerCase());
			model.addAnnotation(SessionScoped.class);
		} catch (final JClassAlreadyExistsException e) {
			throw new PocrException("An error occurred at managed bean creation", e);
		}
	}

	private String getQualifiedName(final String packageName,
			final String className) {
		return packageName + PACKAGE_SEPARATOR + className;
	}

	public void addProperty(final String name, final Class<?> type) {
		validatePropertyName(name);
		model.addField(name, type);
		model.addSetter(name, type);
		model.addGetter(name, type);
	}

	private void validatePropertyName(final String name) {
		if (!JJavaName.isJavaIdentifier(name)) {
			throw new IllegalArgumentException(
					"The property name is not a java identifier.");
		}
		if (model.getListOfFields().contains(name)) {
			throw new IllegalArgumentException(
					"The property is already defined.");
		}
	}

	public Generator getGenerator() {
		return new ManagedBeanGenerator(model);
	}

	protected BeanModel getModel() {
		return model;
	}

}
