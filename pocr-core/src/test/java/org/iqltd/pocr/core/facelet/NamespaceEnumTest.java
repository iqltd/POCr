package org.iqltd.pocr.core.facelet;

import org.junit.Assert;
import org.junit.Test;

public class NamespaceEnumTest {

    @Test
    public void getPrefix_withoutSeparator() {
        Assert.assertEquals("", NamespaceEnum.NO_NAMESPACE.getPrefix());
        Assert.assertEquals("h", NamespaceEnum.HTML.getPrefix());
        Assert.assertEquals("f", NamespaceEnum.FACES.getPrefix());
        Assert.assertEquals("ui", NamespaceEnum.FACELETS.getPrefix());
    }

    @Test
    public void getPrefix_withSeparator() {
        Assert.assertEquals("", NamespaceEnum.NO_NAMESPACE.getPrefix(true));
        Assert.assertEquals("h:", NamespaceEnum.HTML.getPrefix(true));
        Assert.assertEquals("f:", NamespaceEnum.FACES.getPrefix(true));
        Assert.assertEquals("ui:", NamespaceEnum.FACELETS.getPrefix(true));
    }
}
