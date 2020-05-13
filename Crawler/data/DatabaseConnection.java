2
https://raw.githubusercontent.com/devwckd/wckd-vips/master/src/main/java/co/wckd/vips/database/DatabaseConnection.java
package co.wckd.vips.database;

import java.sql.Connection;

public interface DatabaseConnection {

    void openConnection();

    Connection getConnection(boolean autoCommit);

}
