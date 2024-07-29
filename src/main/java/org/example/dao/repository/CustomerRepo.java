package org.example.dao.repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.dao.entity.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static org.example.config.EntityManagerConfig.em;

public class CustomerRepo {
    public static Customer findCustomerByFin(String fin) throws NoResultException {
        TypedQuery<Customer> query = em.createQuery(
                "SELECT c FROM Customer c WHERE c.fin = :fin", Customer.class);
        query.setParameter("fin", fin);
        return query.getSingleResult();
    }

}
