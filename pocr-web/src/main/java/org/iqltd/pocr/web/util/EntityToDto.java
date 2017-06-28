package org.iqltd.pocr.web.util;

import org.iqltd.pocr.core.dto.FieldDto;
import org.iqltd.pocr.core.dto.FormDto;
import org.iqltd.pocr.entity.FieldEntity;
import org.iqltd.pocr.entity.FormEntity;

import java.util.ArrayList;
import java.util.List;

public class EntityToDto {

	public static FormDto toFormDto(final FormEntity form)
			throws ClassNotFoundException {

		final FormDto dto = new FormDto(form.getName());
		final List<FieldDto> fields = new ArrayList<FieldDto>();
		for (final FieldEntity field : form.getFields()) {
			fields.add(toFieldDto(field));
		}
		dto.setFields(fields);

		return dto;
	}

	public static FieldDto toFieldDto(final FieldEntity field)
			throws ClassNotFoundException {
		return new FieldDto(field.getName(),
				Class.forName(field.getType().getClassName()),
				field.isRequired());
	}
}
