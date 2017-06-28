package org.iqltd.pocr.core.dto;

import java.io.Serializable;

public class FieldDto implements Serializable {

	private static final long serialVersionUID = 1084704123809290784L;

	public FieldDto(final String name, final Class<?> type) {
		this.name = name;
		this.type = type;
	}

	public FieldDto(final String name, final Class<?> type, final boolean required) {
		this(name, type);
		this.required = required;
	}

	private String name;
	private  Class<?> type;
	private boolean visible;
	private boolean required;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(final Class<?> type) {
		this.type = type;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(final boolean visible) {
		this.visible = visible;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(final boolean required) {
		this.required = required;
	}

}
