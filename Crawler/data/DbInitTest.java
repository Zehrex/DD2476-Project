12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/srcx/itest/org/bgerp/itest/kernel/db/DbInitTest.java
package org.bgerp.itest.kernel.db;

import static org.bgerp.itest.kernel.db.DbTest.conPoolRoot;
import static org.bgerp.itest.kernel.db.DbTest.initConnectionPool;

import org.testng.annotations.Test;

/** For running tests without complete re-creation of DB, change dependsOnGroups to "db" */
@Test(groups = "dbInit", dependsOnGroups = "dbReset")
public class DbInitTest {
    public static final String DBNAME = "bgerp";

    @Test
    public void init() {
        conPoolRoot = initConnectionPool("/" + DBNAME);
    }
}