package org.example.util;

import org.example.dao.entity.Card;
import org.example.dao.entity.Transactions;
import org.example.enums.CardStatus;
import org.example.enums.Ccy;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Scanner;

import static org.example.dao.repository.CustomerCardRepo.*;
import static org.example.util.CustomerUtil.cards;

public class CardUtil {

    public static Card statusCard(CardStatus cardStatus) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Kartin id sin daxil edin: ");
        Card card = findData(scanner.nextLong(), Card.class);
        card.setStatus(cardStatus);
        System.out.println("Kart ugurla " + cardStatus + " edildi.");
        return card;
    }

    // bundan sonrasi musteri ucundu

    public static void printOwnCards() {
        if (cards != null) {
            cards.stream()
                    .forEach(card -> System.out.println("Card: " + card.getCardNumber() + "\n" +
                            "amount: " + card.getAmount()
                    ));
        } else {
            System.out.println("Sizin kartiniz yoxdur.");
        }
    }

    public static void transferMoneyOtherCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("16 reqemli kart nomrenizi daxil edin: ");
        String cardNumber = scanner.next();
        Card senderCard = cards.stream()
                .filter(c -> c.getCardNumber().equals(cardNumber))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(senderCard)) {
            throw new NullPointerException("Kart tapılmadı.");
        }

        System.out.print("Gönderilen kart nomresini daxil et: ");
        String receiverCardNumber = scanner.next();
        System.out.print("Meblegi  daxil et: ");
        BigDecimal amount = scanner.nextBigDecimal();
        if (amount.compareTo(senderCard.getAmount()) > 0) {
            throw new ArithmeticException("Kifayət qədər balans yoxdur.");
        }

        String senderCardPrefix = senderCard.getCardNumber().substring(0, 4);
        String receiverCardPrefix = receiverCardNumber.substring(0, 4);

        if (senderCardPrefix.equals(receiverCardPrefix)) {
            senderCard.setAmount(senderCard.getAmount().subtract(amount));
        } else {
            BigDecimal fee = amount.multiply(BigDecimal.valueOf(0.05));
            senderCard.setAmount(senderCard.getAmount().subtract(amount.add(fee)));
        }
        updateData(senderCard);
        saveData(new Transactions(receiverCardNumber, senderCard, Ccy.AZN, amount));
        System.out.println("Emeliyyat ugurludu. ");
    }

    public static void transferMoneyPhoneNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("16 reqemli kart nomrenizi daxil edin: ");
        String cardNumber = scanner.next();
        Card senderCard = cards.stream()
                .filter(c -> c.getCardNumber().equals(cardNumber))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(senderCard)) {
            throw new NullPointerException("Kart tapılmadı.");
        }
        System.out.print("Gonderdiyiniz telefon nomresin daxil edin: ");
        String phoneNumber = scanner.next();
        System.out.print("Meblegi daxil edin: ");
        BigDecimal amount = scanner.nextBigDecimal();
        if (amount.compareTo(senderCard.getAmount()) > 0) {
            throw new ArithmeticException("Kifayət qədər balans yoxdur.");
        }
        senderCard.setAmount(senderCard.getAmount().subtract(amount));
        updateData(senderCard);
        saveData(new Transactions(phoneNumber, senderCard, Ccy.AZN, amount));
        System.out.println("Emeliyyat ugurludu. ");
    }


}
