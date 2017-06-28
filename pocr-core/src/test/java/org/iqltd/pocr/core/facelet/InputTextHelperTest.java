package org.iqltd.pocr.core.facelet;

import org.iqltd.pocr.core.constants.JsfConstants;
import org.iqltd.pocr.core.dto.FieldDto;
import org.jdom2.Element;
import org.junit.Assert;
import org.junit.Test;

public class InputTextHelperTest {

	@Test
	public void nominalCase() {
		final FieldDto field = new FieldDto("fieldName", String.class);
		field.setRequired(true);

		final Element input = InputTextHelper.getInput("bean", field);
		Assert.assertNotNull(input);
		Assert.assertEquals(2, input.getContentSize());

		final Element inputText = input.getChildren().get(1);
		Assert.assertEquals("fieldName", inputText.getAttributeValue(JsfConstants.Xhtml.LABEL_ATTR));
		Assert.assertEquals("true", inputText.getAttributeValue(JsfConstants.Xhtml.REQUIRED_ATTR));
		Assert.assertEquals("#{bean.fieldName}", inputText.getAttributeValue(JsfConstants.Xhtml.VALUE_ATTR));

	}
}
