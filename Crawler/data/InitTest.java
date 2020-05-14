12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/srcx/itest/org/bgerp/itest/plugin/report/InitTest.java
package org.bgerp.itest.plugin.report;

import org.bgerp.itest.helper.ConfigHelper;
import org.bgerp.itest.helper.ResourceHelper;
import org.testng.annotations.Test;

@Test(groups = "reportInit", priority = 100, dependsOnGroups = "configInit")
public class InitTest {
    @Test
    public void initConfig() throws Exception {
        ConfigHelper.addIncludedConfig("Plugin Report", ResourceHelper.getResource(this, "config.txt"));
    }
}