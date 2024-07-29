package org.example.util;


import org.example.dao.entity.Card;
import org.example.dao.entity.Customer;
import org.example.dao.entity.Transactions;
import org.example.enums.Ccy;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import static org.example.dao.repository.CustomerCardRepo.findData;


public class CustomerCardUtil {
    public static void printCustomerCard(List<Customer> customers){
        customers.forEach(System.out::println);
    }
    public static void printTranscations(List<Transactions> transcations){
        transcations.forEach(System.out::println);
    }
    public static Card currentCustomerNewCard() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.print("Musterinin id sin daxil edin: ");
        Customer customer = findData(scanner.nextLong() , Customer.class);
        System.out.println("**Karti yaradin!**");
        System.out.print("Kart nomresi: ");
        String cardNumber = scanner.next();
        System.out.print("Kartin bitme tarixi (yyyy-MM-dd): ");
        String date_str = scanner.next();
        java.util.Date expDate = dateFormat.parse(date_str);
        System.out.print("Kartin cvv kodu: ");
        String cvv = scanner.next();
        System.out.print("Kartin valyutasi(AZN,RUB,USD): ");
        Ccy ccy = Ccy.valueOf(scanner.next());
        System.out.print("Kartda olan ilkin meblegi daxil edin: ");
        BigDecimal amount = scanner.nextBigDecimal();
        System.out.println("Kart ugurla yaradildi.");
        return new Card(cardNumber, new java.sql.Date(expDate.getTime()).toLocalDate(),cvv,ccy,amount,customer);
    }

}
