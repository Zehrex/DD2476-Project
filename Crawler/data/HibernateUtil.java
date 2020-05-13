2
https://raw.githubusercontent.com/marcoseduardoss/mini-mvc/master/demos/001-crud-books-mvc-servlets-jstl-jpa/src/main/java/br/me/crudbooks/model/repository/HibernateUtil.java
package br.me.crudbooks.model.repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum HibernateUtil {

    INSTANCE;

    private final EntityManagerFactory entityManagerFactory;

    HibernateUtil() {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("eesdevops");
        } catch (Exception e) {
            throw new RuntimeException("Unable to configure Hibernate connection: " + e.getMessage());
        }
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
