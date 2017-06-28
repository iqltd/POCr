package org.iqltd.pocr.core.util;

import org.junit.Assert;
import org.junit.Test;

public class ConfigurationTest {

    @Test
    public void testAllCriticalConfigurationPropertiesPresent() {
        for (Configuration conf: Configuration.values()) {
            Assert.assertNotNull(conf.value);
        }
    }
}
