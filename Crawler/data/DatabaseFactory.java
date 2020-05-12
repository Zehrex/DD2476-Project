2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/database/DatabaseFactory.java
package com.nitro.core.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DatabaseFactory {

    public static Configuration createMySQLConfiguration(String host, int port, String database, String username, String password, String params) {
        Configuration configuration = createHibernateConfiguration();

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://" + host + ":" + port + "/" + database + params);
        configuration.setProperty("hibernate.connection.username", username);
        configuration.setProperty("hibernate.connection.password", password);

        return configuration;
    }

    public static SessionFactory getSessionFactory(Configuration configuration) {
        if(configuration == null) return null;

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        if(serviceRegistry == null) return null;

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static IDatabaseInstance createDatabaseInstance(SessionFactory sessionFactory) {
        if(sessionFactory == null) return null;

        return new DatabaseInstance(sessionFactory);
    }

    public static Configuration createHibernateConfiguration() {
        return new Configuration();
    }
}
