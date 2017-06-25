package com.pocr.core.facelet;

import com.pocr.core.util.JdomUtil;
import org.jdom2.Attribute;
import org.jdom2.Element;

import com.pocr.core.dto.FieldDto;

import static com.pocr.core.constants.JsfConstants.Xhtml.*;

public class InputTextHelper {


	public static Element getInput(final String beanName, final FieldDto field) {
		final Element div = new Element("div");

		final Element label = new Element(OUTPUT_LABEL_ELEM,
				JdomUtil.getNamespace(NamespaceEnum.HTML));
		label.setAttribute(new Attribute(VALUE_ATTR, field.getName()));

		final Element input = new Element(INPUT_TEXT_ELEM,
				JdomUtil.getNamespace(NamespaceEnum.HTML));
		input.setAttribute(new Attribute(REQUIRED_ATTR, String.valueOf(field
				.isRequired())));
		input.setAttribute(new Attribute(LABEL_ATTR, field.getName()));
		input.setAttribute(new Attribute(VALUE_ATTR, getFieldValue(beanName,
				field.getName())));

		div.addContent(label);
		div.addContent(input);

		return div;
	}

	private static String getFieldValue(final String beanName,
			final String fieldName) {
		return "#{" + beanName + "." + fieldName + "}";
	}

}
