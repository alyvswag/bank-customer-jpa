package org.example.util;

import org.example.dao.entity.Card;
import org.example.dao.entity.Customer;
import org.example.dao.entity.Profile;
import org.example.dao.repository.CustomerRepo;
import org.example.dao.repository.ProfileRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Scanner;

import static org.example.dao.repository.CustomerCardRepo.*;
import static org.example.dao.repository.CustomerRepo.*;

public class CustomerUtil {


    public static Customer addCustomer() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.print("Adi: ");
        String name = scanner.next();
        System.out.print("Soyadi: ");
        String surname = scanner.next();
        System.out.print("Dogum tarixin daxil et (yyyy-MM-dd): ");
        String birth_date_str = scanner.next();
        java.util.Date birth_date = dateFormat.parse(birth_date_str);
        System.out.print("Fin daxil edin: ");
        String fin = scanner.next();
        System.out.println("Emeliyyat ugurlu!");
        return new Customer(name, surname, new java.sql.Date(birth_date.getTime()), fin);
    }

    public static void deleteCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Silmek istediyiniz musterinin id sin daxil edin: ");
        Long id = scanner.nextLong();
        Customer c = (Customer) findData(id, Customer.class);
        c.setIsActive(false);
        updateData(c);
        System.out.println("Emeliyyat ugurlu!");
    }

    /// burdan sonra musteri hissesidi

    public static Customer customer;
    public static List<Card>  cards;

    public static void signCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Zehmet olmasa fin daxil edin: ");
        Customer c = findCustomerByFin(scanner.next());
        System.out.print("Yeni password daxil edin: ");
        String password = scanner.next();
        saveData(new Profile(c, password));
        customer = c;
        cards = customer.getCards();
        System.out.println("Hesab ugurla yaradildi");
    }

    public static void loginCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username(fin) : ");
        String fin = scanner.next();
        System.out.print("Password : ");
        String password = scanner.next();
        Profile p = ProfileRepo.findProfileByFin(findCustomerByFin(fin).getId(),password);
        customer = p.getCustomer();
        cards = customer.getCards();
        System.out.println("Giris ugurludu. ");
    }


}
