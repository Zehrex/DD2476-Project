2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/database/DatabaseInstance.java
package com.nitro.core.database;

import org.hibernate.SessionFactory;

public class DatabaseInstance implements IDatabaseInstance {

    private SessionFactory sessionFactory;

    public DatabaseInstance(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
}
