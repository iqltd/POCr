package com.pocr.core.facelet;

import org.jdom2.Element;
import org.junit.Assert;
import org.junit.Test;

import com.pocr.core.dto.FieldDto;

import static com.pocr.core.constants.JsfConstants.Xhtml.*;

public class InputTextHelperTest {

	@Test
	public void nominalCase() {
		final FieldDto field = new FieldDto("fieldName", String.class);
		field.setRequired(true);

		final Element input = InputTextHelper.getInput("bean", field);
		Assert.assertNotNull(input);
		Assert.assertEquals(2, input.getContentSize());

		final Element inputText = input.getChildren().get(1);
		Assert.assertEquals("fieldName", inputText.getAttributeValue(LABEL_ATTR));
		Assert.assertEquals("true", inputText.getAttributeValue(REQUIRED_ATTR));
		Assert.assertEquals("#{bean.fieldName}", inputText.getAttributeValue(VALUE_ATTR));

	}
}
