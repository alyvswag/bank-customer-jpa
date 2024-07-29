package org.example.dao.repository;


import org.example.dao.entity.Customer;
import org.example.dao.entity.Transactions;

import java.util.List;

import static org.example.config.EntityManagerConfig.em;

public class CustomerCardRepo {
    public static void saveData(Object o) {
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
    }

    public static <T> T findData(Long id, Class<T> c) {
        em.getTransaction().begin();
        T object = em.find(c, id);
        em.getTransaction().commit();
        return object;
    }

    public static void updateData(Object o) {
        em.getTransaction().begin();
        em.merge(o);
        em.getTransaction().commit();
    }

    public static List<Customer> getCustomerCard() {
        List<Customer> customers = em.createNativeQuery("SELECT * FROM customer ", Customer.class).getResultList();
//        List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        for (Customer customer : customers) {
            em.refresh(customer);
        }//chatgpt
        return customers;
    }

    public static List<Transactions> getTransactions() {
        List<Transactions> transcations = em.createNativeQuery("SELECT * FROM transactions ", Transactions.class).getResultList();
        return transcations;
    }


}
