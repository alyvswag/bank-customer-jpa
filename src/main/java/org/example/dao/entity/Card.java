package org.example.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.enums.CardStatus;
import org.example.enums.Ccy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "card")
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CardStatus status;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "exp_date")
    private LocalDate expDate;
    @Column(name = "cvv")
    private String cvv;
    @Column(name = "ccy")
    @Enumerated(EnumType.STRING)
    private Ccy ccy;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id" , referencedColumnName = "id")
    private Customer customer;

    public Card(  String cardNumber, LocalDate expDate, String cvv, Ccy ccy, BigDecimal amount, Customer customer) {
        this.status = CardStatus.ACTIVE;
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.cvv = cvv;
        this.ccy = ccy;
        this.amount = amount;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", status=" + status +
                ", cardNumber='" + cardNumber + '\'' +
                ", expDate=" + expDate +
                ", cvv='" + cvv + '\'' +
                ", ccy=" + ccy +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
