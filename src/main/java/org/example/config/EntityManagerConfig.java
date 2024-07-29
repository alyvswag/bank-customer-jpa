package org.example.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerConfig {
    public static EntityManager em;

    public static void initEntityManager() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("persistence-unit");
        em = entityManagerFactory.createEntityManager();
    }

}
