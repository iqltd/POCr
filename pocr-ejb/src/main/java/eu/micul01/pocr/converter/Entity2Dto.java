package eu.micul01.pocr.converter;

import java.util.ArrayList;
import java.util.List;

import com.test.pocr.dto.FieldDto;
import com.test.pocr.dto.FormDto;

import eu.micul01.pocr.entity.FieldEntity;
import eu.micul01.pocr.entity.FormEntity;

public class Entity2Dto {

	public static FormDto convert2FormDto(final FormEntity form)
			throws ClassNotFoundException {

		final FormDto dto = new FormDto(form.getName());
		final List<FieldDto> fields = new ArrayList<FieldDto>();
		for (final FieldEntity field : form.getFields()) {
			fields.add(convert2FiedlDto(field));
		}
		dto.setFields(fields);

		return dto;
	}

	public static FieldDto convert2FiedlDto(final FieldEntity field)
			throws ClassNotFoundException {
		final FieldDto dto = new FieldDto();
		dto.setName(field.getName());
		dto.setRequired(field.isRequired());
		dto.setType(Class.forName(field.getType().getClassName()));
		return dto;
	}

	public static void main(final String[] s) throws ClassNotFoundException {
		System.out.println(int.class.getPackage());

		System.out.println(Class.forName("int").toString());

	}
}
