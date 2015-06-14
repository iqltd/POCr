package com.test.pocr.code;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JJavaName;
import com.test.pocr.application.IGenerator;
import com.test.pocr.exception.PocrException;

public class ManagedBeanBuilder {

	private static final String MANAGED_BEAN_NAME = "name";
	private static final char PACKAGE_SEPARATOR = '.';

	private final BeanModel model;

	/**
	 *
	 * @param packageName
	 *            the package name
	 * @param className
	 *            the unqualified managed bean name
	 * @throws PocrException
	 *             on class creation errors
	 */
	public ManagedBeanBuilder(final String packageName, final String className) {
		try {
			model = new BeanModel(getQualifiedName(packageName, className));
			model.addAnnotation(ManagedBean.class).param(MANAGED_BEAN_NAME,
					className.toLowerCase());
			model.addAnnotation(SessionScoped.class);
		} catch (final JClassAlreadyExistsException e) {
			throw new PocrException(
					"An error occured at managed bean creation", e);
		}
	}

	private String getQualifiedName(final String packageName,
			final String className) {
		// TODO verify arguments
		return packageName + PACKAGE_SEPARATOR + className;
	}

	/**
	 *
	 * @param name
	 * @param type
	 */
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

	/**
	 *
	 * @return a new managed bean generator object
	 */
	public IGenerator getGenerator() {
		return new ManagedBeanGenerator(model);
	}

	protected BeanModel getModel() {
		return model;
	}

}
