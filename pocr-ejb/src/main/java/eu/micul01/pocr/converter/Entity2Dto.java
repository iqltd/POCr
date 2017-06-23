package eu.micul01.pocr.converter;

import java.util.ArrayList;
import java.util.List;

import com.pocr.core.dto.FieldDto;
import com.pocr.core.dto.FormDto;

import eu.micul01.pocr.entity.FieldEntity;
import eu.micul01.pocr.entity.FormEntity;

public class Entity2Dto {

	public static FormDto convert2FormDto(final FormEntity form)
			throws ClassNotFoundException {

		final FormDto dto = new FormDto(form.getName());
		final List<FieldDto> fields = new ArrayList<FieldDto>();
		for (final FieldEntity field : form.getFields()) {
			fields.add(convert2FieldDto(field));
		}
		dto.setFields(fields);

		return dto;
	}

	public static FieldDto convert2FieldDto(final FieldEntity field)
			throws ClassNotFoundException {
		return new FieldDto(field.getName(),
				Class.forName(field.getType().getClassName()),
				field.isRequired());
	}
}
