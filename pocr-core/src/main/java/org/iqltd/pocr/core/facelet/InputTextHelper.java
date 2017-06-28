package org.iqltd.pocr.core.facelet;

import org.iqltd.pocr.core.util.JdomUtil;
import org.iqltd.pocr.core.constants.JsfConstants;
import org.iqltd.pocr.core.dto.FieldDto;
import org.jdom2.Attribute;
import org.jdom2.Element;

public class InputTextHelper {


	public static Element getInput(final String beanName, final FieldDto field) {
		final Element div = new Element("div");

		final Element label = new Element(JsfConstants.Xhtml.OUTPUT_LABEL_ELEM,
				JdomUtil.getNamespace(NamespaceEnum.HTML));
		label.setAttribute(new Attribute(JsfConstants.Xhtml.VALUE_ATTR, field.getName()));

		final Element input = new Element(JsfConstants.Xhtml.INPUT_TEXT_ELEM,
				JdomUtil.getNamespace(NamespaceEnum.HTML));
		input.setAttribute(new Attribute(JsfConstants.Xhtml.REQUIRED_ATTR, String.valueOf(field
				.isRequired())));
		input.setAttribute(new Attribute(JsfConstants.Xhtml.LABEL_ATTR, field.getName()));
		input.setAttribute(new Attribute(JsfConstants.Xhtml.VALUE_ATTR, getFieldValue(beanName,
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
