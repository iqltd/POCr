package org.iqltd.pocr.core.dto;

import java.util.ArrayList;
import java.util.List;

public class FormDto {

	public FormDto(final String formName) {
		this.formName = formName;
	}

	private final String formName;

	private List<FieldDto> fields = new ArrayList<>();

	public List<FieldDto> getFields() {
		return fields;
	}

	public void setFields(final List<FieldDto> fields) {
		this.fields = fields;
	}

	public String getFormName() {
		return formName;
	}

}
