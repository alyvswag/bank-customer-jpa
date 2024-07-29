package org.example;


import jakarta.persistence.EntityManager;
import org.example.config.EntityManagerConfig;
import org.example.dao.entity.Customer;
import org.example.dao.repository.CustomerCardRepo;

import java.util.List;


import static org.example.config.EntityManagerConfig.initEntityManager;
import static org.example.menu.MenuRepo.entry;

public class Main {
    public static void main(String[] args) throws Exception {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
        initEntityManager();
        entry();
    }
    // silinen musteri qeydiyyat ede bilmesin
    // customerde transaction case 7 yigilsin
    // noresultexceptionlar check edilsin
    // menular while da olsun case 0 lara diqqet edilsin
    //update password olsun
}