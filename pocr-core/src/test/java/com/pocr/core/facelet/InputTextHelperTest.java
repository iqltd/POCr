package com.pocr.core.facelet;

import org.jdom2.Element;
import org.junit.Assert;
import org.junit.Test;

import com.pocr.core.dto.FieldDto;

public class InputTextHelperTest {

	@Test
	public void nominalCase() {
		final FieldDto field = new FieldDto();
		field.setName("fieldName");
		field.setRequired(true);

		final Element input = InputTextHelper.getInput("bean", field);
		Assert.assertNotNull(input);
		Assert.assertEquals(2, input.getContentSize());

		final Element inputText = input.getChildren().get(1);
		Assert.assertEquals("fieldName",
				inputText.getAttributeValue(InputTextHelper.LABEL_ATTR));
		Assert.assertEquals("true",
				inputText.getAttributeValue(InputTextHelper.REQUIRED_ATTR));
		Assert.assertEquals("#{bean.fieldName}",
				inputText.getAttributeValue(InputTextHelper.VALUE_ATTR));

	}
}
