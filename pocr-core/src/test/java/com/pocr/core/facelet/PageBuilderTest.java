package com.pocr.core.facelet;

import com.pocr.core.dto.FieldDto;
import org.junit.Assert;
import org.junit.Test;

public class PageBuilderTest {

    @Test
    public void addComponent_nominal()  {
        PageBuilder builder = new PageBuilder();

        Assert.assertEquals(0, builder.getFields().size());
        builder.addComponent("bean", new FieldDto("field", String.class));
        Assert.assertEquals(1, builder.getFields().size());
    }
}
