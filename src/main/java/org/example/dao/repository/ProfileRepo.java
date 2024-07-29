package org.example.dao.repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.example.dao.entity.Customer;
import org.example.dao.entity.Profile;

import static org.example.config.EntityManagerConfig.em;

public class ProfileRepo {
    public static Profile findProfileByFin(Long id,String password) throws NoResultException {
        TypedQuery<Profile> query = em.createQuery(
                "SELECT p FROM Profile p WHERE p.customer.id = :id and p.password = :password", Profile.class);
        query.setParameter("id", id);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
}
