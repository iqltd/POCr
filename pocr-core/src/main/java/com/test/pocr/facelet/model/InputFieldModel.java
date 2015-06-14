package com.test.pocr.facelet.model;

import java.io.Serializable;

public class InputFieldModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1890777759149001526L;

	private String beanName;
	private String label;
	private boolean required;
	private boolean disabled;

	public String getLabel() {
		return label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(final boolean required) {
		this.required = required;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(final boolean disabled) {
		this.disabled = disabled;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(final String beanName) {
		this.beanName = beanName;
	}

}
