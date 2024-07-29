package org.example.menu;

import org.example.enums.CardStatus;
import org.example.util.CustomerCardUtil;
import org.example.util.CustomerUtil;

import java.text.ParseException;
import java.util.Scanner;

import static org.example.config.EntityManagerConfig.initEntityManager;

import static org.example.dao.repository.CustomerCardRepo.*;
import static org.example.menu.AdminPanel.switchUserMenu;
import static org.example.menu.AdminPanel.welcomeMenu;
import static org.example.util.CustomerCardUtil.*;
import static org.example.util.CustomerUtil.addCustomer;
import static org.example.util.CustomerUtil.deleteCustomer;
import static org.example.util.CustomerUtil.*;
import static org.example.util.CardUtil.*;

public class MenuRepo {

    public static void entry() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(switchUserMenu);
        while (true) {
            System.out.print("Secim edin: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    loginEmployee();
                    break;
                case 2:
                    entryEmployee();
                    break;
                default:
                    System.out.println("Qardas yanlis secim etdiniz...");
            }
        }
    }

    public static void loginEmployee() throws ParseException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
        System.out.println(AdminPanel.employeeMenu);
        initEntityManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Secim edin: ");
            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    return;
                case 1:
                    saveData(addCustomer());
                    break;
                case 2:
                    printCustomerCard(getCustomerCard());
                    break;
                case 3:
                    deleteCustomer();
                    break;
                case 4:
                    saveData(currentCustomerNewCard());
                    break;
                case 5:
                    updateData(statusCard(CardStatus.BLOCKED));
                    break;
                case 6:
                    updateData(statusCard(CardStatus.ACTIVE));
                    break;
                case 7:
                    printTranscations(getTransactions());
                    break;
                default:
                    System.out.println("Qardas yanlis secim etdiniz...");
            }
        }
    }

    public static void entryEmployee() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(welcomeMenu);
        while (true) {
            System.out.print("Secim edin: ");
            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    return;
                case 1:
                    signCustomer();
                    privelegesEmployee();
                    break;
                case 2:
                    loginCustomer();
                    privelegesEmployee();
                    break;
                default:
                    System.out.println("Qardas yanlis secim etdiniz...");

            }
        }
    }


    public static void privelegesEmployee() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
        System.out.println(AdminPanel.customerMenu);
        initEntityManager();//umumi yere getir
        while (true) {
            System.out.print("Secim edin: ");
            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    return;
                case 1:
                    printOwnCards();
                    break;
                case 2:
                    transferMoneyOtherCard();
                    break;
                case 3:
                    transferMoneyPhoneNumber();
                    break;
                default:
                    System.out.println("Qardas yanlis secim etdiniz...");

            }
        }
    }

}
