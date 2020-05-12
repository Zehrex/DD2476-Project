2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/database/IDatabaseInstance.java
package com.nitro.core.database;

import org.hibernate.SessionFactory;

public interface IDatabaseInstance {

    SessionFactory getSessionFactory();
}
